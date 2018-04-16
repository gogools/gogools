
-- ROLES
INSERT INTO `r3db`.`role` (`name`) VALUES ('SUPER');
INSERT INTO `r3db`.`role` (`name`) VALUES ('ADMIN');
INSERT INTO `r3db`.`role` (`name`) VALUES ('USER');

-- TEST CLIENT
INSERT INTO `r3db`.`client` (`idclient`,`idservice`, `name`, `email`, `logo`, `isactive`, `createdate`, `inactivedate`, `css`)
VALUES (0, NULL, 'r3test', 'daniel.pulido@ometik.com', NULL, true, now(), NULL, NULL);
INSERT INTO `r3db`.`client` (`idclient`,`idservice`, `name`, `email`, `logo`, `isactive`, `createdate`, `inactivedate`, `css`)
VALUES (100, NULL, 'Baños Universidad', 'calz_ona@prodigy.net.mx', NULL, true, now(), NULL, NULL);

-- USERS
INSERT INTO `r3db`.`user` ( `idclient`, `username`, `password`, `email`, `lastlogin`, `isactive`, `createdate`, `inactivedate`, `loginfails`, `secret`, `secretanswer`) 
VALUES (NULL, 'gogools', '$2a$10$MTTucbgVxIADMfaKugAZLegOHpMcxSiQ0jiRZR7jyfRJEr7LRrMum', 'gogools@gmail.com', NULL, true, now(), NULL, 0, 'secret', 'xolotl');
INSERT INTO `r3db`.`user` ( `idclient`, `username`, `password`, `email`, `lastlogin`, `isactive`, `createdate`, `inactivedate`, `loginfails`, `secret`, `secretanswer`) 
VALUES (NULL, 'gerava', '$2a$10$DudOEhvocQq/Xiisv4QZauMKq6utb8OZIOh8ceIxRU.P8.IrvbncO', 'ge.ra.va.a@gmail.com', NULL, true, now(), NULL, 0, 'secret', 'morado');
INSERT INTO `r3db`.`user` ( `idclient`, `username`, `password`, `email`, `lastlogin`, `isactive`, `createdate`, `inactivedate`, `loginfails`, `secret`, `secretanswer`) 
VALUES (NULL, 'sermon1', '$2a$10$zNroPVV3.xtYFkendeE4ZuP/ZQzNAH0hBQO5wZpwSw/dCpJ8ijdVS', 'serluismon1@gmail.com', NULL, true, now(), NULL, 0, 'secret', 'genesis');
INSERT INTO `r3db`.`user` ( `idclient`, `username`, `password`, `email`, `lastlogin`, `isactive`, `createdate`, `inactivedate`, `loginfails`, `secret`, `secretanswer`) 
VALUES ((select idclient from client where name = 'r3test'), 'r3test', '$2a$10$DE3eM8tT5Z.I/F9Oht2zGunJx1IyHtYr28W54GnGBlGje/yC9Tdku', 'daniel.pulido@ometik.com', NULL, true, now(), NULL, 0, 'secret', 'papu'); -- +35+p455.,
INSERT INTO `r3db`.`user` ( `idclient`, `username`, `password`, `email`, `lastlogin`, `isactive`, `createdate`, `inactivedate`, `loginfails`, `secret`, `secretanswer`) 
VALUES ((select idclient from client where name = 'Baños Universidad'), 'bañoscu', '$2a$10$q.hoX4/VnjAJ67kLz6sUTuFVi.FBmRFHk5bNtDXPHipbDYs.zZb16', '', NULL, true, now(), NULL, 0, 'secret', 'san'); -- San123** - calz_ona@prodigy.net.mx

INSERT INTO `r3db`.`user_role` (`user_iduser`, `role_idrole`) 
	VALUES ((SELECT iduser FROM user WHERE username = 'gogools'), (SELECT idrole FROM role WHERE name = 'SUPER'));
INSERT INTO `r3db`.`user_role` (`user_iduser`, `role_idrole`) 
	VALUES ((SELECT iduser FROM user WHERE username = 'gerava'), (SELECT idrole FROM role WHERE name = 'ADMIN'));
INSERT INTO `r3db`.`user_role` (`user_iduser`, `role_idrole`) 
	VALUES ((SELECT iduser FROM user WHERE username = 'sermon1'), (SELECT idrole FROM role WHERE name = 'ADMIN'));
INSERT INTO `r3db`.`user_role` (`user_iduser`, `role_idrole`) 
	VALUES ((SELECT iduser FROM user WHERE username = 'r3test'), (SELECT idrole FROM role WHERE name = 'USER'));
INSERT INTO `r3db`.`user_role` (`user_iduser`, `role_idrole`) 
	VALUES ((SELECT iduser FROM user WHERE username = 'bañoscu'), (SELECT idrole FROM role WHERE name = 'USER'));
    
-- CARDS
INSERT INTO `r3db`.`card` (`serial`, `name`, `idclient`, `cardtype`, `isactive`, `createdate`, `inactivedate`, `latitude`, `longitude`, `desc`) 
VALUES (1, 'Test Card 1',(select idclient from client where name = 'r3test'), 1, true, now(), NULL, NULL, NULL, 'Test Card number 1');
INSERT INTO `r3db`.`card` (`serial`, `name`, `idclient`, `cardtype`, `isactive`, `createdate`, `inactivedate`, `latitude`, `longitude`, `desc`) 
VALUES (10, 'Test Card 10', (select idclient from client where name = 'r3test'),1, true, now(), NULL, NULL, NULL, 'Test Card number 10');
INSERT INTO `r3db`.`card` (`serial`, `name`, `idclient`, `cardtype`, `isactive`, `createdate`, `inactivedate`, `latitude`, `longitude`, `desc`) 
VALUES (11, 'Test Card 11', (select idclient from client where name = 'r3test'),1, true, now(), NULL, NULL, NULL, 'Test Card number 11');
INSERT INTO `r3db`.`card` (`serial`, `name`, `idclient`, `cardtype`, `isactive`, `createdate`, `inactivedate`, `latitude`, `longitude`, `desc`) 
VALUES (50, 'Test Card 50', (select idclient from client where name = 'r3test'),1, true, now(), NULL, NULL, NULL, 'Test Card number 50');
INSERT INTO `r3db`.`card` (`serial`, `name`, `idclient`, `cardtype`, `isactive`, `createdate`, `inactivedate`, `latitude`, `longitude`, `desc`) 
VALUES (100, 'Baños Mujeres', (select idclient from client where name = 'Baños Universidad'),1, true, now(), NULL, NULL, NULL, 'Baños Hombres Universidad');
INSERT INTO `r3db`.`card` (`serial`, `name`, `idclient`, `cardtype`, `isactive`, `createdate`, `inactivedate`, `latitude`, `longitude`, `desc`) 
VALUES (101, 'Baños Hombres', (select idclient from client where name = 'Baños Universidad'),1, true, now(), NULL, NULL, NULL, 'Baños Mujeres Universidad');

-- INCREMENTS

-- CATALOGS
INSERT INTO `r3db`.`cat_cardmsg_type` (`value`, `desc`, `isactive`) VALUES ('01', 'Inicio Operación', true);
INSERT INTO `r3db`.`cat_cardmsg_type` (`value`, `desc`, `isactive`) VALUES ('02', 'Corte de Caja', true);
INSERT INTO `r3db`.`cat_cardmsg_type` (`value`, `desc`, `isactive`) VALUES ('03', 'Acceso RFID',  true);
INSERT INTO `r3db`.`cat_cardmsg_type` (`value`, `desc`, `isactive`) VALUES ('04', 'Acceso Usuario', true);
INSERT INTO `r3db`.`cat_cardmsg_type` (`value`, `desc`, `isactive`) VALUES ('05', 'Acceso Token', true);
INSERT INTO `r3db`.`cat_cardmsg_type` (`value`, `desc`, `isactive`) VALUES ('06', 'Alerta', true);
INSERT INTO `r3db`.`cat_cardmsg_type` (`value`, `desc`, `isactive`) VALUES ('08', 'Debug', true);
--
INSERT INTO `r3db`.`cat_card_type` (`value`, `desc`, `isactive`) VALUES ('Estandar', 'Tarjeta Nivel 1', true);
INSERT INTO `r3db`.`cat_card_type` (`value`, `desc`, `isactive`) VALUES ('Medium', 'Tarjeta Nivel 2', true);
INSERT INTO `r3db`.`cat_card_type` (`value`, `desc`, `isactive`) VALUES ('Full', 'Tarjeta Nivel 3', true);