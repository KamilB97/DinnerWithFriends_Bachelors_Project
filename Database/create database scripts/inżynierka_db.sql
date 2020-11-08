CREATE DATABASE  IF NOT EXISTS `dinner_app_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dinner_app_db`;
ALTER DATABASE `dinner_app_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Host: 127.0.0.1    Database: web_customer_tracker
-- ------------------------------------------------------
-- Server version	1.0.0

-- DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS`user` (
	  `user_id` int(11) NOT NULL AUTO_INCREMENT,
	  `email` varchar(45) NOT NULL,
	  `phone` varchar(9) DEFAULT NULL,
	  `password` varchar(68) NOT NULL,
	  `register_date` timestamp DEFAULT CURRENT_TIMESTAMP,
	  PRIMARY KEY (`user_id`)
	) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
	
	  CREATE TABLE IF NOT EXISTS `dietaryPreference` (
	  `diet_id` INT(10) NOT NULL AUTO_INCREMENT,
	  `diet_name` VARCHAR(45),
	  PRIMARY KEY (`diet_id`)
	  )ENGINE=InnoDB DEFAULT CHARSET=utf8;
      
      INSERT INTO `dietaryPreference`
      (`diet_name`)
      VALUES
      ("polska"),
      ("włoska"),
      ("azjatycka"),
      ("japońska"),
      ("tajska"),
      ("turecka");
      
	  CREATE TABLE IF NOT EXISTS `interesting` (
	  `interesting_id` INT(10) NOT NULL AUTO_INCREMENT,
	  `interesting_name` VARCHAR(45),
	  PRIMARY KEY (`interesting_id`)
	  )ENGINE=InnoDB DEFAULT CHARSET=utf8;
      
      INSERT INTO `interesting`
      (`interesting_name`)
      VALUES
      ("ekonomiczne"),
	  ("humanistyczne"),
      ("przyrodnicze"),
      ("sport"),
      ("ścisłe");
      
	  CREATE TABLE IF NOT EXISTS `country` (
	  `country_id` INT(10) NOT NULL AUTO_INCREMENT,
	  `country_name` VARCHAR(45),
	  PRIMARY KEY (`country_id`)
	  )ENGINE=InnoDB DEFAULT CHARSET=utf8;
      
      INSERT INTO `country` (`country_name`)
      VALUES ("Polska");
    
	  CREATE TABLE IF NOT EXISTS `province` (
	  `province_id` INT(10) NOT NULL AUTO_INCREMENT,
	  `province_name` VARCHAR(45),
	  `country_id` INT(10),	
	  PRIMARY KEY (`province_id`),
       FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
       ON DELETE SET NULL
	  -- dodaj fk country
	  )ENGINE=InnoDB DEFAULT CHARSET=utf8;
      
       INSERT INTO `province` 
        (`province_name`,`country_id`)
       VALUES 
		("dolnośląskie",1),
		("kujawsko-pomorskie",1),
		("lubelskie",1),
		("lubuskie",1),
		("łódzkie",1),
        ("małopolskie",1),
        ("mazowieckie",1),
        ("opolskie",1),
        ("podkarpackie",1),
        ("podlaskie",1),
        ("pomorskie",1),
        ("śląskie",1),
        ("świętokrzyskie",1),
        ("warmińsko-mazurskie",1),
        ("wielkopolskie",1),
        ("zachodniopomorskie",1);
  
	   CREATE TABLE IF NOT EXISTS `city` (
	  `city_id` INT(10) NOT NULL AUTO_INCREMENT,
	  `city_name` VARCHAR(45),
	  `province_id` INT(10),
	  PRIMARY KEY (`city_id`),
       FOREIGN KEY (`province_id`) REFERENCES `province` (`province_id`)
       ON DELETE SET NULL
	  )ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
    INSERT INTO `city` 
        (`city_name`,`province_id`)
       VALUES 
		("Wrocław",1),
        ("Bydgoszcz",2),
        ("Toruń",2),
        ("Lublin",3),
        ("Gorzów Wielkopolski",4),
        ("Zielona Góra",4),
        ("Łódź",5),
        ("Kraków",6),
        ("Warszawa",7),
        ("Opole",8),
        ("Rzeszów",9),
        ("Białystok",10),
        ("Gdańsk",11),
        ("Katowice",12),
        ("Kielce",13),
        ("Olsztyn",14),
        ("Poznań",15),
        ("Szczecin",16);
    
    CREATE TABLE IF NOT EXISTS `image` (
	  `image_id` INT(11) NOT NULL AUTO_INCREMENT,
	  `url` blob,  -- varchar
	  PRIMARY KEY (`image_id`)
	  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

	CREATE TABLE IF NOT EXISTS `profile` (
	  `profile_id` int(11) NOT NULL AUTO_INCREMENT,
	  `user_id` int(11) ,
	  `image_id` INT(10) DEFAULT NULL,
	  `firstname` varchar(45) NOT NULL,
	  `lastname` varchar(45) NOT NULL,
	  `gender` ENUM("male","female") NOT NULL,
      `age` int(3) default null,
	  `about` varchar(245) DEFAULT NULL,
	  `update_date` timestamp NOT NULL,
	  `status` tinyint(1) NOT NULL DEFAULT 2,
	  PRIMARY KEY (`profile_id`),
	  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
      ON DELETE CASCADE,
      FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`)
      ON DELETE SET NULL

	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
      CREATE TABLE IF NOT EXISTS `profile_dietaryPreference` (
  `profile_dietary_id` int(10) NOT NULL auto_increment,
  `profile_id` INT(10) NOT NULL,
  `diet_id` INT(10) NOT NULL,
  PRIMARY KEY (`profile_dietary_id`),
  FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
  ON DELETE CASCADE,
  FOREIGN KEY (`diet_id`) REFERENCES `dietaryPreference` (`diet_id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   CREATE TABLE IF NOT EXISTS `profile_city` (
  `profile_id` INT(10) NOT NULL,
  `city_id` INT(10) NOT NULL,
  PRIMARY KEY (`profile_id`),
  FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
  ON DELETE CASCADE,
  FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  CREATE TABLE IF NOT EXISTS `profile_interesting` (
   `profile_interesting_id` int(10) NOT NULL auto_increment,
   `profile_id` INT(10) NOT NULL,
   `interesting_id` INT(10) NOT NULL,
    PRIMARY KEY (`profile_interesting_id`),
    FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
    ON DELETE CASCADE,
	FOREIGN KEY (`interesting_id`) REFERENCES `interesting` (`interesting_id`)
	ON DELETE NO ACTION ON UPDATE NO ACTION
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   CREATE TABLE IF NOT EXISTS `connection`(
  -- `connection_id` INT(20) NOT NULL auto_increment,
   `base_profile_id` INT(10),
   `target_profile_id` INT(10),
   `fit_condition` INT(1),
    PRIMARY KEY (`base_profile_id`,`target_profile_id`),
    FOREIGN KEY (`base_profile_id`) REFERENCES `profile` (`profile_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (`target_profile_id`) REFERENCES `profile` (`profile_id`)
	ON DELETE NO ACTION ON UPDATE NO ACTION
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
    CREATE TABLE IF NOT EXISTS `swipe`(
   `swipe_id` INT(20) NOT NULL auto_increment,
   `base_profile_id` INT(10) NOT NULL,
   `target_profile_id` INT(10) NOT NULL,
   `profile_id` INT(10) NOT NULL,
   `liked` int(1),
   `date` timestamp,
    PRIMARY KEY (`swipe_id`),
    FOREIGN KEY (`base_profile_id`) REFERENCES `connection` (`base_profile_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
     FOREIGN KEY (`target_profile_id`) REFERENCES `connection` (`target_profile_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   CREATE TABLE IF NOT EXISTS`conversation` (

`conversation_id` int(11) NOT NULL auto_increment,
`name` varchar(60) null,
`time` timestamp null,

PRIMARY KEY (`conversation_id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS`conversation_participant` (

`conversation_participant_id` int(11) NOT NULL auto_increment,
`conversation_id` int(11) NOT NULL,
`profile_id` int(11) NOT NULL,
`active` tinyint NOT NULL, 	
PRIMARY KEY (`conversation_participant_id`),
FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
   
   
    CREATE TABLE IF NOT EXISTS `matched`(
   `profile1` INT(10),
   `profile2` INT(10),
   `date` timestamp,
   `conversation_id` int(11) NOT NULL,
    PRIMARY KEY (`profile1`, `profile2`),
    FOREIGN KEY (`profile1`) REFERENCES `profile` (`profile_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (`profile2`)  REFERENCES `profile` (`profile_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
    CREATE TABLE IF NOT EXISTS `not_matched`(
   `profile1` INT(10),
   `profile2` INT(10),
   `date` timestamp,
    PRIMARY KEY (`profile1`, `profile2`),
    FOREIGN KEY (`profile1`) REFERENCES `profile` (`profile_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (`profile2`)  REFERENCES `profile` (`profile_id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   
  




