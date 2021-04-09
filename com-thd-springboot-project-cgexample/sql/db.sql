CREATE TABLE `cg_example` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `user_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `user_age` int(11) DEFAULT NULL COMMENT '年龄',
  `user_birthday` datetime DEFAULT NULL COMMENT '生日',
  `is_deleted` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成器测试';

CREATE TABLE `cg_test` (
  `user_id` varchar(64) NOT NULL COMMENT '主键',
  `user_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `user_age` int(11) DEFAULT NULL COMMENT '年龄',
  `user_birthday` date DEFAULT NULL COMMENT '生日',
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成器测试';

CREATE TABLE `my_plan` (
  `plan_id` varchar(64) NOT NULL,
  `title` varchar(64) DEFAULT NULL,
  `detail` varchar(128) DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `modify_by` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sys_user` (
  `user_id` varchar(64) NOT NULL COMMENT '主键',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(128) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` varchar(4) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

