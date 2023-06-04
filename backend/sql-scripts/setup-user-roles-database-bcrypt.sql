CREATE DATABASE  IF NOT EXISTS `audio_directory`;
USE `audio_directory`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
SET foreign_key_checks = 1;

--
-- Table schema for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` char(80) NOT NULL,
    `enabled` tinyint NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.bcryptcalculator.com/
--
-- Default passwords here are: audio123
--
INSERT INTO `user` (`username`, `password`, `enabled`)
VALUES
('anna', '$2a$10$5d7DEkgVem/0Seb/GAfDdu6U9kOOBw4HxeaVUY01Oz9fz3pH7u2ey', 1),
('mike', '$2a$10$5d7DEkgVem/0Seb/GAfDdu6U9kOOBw4HxeaVUY01Oz9fz3pH7u2ey', 1),
('david', '$2a$10$5d7DEkgVem/0Seb/GAfDdu6U9kOOBw4HxeaVUY01Oz9fz3pH7u2ey', 1);

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES
('ROLE_TEMP'),('ROLE_VIP'),('ROLE_SVIP'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_role`;

CREATE TABLE `users_role` (
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    `start` datetime DEFAULT CURRENT_TIMESTAMP,
    `end` datetime DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`user_id`,`role_id`),

    KEY `FK_ROLE_idx` (`role_id`),

    CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_role`
--

INSERT INTO `users_role` (user_id,role_id,start,end)
VALUES
(1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL 7 DAY),
(2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL 30 DAY),
(3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL 7 DAY),
(3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL 30 DAY),
(3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL 1 YEAR),
(3, 4, CURRENT_TIMESTAMP, NULL)
