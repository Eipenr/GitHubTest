/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : db_shopping

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 13/09/2021 15:21:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail` text,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_address_id` (`uid`) USING BTREE,
  CONSTRAINT `fk_address_id` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart` (
  `id` int(11) NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`pid`) USING BTREE,
  KEY `fk_cart_pid` (`pid`) USING BTREE,
  CONSTRAINT `fk_cart_id` FOREIGN KEY (`id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `fk_cart_pid` FOREIGN KEY (`pid`) REFERENCES `tb_goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kzid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_goods_kz
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_kz`;
CREATE TABLE `tb_goods_kz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `pubdate` date DEFAULT NULL,
  `picture` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `star` tinyint(4) NOT NULL DEFAULT '0',
  `intro` text,
  `typeid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_typeid` (`typeid`) USING BTREE,
  CONSTRAINT `fk_typeid` FOREIGN KEY (`typeid`) REFERENCES `tb_goods_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_type`;
CREATE TABLE `tb_goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `Parent` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '状态,0是删除,1 是正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` varchar(100) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_order_uid` (`uid`) USING BTREE,
  KEY `fk_order_aid` (`aid`) USING BTREE,
  CONSTRAINT `fk_order_aid` FOREIGN KEY (`aid`) REFERENCES `tb_address` (`id`),
  CONSTRAINT `fk_order_uid` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderdetail`;
CREATE TABLE `tb_orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` varchar(100) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_order_pid` (`pid`) USING BTREE,
  KEY `fk_order_id` (`oid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `email` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `flag` int(11) DEFAULT '0',
  `role` int(11) DEFAULT '1',
  `code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `nameuni` (`username`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
