package com.capstone.springboot.audio.service;

import com.amazonaws.services.transcribe.AmazonTranscribeClient;
import com.amazonaws.services.transcribe.model.*;
import com.capstone.springboot.audio.dao.*;
import com.capstone.springboot.audio.entity.Audio;
import com.capstone.springboot.audio.entity.Player;
import com.capstone.springboot.audio.entity.Playlist;
import com.capstone.springboot.audio.entity.Transcript;
import com.capstone.springboot.audio.rest.PlayerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
    @Value("${app.aws.s3.bucketName}")
    private String bucketName;

    private AWSS3Repository s3Repository;
    private AmazonTranscribeClient transcribeClient;

    private AudioRepository audioRepository;
    private TranscriptRepository transcriptRepository;
    private PlaylistRepository playlistRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(AWSS3Repository s3Repository, AmazonTranscribeClient transcribeClient,
                             AudioRepository audioRepository, TranscriptRepository transcriptRepository,
                             PlaylistRepository playlistRepository, PlayerRepository playerRepository) {
        this.s3Repository = s3Repository;
        this.transcribeClient = transcribeClient;
        this.audioRepository = audioRepository;
        this.transcriptRepository = transcriptRepository;
        this.playlistRepository = playlistRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void createNewRecordWith(String username,
                                    String audioName, String audioUrl,
                                    String transcriptName, String transcriptUrl,
                                    String playlist) {
        Audio theAudio = audioRepository.save(new Audio(audioName, audioUrl));
        Transcript theTranscript = transcriptRepository.save(new Transcript(transcriptName, transcriptUrl));
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Playlist thePlaylist = playlistRepository.save(new Playlist(playlist, currentTimestamp));
        int audioId = theAudio.getId();
        int transcriptId = theTranscript.getId();
        int playlistId = thePlaylist.getId();
        Player thePlayer = playerRepository.save(new Player(username, audioId, transcriptId, playlistId));
    }

    @Override
    public String putObject(File audioFile, String key) {
        String fileUrl = s3Repository.getURL(bucketName, key);
        s3Repository.putObject(bucketName, key, audioFile);
        return fileUrl;
    }

    @Override
    public String speechToTextFromAudio(String mediaUri, String jobName, String language, String outputKey) {
        String srtUrl = "";
        Media media = new Media().withMediaFileUri(mediaUri);
        LanguageCode languageCode = getLanguageCodeFrom(language);
        StartTranscriptionJobRequest startTranscriptionJobRequest = new StartTranscriptionJobRequest()
                .withOutputBucketName(bucketName)
                .withOutputKey(outputKey)
                .withSubtitles(new Subtitles().withFormats("srt", "vtt"))
                .withLanguageCode(languageCode)
                .withTranscriptionJobName(jobName)
                .withMedia(media);
        transcribeClient.startTranscriptionJob(startTranscriptionJobRequest);
        GetTranscriptionJobResult transcriptionJobResult = getTranscriptionJobResult(jobName);
        srtUrl = transcriptionJobResult.getTranscriptionJob().getTranscript().getTranscriptFileUri() + ".srt";
        deleteTranscriptionJob(jobName);
        return srtUrl;
    }

    private void deleteTranscriptionJob(String jobName) {
        logger.debug("Delete Transcription Job from amazon Transcribe {}", jobName);
        DeleteTranscriptionJobRequest deleteTranscriptionJobRequest = new DeleteTranscriptionJobRequest()
                .withTranscriptionJobName(jobName);
        transcribeClient.deleteTranscriptionJob(deleteTranscriptionJobRequest);
    }

    private GetTranscriptionJobResult getTranscriptionJobResult(String jobName) {
        logger.debug("Get Transcription Job Result By Job Name : {}",jobName);
        GetTranscriptionJobRequest getTranscriptionJobRequest = new GetTranscriptionJobRequest()
                .withTranscriptionJobName(jobName);
        Boolean resultFound = false;
        TranscriptionJob transcriptionJob = new TranscriptionJob();
        GetTranscriptionJobResult getTranscriptionJobResult = new GetTranscriptionJobResult();
        while (resultFound == false) {
            getTranscriptionJobResult = transcribeClient.getTranscriptionJob(getTranscriptionJobRequest);
            transcriptionJob = getTranscriptionJobResult.getTranscriptionJob();
            if (transcriptionJob.getTranscriptionJobStatus()
                    .equalsIgnoreCase(TranscriptionJobStatus.COMPLETED.name())) {
                return getTranscriptionJobResult;
            } else if (transcriptionJob.getTranscriptionJobStatus()
                    .equalsIgnoreCase(TranscriptionJobStatus.FAILED.name())) {
                return null;
            } else if (transcriptionJob.getTranscriptionJobStatus()
                    .equalsIgnoreCase(TranscriptionJobStatus.IN_PROGRESS.name())) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    logger.debug("Interrupted Exception {}", e.getMessage());
                }
            }
        }
        return getTranscriptionJobResult;
    }

    private LanguageCode getLanguageCodeFrom(String language) {
        if (language.equalsIgnoreCase("french") || language.equalsIgnoreCase("fr")) {
            return LanguageCode.FrCA;
        }
        else if (language.equalsIgnoreCase("chinese") || language.equalsIgnoreCase("cn")) {
            return LanguageCode.ZhCN;
        }
        else {
            return LanguageCode.EnUS;
        }
    }
}
