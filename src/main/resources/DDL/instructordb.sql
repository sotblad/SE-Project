CREATE DATABASE  IF NOT EXISTS `SE_PROJECT`;
use SE_PROJECT;

DROP TABLE IF EXISTS `student_registrations`;
DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `instructors`;

CREATE TABLE `instructors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `username` varchar(45) UNIQUE,
  `password` varchar(255),
  `enabled` boolean not null,
  PRIMARY KEY (`id`)
);

INSERT INTO `instructors` VALUES 
    (1, "zarras", "zarras", "$2a$12$27Lk9T4N8ODnSRgdFyIqweGfjx0vB8iVGbBkgaGXJloFY5ojg3kK2", true),
    (2, "tsiatouxas", "tsiatouxas", "$2a$12$rg7hDGYsOqWkSjb3zGBjcuBWqzsUNIX0K.mjcbdLn.ZO3irBLXlq.", true);
    
CREATE TABLE `authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  FOREIGN KEY (`username`) REFERENCES `instructors`(`username`)
);

INSERT INTO `authorities` (`username`, `authority`) values 
  ('zarras', 'INSTRUCTOR'),
  ('tsiatouxas', 'INSTRUCTOR');

CREATE UNIQUE INDEX `ix_auth_username` on `authorities` (`username`,`authority`);

CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `instructor` int not NULL,
  `syllabus` varchar(255),
  `year` int NOT NULL,
  `semester` int NOT NULL,
  `exam_weight` double DEFAULT 0.00,
  `project_weight` double DEFAULT 0.00,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`instructor`) REFERENCES `instructors` (`fullname`) ON DELETE CASCADE
);

INSERT INTO `courses` VALUES 
    (1,"SE", 1, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 2022, 4, DEFAULT, DEFAULT),
    (2,"VHDL", 2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 2019, 4, DEFAULT, DEFAULT);

CREATE TABLE `student_registrations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `year_of_registration` int NOT NULL,
  `semester` int NOT NULL,
  `course_id` int NOT NULL,
  `grade` double DEFAULT 0,
  `project_grade` double DEFAULT 0,
  `exam_grade` double DEFAULT 0,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
);

INSERT INTO `student_registrations` VALUES 
    (1, 1, 'sotblad', 2018, 7, 1, default, default, default),
    (2, 2, 'stratis', 2018, 6, 1, default, default, default),
    (3, 2, 'stratis', 2018, 7, 2, default, default, default),
    (4, 3, 'jim', 2018, 7, 1, default, default, default);