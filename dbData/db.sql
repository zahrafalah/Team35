CREATE DATABASE  office;
USE office;
create table patients(
   _id INT NOT NULL AUTO_INCREMENT,
   firstname VARCHAR(100) NOT NULL,
   lastname VARCHAR(100) NOT NULL,
   username VARCHAR(100) NOT NULL UNIQUE,
   password VARCHAR(40) NOT NULL,  
   dob VARCHAR(100) NOT NULL,
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

INSERT INTO patients(username,password, firstname, lastname, dob) VALUES ('Zahra', '123456', 'Zahra', 'Falah', '01/01/1995');
INSERT INTO patients(username,password, firstname, lastname, dob) VALUES ('Yogesh', '123456', 'Yogesh', 'Vaidya', '01/01/1996');  
INSERT INTO patients(username,password, firstname, lastname, dob) VALUES ('Nipoon', '123456', 'Nipoon', 'Donta', '01/01/1997');
INSERT INTO patients(username,password, firstname, lastname, dob) VALUES ('Kartavya', '123456', 'Kartavya', 'Chauhan', '01/01/1998');
INSERT INTO patients(username,password, firstname, lastname, dob) VALUES ('Uday', '123456', 'Uday', 'Polishetty', '01/01/1999');

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
DROP TABLE patients ;

SELECT * FROM  patients WHERE username = 'Zahra' AND password = '123456';