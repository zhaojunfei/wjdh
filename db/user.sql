CREATE TABLE sys_user  ( 
	id      	int(10) UNSIGNED AUTO_INCREMENT NOT NULL,
	username 	varchar(100) COMMENT '登录名'  NOT NULL,
	password   	varchar(100) COMMENT '密码'  NOT NULL,
    company_name      	varchar(200) COMMENT '公司名称'  NULL,
	no         	varchar(100) COMMENT '工号'  NULL,
	name       	varchar(300)  character set utf8mb4 collate utf8mb4_unicode_ci default null,
	email      	varchar(200) COMMENT '邮箱'  NULL,
	phone      	varchar(200) COMMENT '电话'  NULL,
	mobile     	varchar(200) COMMENT '手机'  NULL,
	user_type  	char(1) COMMENT '用户类型'  NULL,
	photo      	varchar(1000) COMMENT '用户头像'  NULL,
	login_ip   	varchar(100) COMMENT '最后登陆IP'  NULL,
	login_date 	datetime COMMENT '最后登陆时间'  NULL,
	login_flag 	varchar(64) COMMENT '是否可登录'  NULL,
	create_date	datetime COMMENT '创建时间'  NOT NULL,
	update_date	datetime COMMENT '更新时间'  NOT NULL,
	remarks    	varchar(255) COMMENT '备注信息'  NULL,
	del_flag   	char(1) COMMENT '删除标记'  NOT NULL DEFAULT '0',
	PRIMARY KEY(id)
)COMMENT = '用户表'  default charset = utf8mb4;



CREATE TABLE sys_order  ( 
	id      	int(10) UNSIGNED AUTO_INCREMENT NOT NULL,
	user_id 	int(10) COMMENT '用户编号'  NOT NULL,
	source_name   	varchar(100) COMMENT '寄件人姓名'   NULL,
    source_tel      	varchar(200) COMMENT '寄件人电话'  NULL,
    source_address      	varchar(200) COMMENT '寄件人地址'  NULL,
    tar_name   	varchar(100) COMMENT '收件人姓名'   NULL,
    tar_tel      	varchar(200) COMMENT '收件人电话'  NULL,
    tar_address      	varchar(200) COMMENT '收件人地址'  NULL,
	req_time    varchar(100) COMMENT '取件时间'  NULL,
	name       	varchar(300) COMMENT '商品名称'  not null,
	tiji       	varchar(300) COMMENT '体积'   null,
	zhongliang      	varchar(200) COMMENT '重量'  NULL,
	pack      	varchar(200) COMMENT '包装方式'  NULL,
	status     	varchar(200) COMMENT '手机'  NULL,
	create_date	datetime COMMENT '创建时间'  NOT NULL,
	update_date	datetime COMMENT '更新时间'  NOT NULL,
	remarks    	varchar(255) COMMENT '备注信息'  NULL,
	del_flag   	char(1) COMMENT '删除标记'  NOT NULL DEFAULT '0',
	PRIMARY KEY(id)
)COMMENT = '订单信息表' ;