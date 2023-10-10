create DATABASE IF NOT EXISTS employee_db;

use employee_db;

CREATE TABLE employee
(
id int NOT NULL AUTO_INCREMENT,
name VARCHAR(24) NOT NULL,
email VARCHAR(96),
phone VARCHAR(15),
address VARCHAR(100),
PRIMARY KEY (ID)
);

INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 1', 'user1@gmail.com', '0111111111111', 'address');
INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 2', 'user2@mail.com', '(204) 11111112', 'address');
INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 3', 'user3@mail.com', '(503) 11111112', 'address');
INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 4', 'user4@mail.com', '(171) 11111112', 'address');
INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 5', 'user5@gmail.com', '0111111111111', 'address');
INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 6', 'user6@mail.com', '(204) 11111112', 'address');
INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 7', 'user7@mail.com', '(503) 11111112', 'address');
INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 8', 'user8@mail.com', '(313) 11111112', 'address');
INSERT INTO `employee_db`.`employee` (`name`, `email`, `phone`, `address`) VALUES ('name of user 9', 'user9@mail.com', '(171) 11111112', 'address');	
