DROP DATABASE IF EXISTS superhero_database_test;
CREATE DATABASE IF NOT EXISTS superhero_database_test;

USE superhero_database_test;

CREATE TABLE IF NOT EXISTS Superheroes (
  `superhero_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `description` VARCHAR(100) NULL,
  `superpower` VARCHAR(50) NULL,
  PRIMARY KEY (`superhero_id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Locations (
  `location_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `description` VARCHAR(100) NULL,
  `address` VARCHAR(50) NULL,
  `coordinates` VARCHAR(50) NULL,
  PRIMARY KEY (`location_id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Organizations (
  `organization_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `description` VARCHAR(50) NULL,
  `contact_info` VARCHAR(50) NULL,
  PRIMARY KEY (`organization_id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Sightings (
  `sighting_id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `superhero_id` INT(11) NOT NULL,
  `location_id` INT(11) NOT NULL,
  PRIMARY KEY (`sighting_id`),
  
    FOREIGN KEY (`superhero_id`)
    REFERENCES `Superheroes` (`superhero_id`),
    FOREIGN KEY (`location_id`)
    REFERENCES `Locations` (`location_id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Superheroes_Organizations (
  `superhero_id` INT(11) NOT NULL,
  `organization_id` INT(11) NOT NULL,
  
    FOREIGN KEY (`superhero_id`)
    REFERENCES `Superheroes` (`superhero_id`),
    FOREIGN KEY (`organization_id`)
    REFERENCES `Organizations` (`organization_id`)
) ENGINE = InnoDB;
