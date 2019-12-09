CREATE TABLE `users_awards`(
		`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关系编号',
		`user_id` int(11) NOT NULL COMMENT '用户id',
		`award_id` int(11) NOT NULL COMMENT '奖项id',
		PRIMARY KEY (`id`),
		FOREIGN KEY (`user_id`) REFERENCES users(`user_id`),
		FOREIGN KEY (`award_id`) REFERENCES awards(`award_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;