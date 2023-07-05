package com.capstone.springboot.audio.rest;

import com.capstone.springboot.audio.exception.NoAudioFoundException;
import com.capstone.springboot.audio.models.request.UploadAudioRequest;
import com.capstone.springboot.audio.models.response.FetchAudiosResponse;
import com.capstone.springboot.audio.models.response.PlayAudioResponse;
import com.capstone.springboot.audio.models.response.UploadAudioResponse;
import com.capstone.springboot.audio.service.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/audios")
    public FetchAudiosResponse fetchAudiosOfCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        HashMap<String, List<String>> audios = playerService.getAudiosUploadedBy(currentPrincipalName);
        if (audios.isEmpty()) {
            throw new NoAudioFoundException(String.format("No audios found with the user %s", currentPrincipalName));
        }
        String message = "Get all the audios associated with a customer";
        FetchAudiosResponse response = new FetchAudiosResponse(audios, message);
        return response;

        /*String message = "Get all the audios associated with a customer";
        List<String> audiosNameEnglish = new ArrayList<>();
        audiosNameEnglish.add("Celpip_9_T1_11");
        audiosNameEnglish.add("Celpip_9_T1_12");
        audiosNameEnglish.add("Celpip_9_T1_13");
        List<String> audiosNameFrench = new ArrayList<>();
        audiosNameFrench.add("TEF_9_T1_1");
        HashMap<String, List<String>> audios = new HashMap<>();
        audios.put("EnglishA1", audiosNameEnglish);
        audios.put("FrenchB2", audiosNameFrench);
        FetchAudiosResponse response = new FetchAudiosResponse(audios, message);
        return response;*/
    }

    @GetMapping("/playlist/{playlist}/audio/{audio}")
    public PlayAudioResponse playAudio(@PathVariable String playlist, @PathVariable String audio) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String audioObjectUrl = playerService.getAudioObjectUrlWith(currentPrincipalName, playlist, audio);
        if (audioObjectUrl.length() == 0) {
            throw new NoAudioFoundException(String.format("No audio (%s) found in the playlist (%s): ", audio, playlist));
        }
        String subtitleObjectUrl = audioObjectUrl.replace(".mp3", ".srt.srt");
        // String audioObjectUrl = "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/mike456_gmail.com/EnglishA1/Celpip_9_T1_11.mp3";
        // String subtitleObjectUrl = "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/mike456_gmail.com/EnglishA1/Celpip_9_T1_11.srt.srt";
        String message = "Get the audio and subtitle object URL successfully";
        PlayAudioResponse response = new PlayAudioResponse(audioObjectUrl, subtitleObjectUrl, message);
        return response;
    }

    @PostMapping("/audio")
    public UploadAudioResponse uploadAudio(@ModelAttribute UploadAudioRequest uploadAudioRequest) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String customizedUsername = currentPrincipalName.replace("@", "_");
        String mediaFileName = uploadAudioRequest.getMediaFileName();
        String transcriptFileName = uploadAudioRequest.getTranscriptFileName();
        String playlist = uploadAudioRequest.getPlaylist();
        String targetLanguage = uploadAudioRequest.getLanguage();
        String filePath = "pool/" + customizedUsername + "/" + playlist + "/" + mediaFileName;
        MultipartFile content = uploadAudioRequest.getContent();
        File audioFile = saveFiletoLocalPool(mediaFileName, customizedUsername, content);
        String audioUrl = saveFiletoAWSS3(filePath, audioFile);
        logger.info("Upload Successfully and Audio URL: " + audioUrl);
        String outputFilePath = "pool/" + customizedUsername + "/" + playlist + "/" + transcriptFileName;
        String transcriptionJobName = customizedUsername + "_" + playlist + "_" + mediaFileName.replace(".", "_");
        String srtUrl = generateTranscriptFromMedia(audioUrl, transcriptionJobName, targetLanguage, outputFilePath);
        logger.info("Speech to Text Completed and AWS Transcribe URL: " + srtUrl);
        playerService.createNewRecordWith(currentPrincipalName,
                mediaFileName, audioUrl,
                transcriptFileName, srtUrl,
                playlist);
        logger.info("Add the new audio and subtitle to the database successfully.");
        removeFileFromLocalPool(audioFile);
        String message = "Upload the audio and generate transcription successfully";
        UploadAudioResponse response = new UploadAudioResponse(mediaFileName, transcriptFileName, message);
        return response;
    }

    private String saveFiletoAWSS3(String key, File audioFile) throws IOException {
        String fileURL = playerService.putObject(audioFile, key);
        return fileURL;
    }

    private String generateTranscriptFromMedia(String mediaUrl, String jobName, String language, String outputKey) {
        String srtUrl = playerService.speechToTextFromAudio(mediaUrl, jobName, language, outputKey);
        return srtUrl;
    }

    private boolean removeFileFromLocalPool(File file) {
        try {
            boolean result = Files.deleteIfExists(file.toPath());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
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
