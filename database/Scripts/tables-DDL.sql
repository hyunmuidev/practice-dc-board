-- fake_dc.board definition

CREATE TABLE `board` (
  `id` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `created_by` varchar(16) NOT NULL,
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- fake_dc.post definition

CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `board_id` varchar(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `password` varchar(12) DEFAULT NULL,
  `post_type` enum('NORMAL','NOTICE') NOT NULL DEFAULT 'NORMAL',
  `view_count` int NOT NULL DEFAULT '0',
  `recommend_count` int NOT NULL DEFAULT '0',
  `unrecommend_count` int NOT NULL DEFAULT '0',
  `created_by` varchar(16) NOT NULL,
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `post_fk` (`board_id`),
  CONSTRAINT `post_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- fake_dc.reply definition

CREATE TABLE `reply` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` bigint NOT NULL,
  `created_by` varchar(16) NOT NULL,
  `password` varchar(12) DEFAULT NULL,
  `content` varchar(150) NOT NULL,
  `reply_status` enum('ACTIVATE','INACTIVATE') NOT NULL DEFAULT 'ACTIVATE',
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `reply_fk` (`post_id`),
  CONSTRAINT `reply_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;