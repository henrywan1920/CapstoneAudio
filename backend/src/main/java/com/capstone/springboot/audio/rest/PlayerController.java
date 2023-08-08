package com.capstone.springboot.audio.rest;

import com.capstone.springboot.audio.exception.NoAudioFoundException;
import com.capstone.springboot.audio.models.request.UploadAudioRequest;
import com.capstone.springboot.audio.models.response.FetchAudiosResponse;
import com.capstone.springboot.audio.models.response.PlayAudioResponse;
import com.capstone.springboot.audio.models.response.UploadAudioResponse;
import com.capstone.springboot.audio.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/audios")
    public ResponseEntity<FetchAudiosResponse> fetchAudiosOfCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        HashMap<String, List<String>> audios = playerService.getAudiosUploadedBy(currentPrincipalName);
        if (audios.isEmpty()) {
            throw new NoAudioFoundException(String.format("No audios found with the user %s", currentPrincipalName));
        }
        String message = "Get all the audios associated with a customer";
        int status = HttpStatus.OK.value();
        long timestamp = System.currentTimeMillis();
        FetchAudiosResponse response = new FetchAudiosResponse(status, message, timestamp, audios);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/playlist/{playlist}/audio/{audio}")
    public ResponseEntity<PlayAudioResponse> playAudio(@PathVariable String playlist, @PathVariable String audio) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String audioObjectUrl = playerService.getAudioObjectUrlWith(currentPrincipalName, playlist, audio);
        if (audioObjectUrl.length() == 0) {
            throw new NoAudioFoundException(String.format("No audio (%s) found in the playlist (%s): ", audio, playlist));
        }
        String subtitleObjectUrl = audioObjectUrl.replace(".mp3", ".vtt");
        // String audioObjectUrl = "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/mike456_gmail.com/EnglishA1/Celpip_9_T1_11.mp3";
        // String subtitleObjectUrl = "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/mike456_gmail.com/EnglishA1/Celpip_9_T1_11.vtt";
        String message = "Get the audio and subtitle object URL successfully";
        PlayAudioResponse response = new PlayAudioResponse(message, audioObjectUrl, subtitleObjectUrl);
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/audio")
    public ResponseEntity<UploadAudioResponse> uploadAudio(@ModelAttribute UploadAudioRequest uploadAudioRequest) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String customizedUsername = currentPrincipalName.replace("@", "_");
        String playlist = uploadAudioRequest.getPlaylist();
        String language = uploadAudioRequest.getLanguage();
        String mediaFileName = uploadAudioRequest.getMediaFileName();
        String transcriptFileName = uploadAudioRequest.getTranscriptFileName();
        MultipartFile content = uploadAudioRequest.getContent();
        String filePath = "pool/" + customizedUsername + "/" + playlist + "/" + mediaFileName;
        File audioFile = saveFiletoLocalPool(mediaFileName, customizedUsername, content);
        String audioUrl = saveFiletoAWSS3(filePath, audioFile);
        logger.info("Upload Successfully and Audio URL: " + audioUrl);
        String outputFilePath = "pool/" + customizedUsername + "/" + playlist + "/" + transcriptFileName;
        String transcriptionJobName = customizedUsername + "_" + playlist + "_" + mediaFileName.replace(".", "_");
        playerService.removeExistingTranscriptWith(currentPrincipalName, playlist, transcriptFileName);
        String vttUrl = generateTranscriptFromMedia(audioUrl, transcriptionJobName, language, outputFilePath);
        logger.info("Speech to Text Completed and AWS Transcribe URL: " + vttUrl);
        String existingAudioUrl = playerService.getAudioObjectUrlWith(currentPrincipalName, playlist, mediaFileName);
        logger.info("Transcript file name: " + transcriptFileName);
        if (existingAudioUrl.length() == 0) {
            playerService.createNewRecordWith(currentPrincipalName,
                    mediaFileName, audioUrl,
                    transcriptFileName, vttUrl,
                    playlist);
        }
        logger.info("Add the new audio and subtitle to the database successfully.");
        removeFileFromLocalPool(audioFile);
        String message = "Upload the audio and generate transcription successfully";
        UploadAudioResponse response = new UploadAudioResponse(message, mediaFileName, transcriptFileName);
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*@RequestMapping(path = "/audio", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
     public ResponseEntity<UploadAudioResponse> uploadAudio(@RequestParam String playlist, @RequestParam String mediaFileName,
                                                            @RequestParam String transcriptFileName, @RequestParam String language,
                                                            @RequestPart MultipartFile content) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String customizedUsername = currentPrincipalName.replace("@", "_");
        String filePath = "pool/" + customizedUsername + "/" + playlist + "/" + mediaFileName;
        File audioFile = saveFiletoLocalPool(mediaFileName, customizedUsername, content);
        String audioUrl = saveFiletoAWSS3(filePath, audioFile);
        logger.info("Upload Successfully and Audio URL: " + audioUrl);
        String outputFilePath = "pool/" + customizedUsername + "/" + playlist + "/" + transcriptFileName;
        String transcriptionJobName = customizedUsername + "_" + playlist + "_" + mediaFileName.replace(".", "_");
        playerService.removeExistingTranscriptWith(currentPrincipalName, playlist, transcriptFileName);
        String vttUrl = generateTranscriptFromMedia(audioUrl, transcriptionJobName, language, outputFilePath);
        logger.info("Speech to Text Completed and AWS Transcribe URL: " + vttUrl);
        String existingAudioUrl = playerService.getAudioObjectUrlWith(currentPrincipalName, playlist, mediaFileName);
        logger.info("Transcript file name: " + transcriptFileName);
        if (existingAudioUrl.length() == 0) {
            playerService.createNewRecordWith(currentPrincipalName,
                    mediaFileName, audioUrl,
                    transcriptFileName, vttUrl,
                    playlist);
        }
        logger.info("Add the new audio and subtitle to the database successfully.");
        removeFileFromLocalPool(audioFile);
        String message = "Upload the audio and generate transcription successfully";
        UploadAudioResponse response = new UploadAudioResponse(message, mediaFileName, transcriptFileName);
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    private String saveFiletoAWSS3(String key, File audioFile) throws IOException {
        String fileURL = playerService.putObject(audioFile, key);
        return fileURL;
    }

    private String generateTranscriptFromMedia(String mediaUrl, String jobName, String language, String outputKey) {
        String vttUrl = playerService.speechToTextFromAudio(mediaUrl, jobName, language, outputKey);
        return vttUrl;
    }

    private boolean removeFileFromLocalPool(File file) {
        try {
            boolean result = Files.deleteIfExists(file.toPath());
            return result;
        } catch (IOException e) {
            logger.debug(e.getMessage());
            return true;
        }
    }

    private File saveFiletoLocalPool(String fileName, String customizedUsername, MultipartFile content) throws IOException {
        String filePath = "pool/" + customizedUsername + "/" + fileName;
        File audioFile = new File(filePath);
        if (!audioFile.getParentFile().exists())
            audioFile.getParentFile().mkdirs();
        if (!audioFile.exists())
            audioFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(audioFile, false);
        byte[] audioBytes = content.getBytes();
        fileOutputStream.write(audioBytes);
        return audioFile;
    }
}
