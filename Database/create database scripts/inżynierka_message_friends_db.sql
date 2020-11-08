CREATE DATABASE  IF NOT EXISTS `dinner_app_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dinner_app_db`;
ALTER DATABASE `dinner_app_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `friends` (

`friend_id` int(11) NOT NULL auto_increment,
`conversation_id` int(11) NOT NULL,
`profile_1` int(11) NOT NULL,
`profile_2` int(11) NOT NULL,
PRIMARY KEY (`friend_id`),

FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`profile_1`) REFERENCES `profile` (`profile_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`profile_2`) REFERENCES `profile` (`profile_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `invitations` (

`invitation_id` int(11) NOT NULL auto_increment,
`sender_id` int(11) NOT NULL,
`receiver_id` int(11) NOT NULL,
PRIMARY KEY (`invitation_id`),

FOREIGN KEY (`sender_id`) REFERENCES `profile` (`profile_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`receiver_id`) REFERENCES `profile` (`profile_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
   
CREATE TABLE IF NOT EXISTS `message` (
`message_id` int(11) NOT NULL auto_increment,
`conversation_id` int(11) NOT NULL ,
`sender_id` int(11) NOT NULL,
`date` timestamp NOT NULL,
`text` varchar(300) NOT NULL,
PRIMARY KEY (`message_id`),
FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`sender_id`) REFERENCES `profile` (`profile_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `unread_message` (

`unread_message_id` int(11) NOT NULL auto_increment,
`conversation_id` int(11) NOT NULL,
`to_profile` int(11) NOT NULL,

PRIMARY KEY (`unread_message_id`),
FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`),
-- ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`to_profile`) REFERENCES `profile` (`profile_id`)
-- ON DELETE NO ACTION ON UPDATE NO ACTION -->

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


