CREATE DATABASE LibraryDB;
USE LibraryDB;
CREATE TABLE `books` (
  `book_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `availability_status` enum('AVAILABLE','CHECKEDOUT') NOT NULL,
  PRIMARY KEY (`book_id`),
  KEY `idx_book_title` (`title`)
)

