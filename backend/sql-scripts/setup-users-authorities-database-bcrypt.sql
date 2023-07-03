CREATE DATABASE  IF NOT EXISTS `audio_directory`;
USE `audio_directory`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `authorities`;
SET foreign_key_checks = 1;

--
-- Table schema for table `users`
--
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL UNIQUE,
  `password` varchar(80) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users`
VALUES
('anna123@outlook.com','$2a$10$84bK20xZGLyyI4QWtCVr3uBgpo.UfaYtD4wBbMtBtg44BSq5HLrXq',1),
('mike456@gmail.com','$2a$10$84bK20xZGLyyI4QWtCVr3uBgpo.UfaYtD4wBbMtBtg44BSq5HLrXq',1),
('david789@yahoo.com','$2a$10$84bK20xZGLyyI4QWtCVr3uBgpo.UfaYtD4wBbMtBtg44BSq5HLrXq',1);

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`
VALUES
('anna123@outlook.com','ROLE_TEMP'),
('mike456@gmail.com','ROLE_VIP'),
('david789@yahoo.com','ROLE_SVIP'),
('david789@yahoo.com','ROLE_ADMIN');