package com.capstone.springboot.audio.dummyrest;

public class SimpleResponse {
    private String message;
    private String fileName;
    private String audo;
    private String subtitle;

    public SimpleResponse(String message) {
        this.message = message;
    }

    public SimpleResponse(String message, String fileName) {
        this.message = message;
        this.fileName = fileName;
    }

    public SimpleResponse(String message, String audo, String subtitle) {
        this.message = message;
        this.audo = audo;
        this.subtitle = subtitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        if (this.fileName != null && this.fileName.length() >0) {
            return "SimpleResponse{" +
                    "message='" + message + '\'' +
                    ", fileName='" + fileName + '\'' +
                    '}';
        }
        else if (this.audo != null && this.audo.length() > 0) {
            return "SimpleResponse{" +
                    "message='" + message + '\'' +
                    ", audo='" + audo + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    '}';
        }
        else {
            return "SimpleResponse{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
