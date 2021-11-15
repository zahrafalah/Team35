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

INSERT INTO patients(username,password) VALUES ('Zahra', '123456');
INSERT INTO patients(username,password) VALUES ('Yogesh', '123456');  
INSERT INTO patients(username,password) VALUES ('Nipoon', '123456');
INSERT INTO patients(username,password) VALUES ('Kartavya', '123456');
INSERT INTO patients(username,password) VALUES ('Uday', '123456');

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