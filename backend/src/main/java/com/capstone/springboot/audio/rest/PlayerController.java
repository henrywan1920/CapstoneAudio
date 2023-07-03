package com.capstone.springboot.audio.rest;

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
        String message = "Get all the audios associated with a customer";
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

    @GetMapping("/playlist/{playlist}/audio/{audio}")
    public PlayAudioResponse playAudio(@PathVariable String playlist, @PathVariable String audio) {
        String audioARN = "arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_11.mp3";
        String subtitleARN = "arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_11.srt";
        String message = "Get the audio and subtitle ARN successfully";
        PlayAudioResponse response = new PlayAudioResponse(audioARN, subtitleARN, message);
        return response;
    }
}
