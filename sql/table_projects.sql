CREATE TABLE `projects`(
		`project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
		`project_description` varchar(128) NOT NULL COMMENT '项目描述',
		`project_time` DATE COMMENT '项目时间',
		`project_image_raw` varchar(128) NOT NULL COMMENT '项目原图',
		`project_image_compress` VARCHAR(128) NOT NULL COMMENT '项目压缩图',
		PRIMARY KEY (`project_id`)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;