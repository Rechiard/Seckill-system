/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : e-commerce

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-04-22 19:40:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for delivery_addr_info
-- ----------------------------
DROP TABLE IF EXISTS `delivery_addr_info`;
CREATE TABLE `delivery_addr_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID，（电话号码）',
  `user_delivery_addr` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户地址',
  `user_nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系人',
  `user_mobile` bigint NOT NULL COMMENT '手机号码',
  `status` int DEFAULT '1' COMMENT '状态，1为正常，2为删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `delivery_addr_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of delivery_addr_info
-- ----------------------------
INSERT INTO `delivery_addr_info` VALUES ('1', '17622736441', '江西省南昌市', '波波', '17622736441', '2');
INSERT INTO `delivery_addr_info` VALUES ('2', '13576005583', '江西省高安市', '徐雨柔', '13576005583', '1');
INSERT INTO `delivery_addr_info` VALUES ('6', '13576005583', '天津师范大学', '龚剑波', '1', '1');
INSERT INTO `delivery_addr_info` VALUES ('7', '13576005583', '1', '1', '1', '2');
INSERT INTO `delivery_addr_info` VALUES ('8', '13576005583', '1', '1', '1', '2');
INSERT INTO `delivery_addr_info` VALUES ('9', '13576005583', '2', '2', '2', '2');
INSERT INTO `delivery_addr_info` VALUES ('10', '13576005583', '3', '3', '3', '2');
INSERT INTO `delivery_addr_info` VALUES ('11', '13576005583', '3', '1', '23', '2');
INSERT INTO `delivery_addr_info` VALUES ('12', '13576005583', '14123', '14', '14', '2');
INSERT INTO `delivery_addr_info` VALUES ('13', '13576005583', '312', '23', '1321', '2');
INSERT INTO `delivery_addr_info` VALUES ('14', '17622736441', '江西省南昌市123', '龚剑波', '17622736441', '1');
INSERT INTO `delivery_addr_info` VALUES ('15', '15970583982', '江西省南昌市西湖区', '龚剑波', '15970583982', '1');
INSERT INTO `delivery_addr_info` VALUES ('16', '13970583982', '江西省南昌', '龚剑波', '13576005583', '1');
INSERT INTO `delivery_addr_info` VALUES ('17', '17622736441', '江西省南昌123', '龚剑波123', '17622736441', '2');

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品名称',
  `goods_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品图片',
  `goods_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '商品详情',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
  `goods_type` int DEFAULT NULL COMMENT '商品类型：1电视机，2录像机，3手机，4电子平板，5电脑，6游戏机，7收音机，8投影仪',
  `goods_stock` int DEFAULT NULL COMMENT '商品库存',
  `create_time` datetime DEFAULT NULL COMMENT '商品上架时间',
  `status` int DEFAULT '1' COMMENT '状态，1为正常，2为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES ('1', 'IPHONE 12', 'https://img.alicdn.com/imgextra/i3/45646959/O1CN015tbdzT21HGvkuA3rv_!!0-saturn_solar.jpg_468x468q75.jpg_.webp', '春节正常发货【24期免息】Apple/苹果 iPhone 13苹果13手机5G版苹果官方旗舰店正品iPhone12官网13promax5g新', '6388.00', '3', '96', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('2', '小米12', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/725677994/O1CN01ZbqI4p28vIsyuZyT6_!!0-item_pic.jpg_250x250.jpg_.webp', '当天发【24期免息赠碎屏险】Xiaomi/小米 小米12 5G智能手机小米官方旗舰官网正品', '3699.00', '3', '9996', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('3', 'Hinsense电视机', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/17398353/O1CN01BukbpD2BZj4ZxL1yo_!!0-saturn_solar.jpg_250x250.jpg_.webp', 'Hisense/海信 42E2F 42英寸高清智能WIFI网络平板液晶电视43', '1499.00', '1', '100', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('4', '萤石C6C无线网络摄像头', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/1991926764/O1CN01Evv7e51zpxiv0FDoA_!!0-item_pic.jpg_250x250.jpg_.webp', '萤石C6C无线网络摄像头360全景家用手机远程监控高清夜视看护宠物', '219.00', '2', '100', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('5', 'IPAD 2021', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/113823083/O1CN014b4PZu1Ye3nJndTfV_!!0-saturn_solar.jpg_250x250.jpg_.webp', 'Apple/苹果 10.2 英寸 iPad (第九代) 2021新款iPad9代平板电脑', '2498.00', '4', '100', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('6', '雷神笔记本电脑', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i4/59466999/O1CN01vebhHJ21ZamKJkr1Z_!!0-saturn_solar.jpg_250x250.jpg_.webp', '【性价比推荐】雷神2021年新品911MT 11代酷睿i7-11800H笔记本电脑电竞游戏本手提15.6英寸 4G独显RTX3050Ti', '5899.00', '5', '100', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('7', 'SWITCH OPED', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp', '任天堂Switch NS主机 Lite游戏掌机 续航加强版 新型OLED日版国行', '1359.00', '6', '100', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('8', 'SAST/先科T-50收音机', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/31560215/O1CN018sHXVm1DSVnvIlzDZ_!!0-saturn_solar.jpg_250x250.jpg_.webp', 'SAST/先科T-50收音机老年老人迷你小音响插卡小音箱小型新款便携式播放器随身听mp3可充电唱戏机音乐听戏评书', '48.00', '7', '100', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('9', '华为投影仪', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/1521720142/O1CN014KuByi1Cv4yZ2OADB_!!0-saturn_solar.jpg_250x250.jpg_.webp', '2022新款投影仪4K超高清家用家庭影院小型便携护眼投墙手机智能办公一体机卧室宿舍1080P投影机适用华为小米', '598.00', '8', '100', '2022-01-27 11:49:44', '1');
INSERT INTO `goods_info` VALUES ('12', 'Huawei/华为 Mate', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/114187804/O1CN01v38mF227WHdSGNDOm_!!0-saturn_solar.jpg_250x250.jpg_.webp', 'Huawei/华为 Mate 20全网通4gMate20pro曲面屏 mate10 pro手机', '930.00', '3', '100', '2022-03-16 18:46:31', '1');
INSERT INTO `goods_info` VALUES ('14', '1', '1', '1', '1.00', '6', '1', null, '2');
INSERT INTO `goods_info` VALUES ('15', '123', '123', '1', '1.00', '2', '1', null, '2');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `goods_id` bigint NOT NULL COMMENT '商品ID',
  `goods_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品名称',
  `goods_count` int NOT NULL DEFAULT '0' COMMENT '商品数量',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `delivery_addr_id` bigint DEFAULT NULL COMMENT '收货地址ID',
  `create_time` datetime NOT NULL COMMENT '订单的创建时间',
  `status` int DEFAULT '1' COMMENT '状态，1为正常，2为删除',
  `goods_capacity` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品的内存容量',
  `goods_img` varchar(255) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `2` (`goods_id`),
  CONSTRAINT `1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `2` FOREIGN KEY (`goods_id`) REFERENCES `goods_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('15', '17622736441', '8', 'SAST/先科T-50收音机', '1', '48.00', '14', '2022-02-03 15:42:09', '3', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/31560215/O1CN018sHXVm1DSVnvIlzDZ_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('16', '17622736441', '3', 'Hinsense电视机', '1', '1499.00', '14', '2022-02-03 15:48:35', '3', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/17398353/O1CN01BukbpD2BZj4ZxL1yo_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('17', '13576005583', '1', 'IPHONE 12', '1', '6388.00', '6', '2022-02-04 15:33:15', '2', '32GB', 'https://img.alicdn.com/imgextra/i3/45646959/O1CN015tbdzT21HGvkuA3rv_!!0-saturn_solar.jpg_468x468q75.jpg_.webp');
INSERT INTO `order_info` VALUES ('18', '13576005583', '1', 'IPHONE 12', '1', '6388.00', '6', '2022-02-18 21:00:02', '3', '32GB', 'https://img.alicdn.com/imgextra/i3/45646959/O1CN015tbdzT21HGvkuA3rv_!!0-saturn_solar.jpg_468x468q75.jpg_.webp');
INSERT INTO `order_info` VALUES ('20', '17622736441', '1', 'IPHONE 12', '1', '6388.00', '14', '2022-02-21 21:00:10', '3', '32GB', 'https://img.alicdn.com/imgextra/i3/45646959/O1CN015tbdzT21HGvkuA3rv_!!0-saturn_solar.jpg_468x468q75.jpg_.webp');
INSERT INTO `order_info` VALUES ('21', '17622736441', '5', 'IPAD 2021', '1', '2498.00', '14', '2022-03-03 00:15:22', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/113823083/O1CN014b4PZu1Ye3nJndTfV_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('22', '17622736441', '3', 'Hinsense电视机', '1', '1499.00', '14', '2022-03-03 00:17:10', '3', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/17398353/O1CN01BukbpD2BZj4ZxL1yo_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('23', '17622736441', '7', 'SWITCH OPED', '1', '1359.00', null, '2022-03-19 20:58:13', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('24', '17622736441', '5', 'IPAD 2021', '1', '2498.00', null, '2022-03-19 20:58:19', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/113823083/O1CN014b4PZu1Ye3nJndTfV_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('25', '17622736441', '7', 'SWITCH OPED', '1', '1359.00', null, '2022-03-19 21:02:28', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('26', '17622736441', '7', 'SWITCH OPED', '1', '1359.00', null, '2022-03-19 21:02:29', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('27', '17622736441', '7', 'SWITCH OPED', '1', '1359.00', '14', '2022-03-19 21:02:39', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('28', '17622736441', '3', 'Hinsense电视机', '1', '1499.00', '14', '2022-03-19 21:08:26', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/17398353/O1CN01BukbpD2BZj4ZxL1yo_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('29', '17622736441', '5', 'IPAD 2021', '3', '2498.00', '14', '2022-03-19 21:08:34', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/113823083/O1CN014b4PZu1Ye3nJndTfV_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('30', '15970583982', '5', 'IPAD 2021', '4', '2498.00', '15', '2022-03-23 15:59:06', '1', '256GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/113823083/O1CN014b4PZu1Ye3nJndTfV_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('31', '15970583982', '7', 'SWITCH OPED', '2', '1359.00', '15', '2022-03-23 15:59:16', '1', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('32', '15970583982', '1', 'IPHONE 12', '1', '5388.00', null, '2022-03-23 15:59:50', '2', '32GB', 'https://img.alicdn.com/imgextra/i3/45646959/O1CN015tbdzT21HGvkuA3rv_!!0-saturn_solar.jpg_468x468q75.jpg_.webp');
INSERT INTO `order_info` VALUES ('33', '13970583982', '5', 'IPAD 2021', '2', '2498.00', null, '2022-03-31 14:00:38', '2', '128GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/113823083/O1CN014b4PZu1Ye3nJndTfV_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('34', '13970583982', '7', 'SWITCH OPED', '2', '1359.00', '16', '2022-03-31 14:00:45', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('35', '13970583982', '1', 'IPHONE 12', '1', '5388.00', null, '2022-03-31 14:01:39', '2', '32GB', 'https://img.alicdn.com/imgextra/i3/45646959/O1CN015tbdzT21HGvkuA3rv_!!0-saturn_solar.jpg_468x468q75.jpg_.webp');
INSERT INTO `order_info` VALUES ('36', '17622736441', '1', 'IPHONE 12', '3', '6388.00', null, '2022-04-18 14:29:56', '2', '32GB', 'https://img.alicdn.com/imgextra/i3/45646959/O1CN015tbdzT21HGvkuA3rv_!!0-saturn_solar.jpg_468x468q75.jpg_.webp');
INSERT INTO `order_info` VALUES ('37', '17622736441', '7', 'SWITCH OPED', '2', '1359.00', '14', '2022-04-18 14:30:07', '2', '256GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('38', '17622736441', '2', '小米12', '1', '2999.00', '14', '2022-04-18 14:31:41', '2', '32GB', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/725677994/O1CN01ZbqI4p28vIsyuZyT6_!!0-item_pic.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('39', '13576005583', '2', '小米12', '1', '2999.00', '2', '2022-04-18 14:36:55', '3', '32GB', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/725677994/O1CN01ZbqI4p28vIsyuZyT6_!!0-item_pic.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('40', '17622736441', '5', 'IPAD 2021', '2', '2498.00', '14', '2022-04-21 11:21:23', '2', '64GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/113823083/O1CN014b4PZu1Ye3nJndTfV_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('41', '17622736441', '3', 'Hinsense电视机', '3', '1499.00', null, '2022-04-21 11:22:44', '2', '32GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/17398353/O1CN01BukbpD2BZj4ZxL1yo_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('42', '17622736441', '1', 'IPHONE 12', '2', '6388.00', '14', '2022-04-21 11:23:35', '2', '128GB', 'https://img.alicdn.com/imgextra/i3/45646959/O1CN015tbdzT21HGvkuA3rv_!!0-saturn_solar.jpg_468x468q75.jpg_.webp');
INSERT INTO `order_info` VALUES ('43', '17622736441', '2', '小米12', '2', '3699.00', '14', '2022-04-21 11:25:20', '2', '128GB', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/725677994/O1CN01ZbqI4p28vIsyuZyT6_!!0-item_pic.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('44', '17622736441', '3', 'Hinsense电视机', '2', '1499.00', '14', '2022-04-21 11:27:01', '2', '128GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/17398353/O1CN01BukbpD2BZj4ZxL1yo_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('45', '13576005583', '2', '小米12', '1', '2999.00', null, '2022-04-21 11:30:30', '1', '32GB', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/725677994/O1CN01ZbqI4p28vIsyuZyT6_!!0-item_pic.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('46', '17622736441', '5', 'IPAD 2021', '2', '2498.00', '14', '2022-04-21 11:30:55', '1', '128GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/113823083/O1CN014b4PZu1Ye3nJndTfV_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('47', '17622736441', '7', 'SWITCH OPED', '2', '1359.00', '14', '2022-04-21 11:31:59', '1', '64GB', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/30556915/O1CN012oBCZb20x7WMMQ2v1_!!0-saturn_solar.jpg_250x250.jpg_.webp');
INSERT INTO `order_info` VALUES ('48', '17622736441', '2', '小米12', '1', '2999.00', '14', '2022-04-21 11:32:25', '1', '32GB', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/725677994/O1CN01ZbqI4p28vIsyuZyT6_!!0-item_pic.jpg_250x250.jpg_.webp');

-- ----------------------------
-- Table structure for seckill_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `seckill_goods_info`;
CREATE TABLE `seckill_goods_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '秒杀',
  `goods_id` bigint DEFAULT NULL COMMENT '商品ID',
  `seckill_price` decimal(10,2) DEFAULT NULL COMMENT '秒杀价格',
  `start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `status` int DEFAULT NULL COMMENT '状态，1位正常，2为删除',
  PRIMARY KEY (`id`),
  KEY `112` (`goods_id`),
  CONSTRAINT `112` FOREIGN KEY (`goods_id`) REFERENCES `goods_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of seckill_goods_info
-- ----------------------------
INSERT INTO `seckill_goods_info` VALUES ('1', '1', '5388.00', '2022-04-21 12:10:03', '2022-04-21 12:10:03', '1');
INSERT INTO `seckill_goods_info` VALUES ('2', '2', '2999.00', '2022-04-21 08:10:03', '2022-04-21 18:10:03', '1');
INSERT INTO `seckill_goods_info` VALUES ('3', '3', '999.00', '2022-04-17 13:40:04', '2022-04-18 13:40:04', '1');
INSERT INTO `seckill_goods_info` VALUES ('4', '4', '199.00', '2022-03-23 15:45:21', '2022-03-25 14:06:31', '1');
INSERT INTO `seckill_goods_info` VALUES ('5', '5', '2100.00', '2022-03-23 15:45:21', '2022-03-25 14:06:31', '1');

-- ----------------------------
-- Table structure for seckill_order_info
-- ----------------------------
DROP TABLE IF EXISTS `seckill_order_info`;
CREATE TABLE `seckill_order_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '秒杀订单ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `goods_id` bigint DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`),
  KEY `12` (`user_id`),
  KEY `22` (`order_id`),
  KEY `32` (`goods_id`),
  CONSTRAINT `12` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `22` FOREIGN KEY (`order_id`) REFERENCES `order_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `32` FOREIGN KEY (`goods_id`) REFERENCES `goods_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of seckill_order_info
-- ----------------------------
INSERT INTO `seckill_order_info` VALUES ('7', '13576005583', '45', '2');
INSERT INTO `seckill_order_info` VALUES ('8', '17622736441', '48', '2');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint NOT NULL COMMENT '用户ID，手机号码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '盐值',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `status` int DEFAULT '1' COMMENT '状态，1为成功，2为删除',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱信息',
  `role` int DEFAULT NULL COMMENT '1为用户，2为管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('13576005583', '龚剑波', 'e53d30b06d4d0fb2e0d0bd6412e66440', '1a2b3c4d', '2022-01-27 20:28:24', '1', '616316004@qq.com', '1');
INSERT INTO `user_info` VALUES ('13576005584', '柔柔', 'e53d30b06d4d0fb2e0d0bd6412e66440', '1a2b3c4d', '2022-01-28 11:36:06', '1', '17622736441@qq.com', '2');
INSERT INTO `user_info` VALUES ('13970583982', '波波', 'bd8cfcfa579981dc737d1a313fb13e17', '1a2b3c4d', '2022-03-31 13:59:31', '1', 'bobo616316004@gmail.com', '1');
INSERT INTO `user_info` VALUES ('15970583982', '龚剑波5156156156', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', '2022-03-23 15:58:01', '1', null, '1');
INSERT INTO `user_info` VALUES ('17622736441', '波波', 'e53d30b06d4d0fb2e0d0bd6412e66440', '1a2b3c4d', '2022-01-28 11:36:06', '1', '17622736441@qq.com', '1');
