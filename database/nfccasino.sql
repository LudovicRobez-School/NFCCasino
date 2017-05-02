--DROP DATABASE `nfccasino`;
CREATE DATABASE  IF NOT EXISTS `nfccasino` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nfccasino`;

CREATE TABLE IF NOT EXISTS Customer (
  c_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  c_mail varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  c_firstname varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  c_lastname varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  c_address varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  c_zipcode varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  c_city varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  c_region varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  c_state varchar(255) COLLATE utf8_unicode_ci NOT NULL
);

INSERT INTO Customer(c_id, c_mail, c_firstname, c_lastname, c_address, c_zipcode, c_city, c_region, c_state)
VALUES (1, "test@mail.com", "Michel", "DUPONT", "10 avenue de la Californie", "06200", "Nice", "PACA", "France");

CREATE TABLE IF NOT EXISTS BankCard (
  b_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  b_c_id int(11) NOT NULL,
  b_number int(16) NOT NULL,
  b_expDate date,
  b_ctrlNumber int(3) NOT NULL,
  b_type varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  CONSTRAINT fk_b_c_id FOREIGN KEY (b_c_id) REFERENCES Customer(c_id)
);

CREATE TABLE IF NOT EXISTS Payment (
  p_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  p_c_id int(11) NOT NULL,
  p_b_id int(11) NOT NULL,
  p_amount float(11) NOT NULL,
  p_token varchar(255) COLLATE utf8_unicode_ci,
  CONSTRAINT fk_p_c_id FOREIGN KEY (p_c_id) REFERENCES Customer(c_id),
  CONSTRAINT fk_p_b_id FOREIGN KEY (p_b_id) REFERENCES BankCard(b_id)
);

