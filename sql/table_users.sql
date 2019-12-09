CREATE TABLE users(
		`user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
		`user_name` varchar(20) NOT NULL COMMENT '用户名',
		`user_password` varchar(20) NOT NULL COMMENT '密码',
		`user_age` int(11) DEFAULT NULL COMMENT '年龄',
		`user_gender` varchar(6) DEFAULT NULL COMMENT '性别',
		`user_phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
		`user_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
		`user_create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
		`user_last_login` DATE COMMENT '用户最后登录时间',
		`user_status` int(11) DEFAULT 0 COMMENT '用户权限，0为普通用户，1为管理员，2为超级管理员',
		`user_remark` text COMMENT '备注',
		`user_head_image` VARCHAR(128) DEFAULT NULL COMMENT '用户头像路径',
		PRIMARY KEY (`user_id`)
)ENGINE=INNODB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;