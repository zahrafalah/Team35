CREATE DATABASE office;
USE office;
create table patients(
   _id INT NOT NULL AUTO_INCREMENT,
   firstname VARCHAR(100) NOT NULL,
   lastname VARCHAR(100) NOT NULL,
   username VARCHAR(100) NOT NULL UNIQUE,
   password VARCHAR(40) NOT NULL,  
   dob VARCHAR(100) NOT NULL,
   immunization VARCHAR(100) DEFAULT "",
   PRIMARY KEY ( _id )
);

create table vitals(
   _id INT NOT NULL AUTO_INCREMENT,
   weight INT(3) NOT NULL,
   height INT(3) NOT NULL,
   temperature INT(3) NOT NULL,
   systolic INT(3),  
   diastolic INT(3),
   PRIMARY KEY ( _id )
);

create table visits(
   _id INT NOT NULL AUTO_INCREMENT,
   patientId VARCHAR(100) NOT NULL,
   vitalId VARCHAR(100) NOT NULL,
   healthIssue VARCHAR(100),
   diagnosis VARCHAR(100),
   prescription VARCHAR(100),
   createdAt VARCHAR(100) NOT NULL,
   PRIMARY KEY ( _id )
);

create table nurses(
   _id INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(100) NOT NULL UNIQUE,
   password VARCHAR(40) NOT NULL,
   PRIMARY KEY ( _id )
);

create table doctors(
   _id INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(100) NOT NULL UNIQUE,
   password VARCHAR(40) NOT NULL,
   PRIMARY KEY ( _id )
);

INSERT INTO patients(username,password, firstname, lastname, dob, immunization) VALUES ('Zahra', '123456', 'Zahra', 'Falah', '1995-01-01', "");
INSERT INTO patients(username,password, firstname, lastname, dob, immunization) VALUES ('Yogesh', '123456', 'Yogesh', 'Vaidya', '1996-01-01', "COVID-19: 2021-06-06");  
INSERT INTO patients(username,password, firstname, lastname, dob, immunization) VALUES ('Nipoon', '123456', 'Nipoon', 'Donta', '1997-01-01', "COVID-19: 2021-06-06, MMR: 2021-06-06");
INSERT INTO patients(username,password, firstname, lastname, dob, immunization) VALUES ('Kartavya', '123456', 'Kartavya', 'Chauhan', '1998-01-01', "COVID-19: 2021-06-06");
INSERT INTO patients(username,password, firstname, lastname, dob, immunization) VALUES ('Uday', '123456', 'Uday', 'Polishetty', '1999-01-01', "COVID-19: 2021-06-06");

INSERT INTO nurses(username,password) VALUES ('Zahra', '123456');
INSERT INTO nurses(username,password) VALUES ('Yogesh', '123456');  
INSERT INTO nurses(username,password) VALUES ('Nipoon', '123456');
INSERT INTO nurses(username,password) VALUES ('Kartavya', '123456');
INSERT INTO nurses(username,password) VALUES ('Uday', '123456');

INSERT INTO doctors(username,password) VALUES ('Zahra', '123456');
INSERT INTO doctors(username,password) VALUES ('Yogesh', '123456');  
INSERT INTO doctors(username,password) VALUES ('Nipoon', '123456');
INSERT INTO doctors(username,password) VALUES ('Kartavya', '123456');
INSERT INTO doctors(username,password) VALUES ('Uday', '123456');

SELECT * FROM patients;