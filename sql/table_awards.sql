CREATE TABLE `awards`(
		`award_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '获奖编号',
		`award_name` varchar(50) NOT NULL COMMENT '获奖名称',
		`award_time` DATE COMMENT '获奖时间',
		PRIMARY KEY(`award_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;