
CREATE DATABASE `product` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `product_sku` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '规格名称',
  `thumb` varchar(255) NOT NULL DEFAULT '' COMMENT '规格图片链接',
  `price` decimal(12,2) NOT NULL COMMENT '售卖价格',
  `cost_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '成本价',
  `original_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '划线价格',
  `product_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商品编码 格式:XXXX-YYYYYYYY-ZZ',
  `product_sn` varchar(50) NOT NULL DEFAULT '' COMMENT '商品条形码',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `stock_warning` int(11) NOT NULL DEFAULT '0' COMMENT '库存预警',
  `sales_count` int(11) NOT NULL DEFAULT '0' COMMENT '销量',
  `weight` varchar(10) NOT NULL DEFAULT '' COMMENT '重量(千克)',
  `volume` varchar(10) NOT NULL DEFAULT '' COMMENT '体积(m³)',
  `spec_item_ids` varchar(128) DEFAULT NULL COMMENT '规格项id 多个值逗号分割 关联product_spec_item表主键ID',
  `display_order` smallint(4) NOT NULL DEFAULT '0' COMMENT '展示顺序 越小越靠前',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_shop_id_product_code` (`shop_id`,`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格信息表';


CREATE TABLE `product_spec_option` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '商品规格项名称',
  `display_order` smallint(4) NOT NULL DEFAULT '0' COMMENT '展示顺序 越小越靠前',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品规格项-名称表';

CREATE TABLE `product_spec_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `spec_id` int(11) unsigned NOT NULL COMMENT '商品规格项id 关联product_spec_option表主键ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '商品规格项取值',
  `display_order` smallint(4) NOT NULL DEFAULT '0' COMMENT '展示顺序 越小越靠前',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_spec_id` (`spec_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='商品规格项-取值表'