CREATE TABLE `wishes`(
		`wish_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '寄语编号',
		`wish_name` VARCHAR(50) NOT NULL COMMENT '寄语发送者',
		`wish_word` text NOT NULL COMMENT '寄语内容',
		`wish_status` int(11) DEFAULT 1 COMMENT '0为审核不通过，1为待审核，2为审核通过待发布，3为已发布',
		PRIMARY KEY(`wish_id`)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;