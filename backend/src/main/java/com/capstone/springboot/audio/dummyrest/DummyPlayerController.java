package com.capstone.springboot.audio.dummyrest;

import com.capstone.springboot.audio.models.request.UploadAudioRequest;
import com.capstone.springboot.audio.models.response.FetchAudiosResponse;
import com.capstone.springboot.audio.models.response.PlayAudioResponse;
import com.capstone.springboot.audio.models.response.UploadAudioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dummy/api")
public class DummyPlayerController {
    @GetMapping("/audios")
    public ResponseEntity<FetchAudiosResponse> fetchAudiosOfCustomer() {
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
        int status = HttpStatus.OK.value();
        long timeStamp = System.currentTimeMillis();
        FetchAudiosResponse response = new FetchAudiosResponse(status, message, timeStamp, audios);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/audio")
    public ResponseEntity<UploadAudioResponse> uploadAudio(@RequestBody UploadAudioRequest uploadAudioRequest) {
        String message = "Upload the audio successfully";
        String mediaFileName = "Celpip_9_T1_11.mp3";
        String transcriptFileName = "Celpip_9_T1_11.srt.srt";
        UploadAudioResponse response = new UploadAudioResponse(message, mediaFileName, transcriptFileName);
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/playlist/{playlist}/audio/{audio}")
    public ResponseEntity<PlayAudioResponse> playAudio(@PathVariable String playlist, @PathVariable String audio) {
        String audioObjectUrl = "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/anna123_outlook.com/EnglishA1/Celpip_9_T1_11.mp3";
        String subtitleObjectUrl = "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/anna123_outlook.com/EnglishA1/Celpip_9_T1_11.srt.srt";
        String message = "Get the audio and subtitle url successfully";
        PlayAudioResponse response = new PlayAudioResponse(message, audioObjectUrl, subtitleObjectUrl);
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
