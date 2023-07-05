package com.capstone.springboot.audio.exception;

public class NoAudioFoundException extends RuntimeException{
    public NoAudioFoundException(String message) {
        super(message);
    }

    public NoAudioFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAudioFoundException(Throwable cause) {
        super(cause);
    }
}
