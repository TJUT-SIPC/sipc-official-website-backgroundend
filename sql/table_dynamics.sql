CREATE TABLE `dynamics`(
		`dynamic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态编号',
		`dynamic_image` VARCHAR(128) NOT NULL COMMENT '动态图片',
		`dynamic_header` VARCHAR(50) NOT NULL COMMENT '动态标题',
		`dynamic_text` text NOT NULL COMMENT '动态内容',
		`dynamic_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '动态修改时间',
		`dynamic_editor` VARCHAR(50) DEFAULT NULL COMMENT '发布者',
		`dynamic_catagory` int(11) DEFAULT 1 COMMENT '分类（待定）',
		PRIMARY KEY(`dynamic_id`)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
