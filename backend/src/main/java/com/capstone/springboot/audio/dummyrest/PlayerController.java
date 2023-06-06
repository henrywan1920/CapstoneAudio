package com.capstone.springboot.audio.dummyrest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dummy/api")
public class PlayerController {
    @PostMapping("/audio")
    public SimpleResponse uploadAudio() {
        String message = "Upload the audio successfully";
        String fileName = "Celpip_9_T1_11.mp3";
        SimpleResponse response = new SimpleResponse(message, fileName);
        return response;
    }

    @GetMapping("/playlist/{playlist}/audio/{audio}")
    public SimpleResponse playAudio(@PathVariable String playlist, @PathVariable String audio) {
        String audioARN = "arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_11.mp3";
        String subtitleARN = "arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_11.srt";
        String message = "Get the audio and subtitle ARN successfully";
        SimpleResponse response = new SimpleResponse(message, audioARN, subtitleARN);
        return response;
    }
}
