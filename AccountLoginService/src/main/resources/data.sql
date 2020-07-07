DROP TABLE IF EXISTS user;
CREATE TABLE user ( id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(20) NOT NULL, password VARCHAR(70) NOT NULL);  
INSERT INTO user (username, password) VALUES
('userone', '$2a$04$OrCDnLlCQLHWnmpFw8cvIOOxH3nY6vtVnS7PCqBC.FB.nBbZUDv1q'),
('usertwo', '$2a$04$PKJXGhfMZxP7Isfd8AsCg.sbFJQdtB3aX29Zp/GkRKRV18AsQR1gS'),
('userthree', '$2a$04$RVN1tBvgwdREP3TYlPyr4uE6gaH6AMZPa031xYJtX.oBf6f9NGISW');