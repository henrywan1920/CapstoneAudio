USE `audio_directory`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `playlist`;
DROP TABLE IF EXISTS `audio`;
DROP TABLE IF EXISTS `transcript`;
SET foreign_key_checks = 1;

--
-- Table schema for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `time_created` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `playlist`
--

INSERT INTO `playlist` (name)
VALUES
('EnglishA1'),
('FrenchB2');

--
-- Table schema for table `transcript`
--

DROP TABLE IF EXISTS `transcript`;
CREATE TABLE `transcript` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `arn` varchar(2048) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transcript`
--

INSERT INTO `transcript` (name, arn)
VALUES
('Celpip_9_T1_11.mp3', 'arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_11.mp3'),
('Celpip_9_T1_12.mp3', 'arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_12.mp3'),
('Celpip_9_T1_13.mp3', 'arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_13.mp3'),
('TEF_9_T1_1.mp3', 'arn:aws:::audio-capstone/mike/FrenchB2/TEF_9_T1_1.mp3');

--
-- Table schema for table `audio`
--

DROP TABLE IF EXISTS `audio`;
CREATE TABLE `audio` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `arn` varchar(2048) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `audio`
--

INSERT INTO `audio` (name, arn)
VALUES
('Celpip_9_T1_11.srt', 'arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_11.srt'),
('Celpip_9_T1_12.srt', 'arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_12.srt'),
('Celpip_9_T1_13.srt', 'arn:aws:::audio-capstone/mike/EnglishA1/Celpip_9_T1_13.srt'),
('TEF_9_T1_1.srt', 'arn:aws:::audio-capstone/mike/FrenchB2/TEF_9_T1_1.srt');

--
-- Table schema for table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL,
    `audio_id` int(11) NOT NULL,
    `transcript_id` int(11) NOT NULL,
    `playlist_id` int(11) NOT NULL,

    PRIMARY KEY (`id`),

    CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_AUDIO` FOREIGN KEY (`audio_id`)
    REFERENCES `audio` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_TRANSCRIPT` FOREIGN KEY (`transcript_id`)
    REFERENCES `transcript` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_PLAYLIST` FOREIGN KEY (`playlist_id`)
    REFERENCES `playlist` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (user_id, audio_id, transcript_id, playlist_id)
VALUES
(2, 1, 1, 1),
(2, 2, 2, 1),
(2, 3, 3, 1),
(2, 4, 4, 2)