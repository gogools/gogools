-- -----------------------------------------------------
-- Schema r3db
-- -----------------------------------------------------
-- RestRoomReports (R3)

-- -----------------------------------------------------
-- Schema r3db
--
-- RestRoomReports (R3)
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `r3db` DEFAULT CHARACTER SET utf8 ;
USE `r3db` ;

-- -----------------------------------------------------
-- Table `r3db`.`SERVICE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`SERVICE` ;

CREATE TABLE IF NOT EXISTS `r3db`.`SERVICE` (
  `idservicio` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(150) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`idservicio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CLIENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CLIENT` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CLIENT` (
  `idclient` INT NOT NULL AUTO_INCREMENT,
  `idservice` INT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `logo` VARCHAR(45) NULL,
  `isactive` TINYINT(1) NOT NULL,
  `createdate` DATETIME NOT NULL,
  `inactivedate` DATETIME NOT NULL,
  `css` VARCHAR(150) NULL,
  PRIMARY KEY (`idclient`),
  INDEX `fk_client_service1_idx` (`idservice` ASC),
  CONSTRAINT `fk_client_service1`
    FOREIGN KEY (`idservice`)
    REFERENCES `r3db`.`SERVICE` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`SERVICE-RECORD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`SERVICE-RECORD` ;

CREATE TABLE IF NOT EXISTS `r3db`.`SERVICE-RECORD` (
  `idservice` INT NOT NULL,
  `idclient` INT NOT NULL,
  `initdate` DATETIME NOT NULL,
  `enddate` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `isactive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idservice`, `idclient`),
  INDEX `fk_serv-record_servicio1_idx` (`idservice` ASC),
  INDEX `fk_serv-record_client1_idx` (`idclient` ASC),
  CONSTRAINT `fk_serv-record_servicio1`
    FOREIGN KEY (`idservice`)
    REFERENCES `r3db`.`SERVICE` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_serv-record_client1`
    FOREIGN KEY (`idclient`)
    REFERENCES `r3db`.`CLIENT` (`idclient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CARD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CARD` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CARD` (
  `serial` VARCHAR(45) NOT NULL,
  `idclient` INT NOT NULL,
  `cardtype` INT NOT NULL,
  `isactive` TINYINT(1) NOT NULL,
  `createdate` DATETIME NOT NULL,
  `inactivedate` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `latitude` FLOAT NULL,
  `longitude` FLOAT NULL,
  `desc` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`serial`, `idclient`),
  INDEX `fk_card_client1_idx` (`idclient` ASC),
  CONSTRAINT `fk_card_client1`
    FOREIGN KEY (`idclient`)
    REFERENCES `r3db`.`CLIENT` (`idclient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`REPORT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`REPORT` ;

CREATE TABLE IF NOT EXISTS `r3db`.`REPORT` (
  `idreport` INT NOT NULL,
  `idservice` INT NOT NULL,
  `rules` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idreport`),
  INDEX `fk_report_service1_idx` (`idservice` ASC),
  CONSTRAINT `fk_report_service1`
    FOREIGN KEY (`idservice`)
    REFERENCES `r3db`.`SERVICE` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CAT_operation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CAT_operation` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CAT_operation` (
  `key` INT NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(100) NULL,
  `isactive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`key`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CAT_servicetype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CAT_servicetype` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CAT_servicetype` (
  `key` INT NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(100) NULL,
  `isactive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`key`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`GHOST-CARD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`GHOST-CARD` ;

CREATE TABLE IF NOT EXISTS `r3db`.`GHOST-CARD` (
  `idghost` INT NOT NULL AUTO_INCREMENT,
  `serial` VARCHAR(45) NULL,
  `idclient` INT NULL,
  `msg` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`idghost`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CARD-LOG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CARD-LOG` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CARD-LOG` (
  `idcard` INT NOT NULL,
  `type` INT NOT NULL,
  `msg` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idcard`),
  CONSTRAINT `fk_card-log_card1`
    FOREIGN KEY (`idcard`)
    REFERENCES `r3db`.`CARD` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`PROFILE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`PROFILE` ;

CREATE TABLE IF NOT EXISTS `r3db`.`PROFILE` (
  `idprofile` INT NOT NULL,
  PRIMARY KEY (`idprofile`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`USER` ;

CREATE TABLE IF NOT EXISTS `r3db`.`USER` (
  `iduser` INT NOT NULL,
  `idclient` INT ZEROFILL NULL,
  `profile_idprofile` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `lastlogin` DATETIME NULL,
  `isactive` TINYINT(1) NOT NULL,
  `createdate` DATETIME NOT NULL,
  `inactivedate` DATETIME NOT NULL,
  `loginfails` SMALLINT(6) NOT NULL,
  `secret` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`),
  INDEX `fk_user_client1_idx` (`idclient` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_profile1_idx` (`profile_idprofile` ASC),
  CONSTRAINT `fk_user_client1`
    FOREIGN KEY (`idclient`)
    REFERENCES `r3db`.`CLIENT` (`idclient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_profile1`
    FOREIGN KEY (`profile_idprofile`)
    REFERENCES `r3db`.`PROFILE` (`idprofile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CAT_cardtype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CAT_cardtype` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CAT_cardtype` (
  `key` INT NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(100) NULL,
  `isactive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`key`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CAT_cardstatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CAT_cardstatus` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CAT_cardstatus` (
  `key` INT NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(100) NULL,
  `isactive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`key`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CAT_servicestatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CAT_servicestatus` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CAT_servicestatus` (
  `key` INT NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(100) NULL,
  `isactive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`key`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `r3db`.`CAT_cardmsgtype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `r3db`.`CAT_cardmsgtype` ;

CREATE TABLE IF NOT EXISTS `r3db`.`CAT_cardmsgtype` (
  `key` INT NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(100) NULL,
  `isactive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`key`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
