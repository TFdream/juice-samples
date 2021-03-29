
CREATE DATABASE `member` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `es_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '注册手机号',
  `mobile_verified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '手机号是否实名',
  `nickname` varchar(100) NOT NULL DEFAULT '' COMMENT '会员昵称',
  `realname` varchar(20) NOT NULL DEFAULT '' COMMENT '会员真实姓名',
  `sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别 0:男 1:女 -1:未知',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '会员图像',
  `inviter` bigint(20) NOT NULL DEFAULT '0' COMMENT '注册邀请人',
  `is_black` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否黑名单用户 0:否 1:是',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '登录密码',
  `salt` varchar(32) NOT NULL DEFAULT '' COMMENT '登录密码-盐值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员基础信息表';

CREATE TABLE `es_member_ext` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺ID',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员ID',
  `level` smallint(4) NOT NULL COMMENT '会员等级，关联es_member_level表level',
  `level_id` int(11) unsigned NOT NULL COMMENT '会员等级ID',
  `is_trial` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否为试用等级 0:否 1:是',
  `credit_total` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '累计会员积分',
  `credit_available` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '当前可用会员积分',
  `balance` decimal(12,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '余额',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `enable_pay_pwd` tinyint(1) NOT NULL DEFAULT '0' COMMENT '开启支付密码',
  `pay_password` varchar(255) NOT NULL DEFAULT '' COMMENT '支付密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员扩展信息表';

CREATE TABLE `es_member_level` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int(11) NOT NULL COMMENT '店铺ID',
  `level` smallint(4) NOT NULL COMMENT '会员等级',
  `name` varchar(12) NOT NULL DEFAULT '' COMMENT '会员等级名称',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '会员等级描述',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shop_id_level` (`shop_id`, `level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员等级表';