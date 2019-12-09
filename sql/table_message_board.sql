CREATE TABLE `message_board`(
		`board_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言编号',
		`board_email` VARCHAR(50) NOT NULL COMMENT '邮箱',
		`board_nickname` VARCHAR(50) NOT NULL COMMENT '留言者',
		`board_advice` text COMMENT '留言内容',
		`board_upload_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '留言时间',
		PRIMARY KEY(`board_id`)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;