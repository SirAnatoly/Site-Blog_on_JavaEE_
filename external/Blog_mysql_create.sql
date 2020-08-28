CREATE TABLE `category` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL UNIQUE,
	`url` varchar(100) NOT NULL UNIQUE,
	`articles` int(11) NOT NULL UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `article` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(256) NOT NULL UNIQUE,
	`url` varchar(256) NOT NULL UNIQUE,
	`logo` varchar(256) NOT NULL UNIQUE,
	`desc` varchar(256) NOT NULL,
	`content` TEXT NOT NULL,
	`created` TIMESTAMP NOT NULL,
	`views` int(11) NOT NULL,
	`comments` int(11) NOT NULL,
	`id_category` int(11) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `account` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`email` varchar(100) NOT NULL UNIQUE,
	`avatar` varchar(100) UNIQUE,
	`name` varchar(100) NOT NULL,
	`created` TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `comment` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`id_article` int(11) NOT NULL AUTO_INCREMENT,
	`id_account` int(11) NOT NULL AUTO_INCREMENT,
	`content` TEXT NOT NULL,
	`created` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `article` ADD CONSTRAINT `article_fk0` FOREIGN KEY (`id_category`) REFERENCES `category`(`id`);

ALTER TABLE `comment` ADD CONSTRAINT `comment_fk0` FOREIGN KEY (`id_article`) REFERENCES `article`(`id`);

ALTER TABLE `comment` ADD CONSTRAINT `comment_fk1` FOREIGN KEY (`id_account`) REFERENCES `account`(`id`);

