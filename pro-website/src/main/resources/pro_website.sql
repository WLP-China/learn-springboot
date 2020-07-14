/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : pro_website

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 14/07/2020 10:46:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `css` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `href` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '用户管理', 'fa-users', '', 1, '', 3);
INSERT INTO `sys_permission` VALUES (2, 1, '用户查询', 'fa-user', 'pages/user/userList.html', 1, '', 1);
INSERT INTO `sys_permission` VALUES (3, 2, '查询', '', '', 2, 'sys:user:query', 100);
INSERT INTO `sys_permission` VALUES (4, 2, '新增', '', '', 2, 'sys:user:add', 100);
INSERT INTO `sys_permission` VALUES (5, 0, '修改密码', 'fa-pencil-square-o', 'pages/user/changePassword.html', 1, 'sys:user:password', 11);
INSERT INTO `sys_permission` VALUES (7, 0, '系统设置', 'fa-gears', '', 1, '', 13);
INSERT INTO `sys_permission` VALUES (8, 7, '菜单', 'fa-cog', 'pages/menu/menuList.html', 1, '', 1);
INSERT INTO `sys_permission` VALUES (9, 8, '查询', '', '', 2, 'sys:menu:query', 100);
INSERT INTO `sys_permission` VALUES (10, 8, '新增', '', '', 2, 'sys:menu:add', 100);
INSERT INTO `sys_permission` VALUES (11, 8, '删除', '', '', 2, 'sys:menu:del', 100);
INSERT INTO `sys_permission` VALUES (12, 7, '角色', 'fa-user-secret', 'pages/role/roleList.html', 1, '', 2);
INSERT INTO `sys_permission` VALUES (13, 12, '查询', '', '', 2, 'sys:role:query', 100);
INSERT INTO `sys_permission` VALUES (14, 12, '新增', '', '', 2, 'sys:role:add', 100);
INSERT INTO `sys_permission` VALUES (15, 12, '删除', '', '', 2, 'sys:role:del', 100);
INSERT INTO `sys_permission` VALUES (16, 0, '字典管理', 'fa-reddit', 'pages/dict/dictList.html', 1, '', 9);
INSERT INTO `sys_permission` VALUES (17, 16, '查询', '', '', 2, 'dict:query', 100);
INSERT INTO `sys_permission` VALUES (18, 16, '新增', '', '', 2, 'dict:add', 100);
INSERT INTO `sys_permission` VALUES (19, 16, '删除', '', '', 2, 'dict:del', 100);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ADMIN', '超级管理员', '2020-05-17 23:20:29', '2020-07-11 23:25:09');
INSERT INTO `sys_role` VALUES (2, 'USER', '', '2017-08-01 21:47:31', '2020-07-11 23:25:15');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`, `permissionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 19);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`, `roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1);
INSERT INTO `sys_role_user` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$iYM/H7TrSaLs7XyIWQdGwe1xf4cdmt3nwMja6RT0wxG5YY1RjN0EK', '管理员', NULL, 1, '2017-04-10 15:21:38', '2020-05-17 23:21:29');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `k` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type`(`type`, `k`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES (1, 'boolean', '0', '否', '2020-06-30 10:47:29', '2020-06-30 10:47:29');
INSERT INTO `t_dict` VALUES (2, 'boolean', '1', '是', '2020-06-30 10:48:01', '2020-06-30 10:48:01');
INSERT INTO `t_dict` VALUES (3, 'userStatus', '0', '无效', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (4, 'userStatus', '1', '正常', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (5, 'userStatus', '2', '锁定', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (6, 'noticeStatus', '0', '草稿', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (7, 'noticeStatus', '1', '发布', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (8, 'isRead', '0', '未读', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (9, 'isRead', '1', '已读', '2017-11-17 16:26:06', '2017-11-17 16:26:09');

-- ----------------------------
-- Table structure for t_token
-- ----------------------------
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `val` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'LoginUser的json串',
  `expireTime` datetime(0) NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_token
-- ----------------------------
INSERT INTO `t_token` VALUES ('73d923f1-cc42-4835-82c1-6106ac31a1e8', '{\"accountNonExpired\":true,\"accountNonLocked\":true,\"authorities\":[{\"authority\":\"sys:role:del\"},{\"authority\":\"dict:query\"},{\"authority\":\"dict:del\"},{\"authority\":\"sys:menu:del\"},{\"authority\":\"sys:user:password\"},{\"authority\":\"sys:user:query\"},{\"authority\":\"sys:menu:add\"},{\"authority\":\"sys:user:add\"},{\"authority\":\"sys:role:query\"},{\"authority\":\"sys:role:add\"},{\"authority\":\"sys:menu:query\"},{\"authority\":\"dict:add\"}],\"createTime\":1491808898000,\"credentialsNonExpired\":true,\"enabled\":true,\"expireTime\":1594765917702,\"id\":1,\"loginTime\":1594693917702,\"name\":\"管理员\",\"password\":\"$2a$10$iYM/H7TrSaLs7XyIWQdGwe1xf4cdmt3nwMja6RT0wxG5YY1RjN0EK\",\"permissions\":[{\"createTime\":1594693917611,\"css\":\"fa-cog\",\"href\":\"pages/menu/menuList.html\",\"id\":8,\"name\":\"菜单\",\"parentId\":7,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1594693917611},{\"createTime\":1594693917611,\"css\":\"fa-user\",\"href\":\"pages/user/userList.html\",\"id\":2,\"name\":\"用户查询\",\"parentId\":1,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1594693917611},{\"createTime\":1594693917611,\"css\":\"fa-user-secret\",\"href\":\"pages/role/roleList.html\",\"id\":12,\"name\":\"角色\",\"parentId\":7,\"permission\":\"\",\"sort\":2,\"type\":1,\"updateTime\":1594693917611},{\"createTime\":1594693917612,\"css\":\"fa-users\",\"href\":\"\",\"id\":1,\"name\":\"用户管理\",\"parentId\":0,\"permission\":\"\",\"sort\":3,\"type\":1,\"updateTime\":1594693917612},{\"createTime\":1594693917612,\"css\":\"fa-reddit\",\"href\":\"pages/dict/dictList.html\",\"id\":16,\"name\":\"字典管理\",\"parentId\":0,\"permission\":\"\",\"sort\":9,\"type\":1,\"updateTime\":1594693917612},{\"createTime\":1594693917612,\"css\":\"fa-pencil-square-o\",\"href\":\"pages/user/changePassword.html\",\"id\":5,\"name\":\"修改密码\",\"parentId\":0,\"permission\":\"sys:user:password\",\"sort\":11,\"type\":1,\"updateTime\":1594693917612},{\"createTime\":1594693917612,\"css\":\"fa-gears\",\"href\":\"\",\"id\":7,\"name\":\"系统设置\",\"parentId\":0,\"permission\":\"\",\"sort\":13,\"type\":1,\"updateTime\":1594693917612},{\"createTime\":1594693917612,\"css\":\"\",\"href\":\"\",\"id\":17,\"name\":\"查询\",\"parentId\":16,\"permission\":\"dict:query\",\"sort\":100,\"type\":2,\"updateTime\":1594693917612},{\"createTime\":1594693917613,\"css\":\"\",\"href\":\"\",\"id\":11,\"name\":\"删除\",\"parentId\":8,\"permission\":\"sys:menu:del\",\"sort\":100,\"type\":2,\"updateTime\":1594693917613},{\"createTime\":1594693917613,\"css\":\"\",\"href\":\"\",\"id\":15,\"name\":\"删除\",\"parentId\":12,\"permission\":\"sys:role:del\",\"sort\":100,\"type\":2,\"updateTime\":1594693917613},{\"createTime\":1594693917613,\"css\":\"\",\"href\":\"\",\"id\":10,\"name\":\"新增\",\"parentId\":8,\"permission\":\"sys:menu:add\",\"sort\":100,\"type\":2,\"updateTime\":1594693917613},{\"createTime\":1594693917613,\"css\":\"\",\"href\":\"\",\"id\":19,\"name\":\"删除\",\"parentId\":16,\"permission\":\"dict:del\",\"sort\":100,\"type\":2,\"updateTime\":1594693917613},{\"createTime\":1594693917613,\"css\":\"\",\"href\":\"\",\"id\":4,\"name\":\"新增\",\"parentId\":2,\"permission\":\"sys:user:add\",\"sort\":100,\"type\":2,\"updateTime\":1594693917613},{\"createTime\":1594693917613,\"css\":\"\",\"href\":\"\",\"id\":14,\"name\":\"新增\",\"parentId\":12,\"permission\":\"sys:role:add\",\"sort\":100,\"type\":2,\"updateTime\":1594693917613},{\"createTime\":1594693917613,\"css\":\"\",\"href\":\"\",\"id\":9,\"name\":\"查询\",\"parentId\":8,\"permission\":\"sys:menu:query\",\"sort\":100,\"type\":2,\"updateTime\":1594693917613},{\"createTime\":1594693917614,\"css\":\"\",\"href\":\"\",\"id\":18,\"name\":\"新增\",\"parentId\":16,\"permission\":\"dict:add\",\"sort\":100,\"type\":2,\"updateTime\":1594693917614},{\"createTime\":1594693917616,\"css\":\"\",\"href\":\"\",\"id\":3,\"name\":\"查询\",\"parentId\":2,\"permission\":\"sys:user:query\",\"sort\":100,\"type\":2,\"updateTime\":1594693917616},{\"createTime\":1594693917616,\"css\":\"\",\"href\":\"\",\"id\":13,\"name\":\"查询\",\"parentId\":12,\"permission\":\"sys:role:query\",\"sort\":100,\"type\":2,\"updateTime\":1594693917616}],\"status\":1,\"token\":\"73d923f1-cc42-4835-82c1-6106ac31a1e8\",\"updateTime\":1589728889000,\"username\":\"admin\"}', '2020-07-15 06:31:58', '2020-07-14 10:31:58', '2020-07-14 10:31:58');

SET FOREIGN_KEY_CHECKS = 1;
