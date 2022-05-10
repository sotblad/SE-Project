CREATE DATABASE  IF NOT EXISTS `SE_PROJECT`;
use SE_PROJECT;

DROP TABLE IF EXISTS `student_registrations`;
DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `instructor` varchar(45),
  `syllabus` varchar(255),
  `year` int NOT NULL,
  `semester` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `courses` VALUES 
    (1,"SE", "zarras", "KAAARK", 4, 4),
    (2,"VHDL", "tsiatouxas", "KAAARK", 4, 4);

CREATE TABLE `student_registrations` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `year_of_registration` int NOT NULL,
  `semester` int NOT NULL,
  `course_id` int NOT NULL,
  `grade` double DEFAULT 0,
  `project_grade` double DEFAULT 0,
  `exam_grade` double DEFAULT 0,
  PRIMARY KEY (`id`, `course_id`),
  FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `student_registrations` VALUES 
    (1,'sotblad', 2018, 7, 1, default, default, default),
    (2,'stratis', 2018, 6, 1, default, default, default),
    (2,'stratis', 2018, 7, 2, default, default, default),
    (3,'jim', 2018, 7, 1, default, default, default);
