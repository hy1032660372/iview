/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : spring_boot_test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-15 23:39:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_role`
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `id` varchar(32) NOT NULL,
  `account_id` varchar(32) NOT NULL,
  `role_code` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES ('123', '123', '199277');
INSERT INTO `account_role` VALUES ('1ff56186aa51437f974c862bdc5e5aac', '1127e2732906420e984b05f58b23390d', '1234561');
INSERT INTO `account_role` VALUES ('2748539b67344ad1a52d742241bfe11e', 'dffc0df6c94b4573bb7f351be712fee8', '199277');
INSERT INTO `account_role` VALUES ('305852381e3e4567bde69e095dd0db49', '16bc8d3317b54c23b53ad73b0c9de218', '123456');
INSERT INTO `account_role` VALUES ('be74bf31753847b38b23bbf56de80e46', 'c39ecacf3f474e498fb187c5bfe789b1', '1234561');
INSERT INTO `account_role` VALUES ('dd90a802b62b4700b00fb26fe4eaeced', 'e4aaba08531e4c52b7fc6bfe77b9f035', '199277');
INSERT INTO `account_role` VALUES ('e05a57a8cc2f4414ba3f1f4f9e7078de', 'b61b669506e040b08272e90042ed41ef', '199277');
INSERT INTO `account_role` VALUES ('f679822556834234aeeb538b04c132b8', '72316fdf058f412fa73f9ff2e6e3cbc1', '1234561');
INSERT INTO `account_role` VALUES ('f6f1f987e14f46bcbfb64bef101e1c33', '553674c2379c448b99479043651cdd99', '199277');
INSERT INTO `account_role` VALUES ('f9a38d00154842fb8f07fe72bf911d1d', '6be2fdc799754381853dd58353ab45cd', '1234561');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `permission_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('120', '199277', '120');
INSERT INTO `role_permission` VALUES ('121', '199277', '121');
INSERT INTO `role_permission` VALUES ('122', '199277', '122');
INSERT INTO `role_permission` VALUES ('123', '199277', '123');

-- ----------------------------
-- Table structure for `sys_account`
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` char(32) NOT NULL COMMENT ' 主键 id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('1127e2732906420e984b05f58b23390d', 'qqqq', '$2a$10$EwOMv809YAguV.c/jtU/hu0X.ohokSXOeSmL/4zyRY6J5JuQHZ1/e', '23');
INSERT INTO `sys_account` VALUES ('123', 'admin123', '$2a$10$WU6/5voJWTEwHERyj4SBVO.P3N0adFERUi56tzwVJuWI.CKK5QXo.', '999');
INSERT INTO `sys_account` VALUES ('6be2fdc799754381853dd58353ab45cd', 'ddddd', '$2a$10$/hOPRKioOePO6pBaCThAAeZ9ECxknen1Ng9zBHPf/vONxrUeIkDLK', '45');
INSERT INTO `sys_account` VALUES ('72316fdf058f412fa73f9ff2e6e3cbc1', 'lll', '$2a$10$URcrPTR9ZEntkUvY2BpUOe3GTSeXk2ZbcOKjjPUSjDArlyC8/UhBq', '23');
INSERT INTO `sys_account` VALUES ('c39ecacf3f474e498fb187c5bfe789b1', '223', '$2a$10$WU6/5voJWTEwHERyj4SBVO.P3N0adFERUi56tzwVJuWI.CKK5QXo.', '22');

-- ----------------------------
-- Table structure for `sys_custommenu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_custommenu`;
CREATE TABLE `sys_custommenu` (
  `id` varchar(32) NOT NULL,
  `title` varchar(50) NOT NULL,
  `menu_code` varchar(20) NOT NULL,
  `user_role` varchar(20) DEFAULT NULL,
  `menu_url` varchar(50) DEFAULT NULL,
  `expand` tinyint(4) DEFAULT NULL,
  `parent_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_custommenu
-- ----------------------------
INSERT INTO `sys_custommenu` VALUES ('9f18b876569d4476af77e0f45c63562b', 'fghjj', '1230', null, null, '1', '123');
INSERT INTO `sys_custommenu` VALUES ('aa', 'sss', '123', '123', '/user', '0', null);

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(32) NOT NULL,
  `permission_name` varchar(20) DEFAULT NULL,
  `permission_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('120', '查询用户', 'USER_SELECT');
INSERT INTO `sys_permission` VALUES ('121', '删除用户', 'USER_DELETE');
INSERT INTO `sys_permission` VALUES ('122', '编辑用户', 'USER_UPDATE');
INSERT INTO `sys_permission` VALUES ('123', '添加用户', 'USER_ADD');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `title` varchar(20) NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  `expand` tinyint(1) DEFAULT NULL,
  `parent_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('123456', 'adminA', '123456', '1', '199277');
INSERT INTO `sys_role` VALUES ('199277', 'superadmin', '199277', '1', '');
INSERT INTO `sys_role` VALUES ('223456', 'adminB', '223456', '1', '199277');
INSERT INTO `sys_role` VALUES ('24fc0d22a0f04ae19d6390dea86ac957', 'dddd', '2234560', '1', '223456');
INSERT INTO `sys_role` VALUES ('2b4b41da8a0546e3bfab579a1a2e2eb2', 'user2', '1234561', '1', '123456');

-- ----------------------------
-- Table structure for `wish`
-- ----------------------------
DROP TABLE IF EXISTS `wish`;
CREATE TABLE `wish` (
  `ID` char(32) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `UID` char(32) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT NULL,
  `COLOR` tinyint(1) DEFAULT NULL,
  `ADD_TIME` date DEFAULT NULL,
  `CONTENT` varchar(200) DEFAULT NULL,
  `POSITION_TOP` varchar(10) DEFAULT NULL,
  `POSITION_LEFT` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wish
-- ----------------------------
INSERT INTO `wish` VALUES ('02760f14f4f547f9a9659ba68abccb5c', 'try', null, null, '4', '2018-07-10', 'jghjgh[挤眼]', '154', '645');
INSERT INTO `wish` VALUES ('d0cfec98101348aa82d1ffc286fc5cfb', 'fffff', null, null, '4', '2018-07-10', 'ssssfsdf[酷]', null, null);
