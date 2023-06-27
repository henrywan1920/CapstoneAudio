package com.capstone.springboot.audio.dummyrest;

import com.capstone.springboot.audio.models.request.UploadAudioRequest;
import com.capstone.springboot.audio.models.response.FetchAudiosResponse;
import com.capstone.springboot.audio.models.response.PlayAudioResponse;
import com.capstone.springboot.audio.models.response.UploadAudioResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dummy/api")
public class PlayerController {
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
    public UploadAudioResponse uploadAudio(@RequestBody UploadAudioRequest uploadAudioRequest) {
        String message = "Upload the audio successfully";
        String fileName = "Celpip_9_T1_11.mp3";
        UploadAudioResponse response = new UploadAudioResponse(fileName, message);
        return response;
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
