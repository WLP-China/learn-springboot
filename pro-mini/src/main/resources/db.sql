/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 18/08/2020 10:04:12
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
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 5);

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$dqV5omL.uFKaW/q7FhxGs.cW5SLmFY3zxH7Y7h4ZoPT9LG9z3DPNG', '超级管理员', '', 1, '2017-04-10 15:21:38', '2020-05-17 23:21:29');
INSERT INTO `sys_user` VALUES (2, 'user', '$2a$10$ooGb4wjT7Hg3zgU2RhZp6eVu3jvG29i/U4L6VRwiZZ4.DZ0OOEAHu', '管理员', '', 1, '2017-08-01 21:47:18', '2017-08-01 21:47:18');

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES (1, 'boolean', '0', '否', '2020-06-30 10:47:29', '2020-06-30 10:47:29');
INSERT INTO `t_dict` VALUES (2, 'boolean', '1', '是', '2020-06-30 10:48:01', '2020-06-30 10:48:01');
INSERT INTO `t_dict` VALUES (3, 'userStatus', '0', '无效', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (4, 'userStatus', '1', '正常', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (5, 'userStatus', '2', '锁定', '2017-11-17 16:26:06', '2017-11-17 16:26:09');

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
INSERT INTO `t_token` VALUES ('23484f6e-c376-411c-9e1f-4b425c9763c9', '{\"accountNonExpired\":true,\"accountNonLocked\":true,\"authorities\":[{\"authority\":\"sys:role:del\"},{\"authority\":\"dict:del\"},{\"authority\":\"dict:query\"},{\"authority\":\"sys:menu:del\"},{\"authority\":\"sys:user:password\"},{\"authority\":\"sys:user:query\"},{\"authority\":\"sys:menu:add\"},{\"authority\":\"sys:user:add\"},{\"authority\":\"sys:role:query\"},{\"authority\":\"sys:role:add\"},{\"authority\":\"sys:menu:query\"},{\"authority\":\"dict:add\"}],\"createTime\":1491808898000,\"credentialsNonExpired\":true,\"enabled\":true,\"expireTime\":1597787866718,\"id\":1,\"loginTime\":1597715866718,\"name\":\"超级管理员\",\"password\":\"$2a$10$dqV5omL.uFKaW/q7FhxGs.cW5SLmFY3zxH7Y7h4ZoPT9LG9z3DPNG\",\"permissions\":[{\"createTime\":1597715866632,\"css\":\"fa-cog\",\"href\":\"pages/menu/menuList.html\",\"id\":8,\"name\":\"菜单\",\"parentId\":7,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1597715866632},{\"createTime\":1597715866632,\"css\":\"fa-user\",\"href\":\"pages/user/userList.html\",\"id\":2,\"name\":\"用户查询\",\"parentId\":1,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1597715866632},{\"createTime\":1597715866632,\"css\":\"fa-user-secret\",\"href\":\"pages/role/roleList.html\",\"id\":12,\"name\":\"角色\",\"parentId\":7,\"permission\":\"\",\"sort\":2,\"type\":1,\"updateTime\":1597715866632},{\"createTime\":1597715866633,\"css\":\"fa-users\",\"href\":\"\",\"id\":1,\"name\":\"用户管理\",\"parentId\":0,\"permission\":\"\",\"sort\":3,\"type\":1,\"updateTime\":1597715866633},{\"createTime\":1597715866633,\"css\":\"fa-reddit\",\"href\":\"pages/dict/dictList.html\",\"id\":16,\"name\":\"字典管理\",\"parentId\":0,\"permission\":\"\",\"sort\":9,\"type\":1,\"updateTime\":1597715866633},{\"createTime\":1597715866633,\"css\":\"fa-pencil-square-o\",\"href\":\"pages/user/changePassword.html\",\"id\":5,\"name\":\"修改密码\",\"parentId\":0,\"permission\":\"sys:user:password\",\"sort\":11,\"type\":1,\"updateTime\":1597715866633},{\"createTime\":1597715866633,\"css\":\"fa-gears\",\"href\":\"\",\"id\":7,\"name\":\"系统设置\",\"parentId\":0,\"permission\":\"\",\"sort\":13,\"type\":1,\"updateTime\":1597715866633},{\"createTime\":1597715866634,\"css\":\"\",\"href\":\"\",\"id\":11,\"name\":\"删除\",\"parentId\":8,\"permission\":\"sys:menu:del\",\"sort\":100,\"type\":2,\"updateTime\":1597715866634},{\"createTime\":1597715866634,\"css\":\"\",\"href\":\"\",\"id\":15,\"name\":\"删除\",\"parentId\":12,\"permission\":\"sys:role:del\",\"sort\":100,\"type\":2,\"updateTime\":1597715866634},{\"createTime\":1597715866634,\"css\":\"\",\"href\":\"\",\"id\":10,\"name\":\"新增\",\"parentId\":8,\"permission\":\"sys:menu:add\",\"sort\":100,\"type\":2,\"updateTime\":1597715866634},{\"createTime\":1597715866634,\"css\":\"\",\"href\":\"\",\"id\":19,\"name\":\"删除\",\"parentId\":16,\"permission\":\"dict:del\",\"sort\":100,\"type\":2,\"updateTime\":1597715866634},{\"createTime\":1597715866634,\"css\":\"\",\"href\":\"\",\"id\":4,\"name\":\"新增\",\"parentId\":2,\"permission\":\"sys:user:add\",\"sort\":100,\"type\":2,\"updateTime\":1597715866634},{\"createTime\":1597715866634,\"css\":\"\",\"href\":\"\",\"id\":14,\"name\":\"新增\",\"parentId\":12,\"permission\":\"sys:role:add\",\"sort\":100,\"type\":2,\"updateTime\":1597715866634},{\"createTime\":1597715866634,\"css\":\"\",\"href\":\"\",\"id\":9,\"name\":\"查询\",\"parentId\":8,\"permission\":\"sys:menu:query\",\"sort\":100,\"type\":2,\"updateTime\":1597715866634},{\"createTime\":1597715866634,\"css\":\"\",\"href\":\"\",\"id\":18,\"name\":\"新增\",\"parentId\":16,\"permission\":\"dict:add\",\"sort\":100,\"type\":2,\"updateTime\":1597715866634},{\"createTime\":1597715866635,\"css\":\"\",\"href\":\"\",\"id\":3,\"name\":\"查询\",\"parentId\":2,\"permission\":\"sys:user:query\",\"sort\":100,\"type\":2,\"updateTime\":1597715866635},{\"createTime\":1597715866636,\"css\":\"\",\"href\":\"\",\"id\":13,\"name\":\"查询\",\"parentId\":12,\"permission\":\"sys:role:query\",\"sort\":100,\"type\":2,\"updateTime\":1597715866636},{\"createTime\":1597715866636,\"css\":\"\",\"href\":\"\",\"id\":17,\"name\":\"查询\",\"parentId\":16,\"permission\":\"dict:query\",\"sort\":100,\"type\":2,\"updateTime\":1597715866636}],\"phone\":\"\",\"status\":1,\"token\":\"23484f6e-c376-411c-9e1f-4b425c9763c9\",\"updateTime\":1589728889000,\"username\":\"admin\"}', '2020-08-19 05:57:47', '2020-08-18 09:57:47', '2020-08-18 09:57:47');
INSERT INTO `t_token` VALUES ('ea15909b-8486-497f-97ba-af8dbb4e4b1e', '{\"accountNonExpired\":true,\"accountNonLocked\":true,\"authorities\":[{\"authority\":\"enterprise:add\"},{\"authority\":\"order:del\"},{\"authority\":\"sys:user:password\"},{\"authority\":\"order:confirm:seller\"},{\"authority\":\"sys:menu:add\"},{\"authority\":\"sys:user:add\"},{\"authority\":\"order:addseller\"},{\"authority\":\"dict:add\"},{\"authority\":\"dict:del\"},{\"authority\":\"dict:query\"},{\"authority\":\"concreteType:query\"},{\"authority\":\"concreteType:add\"},{\"authority\":\"sys:role:query\"},{\"authority\":\"order:addsent\"},{\"authority\":\"sys:role:add\"},{\"authority\":\"sys:role:del\"},{\"authority\":\"sys:menu:del\"},{\"authority\":\"sys:user:query\"},{\"authority\":\"order:add\"},{\"authority\":\"order:query:owns\"},{\"authority\":\"order:query:all\"},{\"authority\":\"order:confirm:buyer\"},{\"authority\":\"sys:menu:query\"},{\"authority\":\"concreteType:del\"},{\"authority\":\"enterprise:query\"},{\"authority\":\"enterprise:del\"}],\"createTime\":1491808898000,\"credentialsNonExpired\":true,\"eName\":\"创投有限公司\",\"eid\":1,\"enabled\":true,\"expireTime\":1597785871260,\"id\":1,\"loginTime\":1597713871260,\"name\":\"超级管理员\",\"password\":\"$2a$10$dqV5omL.uFKaW/q7FhxGs.cW5SLmFY3zxH7Y7h4ZoPT9LG9z3DPNG\",\"permissions\":[{\"createTime\":1597713871166,\"css\":\"\",\"href\":\"pages/order/orderList.html?\",\"id\":34,\"name\":\"订单列表\",\"parentId\":33,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"\",\"href\":\"pages/dashboard.html\",\"id\":43,\"name\":\"今日价格\",\"parentId\":0,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"\",\"href\":\"pages/concretetype/concretetypeList.html?type=additive\",\"id\":27,\"name\":\"添加剂\",\"parentId\":26,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"fa-cog\",\"href\":\"pages/menu/menuList.html\",\"id\":8,\"name\":\"菜单\",\"parentId\":7,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"fa-user\",\"href\":\"pages/user/userList.html\",\"id\":2,\"name\":\"用户查询\",\"parentId\":1,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"\",\"href\":\"pages/enterprise/enterpriseList.html?type=1\",\"id\":21,\"name\":\"需方企业\",\"parentId\":20,\"permission\":\"\",\"sort\":1,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"\",\"href\":\"pages/concretetype/concretetypeList.html?type=type\",\"id\":28,\"name\":\"混凝土型号\",\"parentId\":26,\"permission\":\"\",\"sort\":2,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"\",\"href\":\"\",\"id\":33,\"name\":\"订单管理\",\"parentId\":0,\"permission\":\"\",\"sort\":2,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"\",\"href\":\"pages/enterprise/enterpriseList.html?type=2\",\"id\":22,\"name\":\"供方企业\",\"parentId\":20,\"permission\":\"\",\"sort\":2,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871166,\"css\":\"fa-user-secret\",\"href\":\"pages/role/roleList.html\",\"id\":12,\"name\":\"角色\",\"parentId\":7,\"permission\":\"\",\"sort\":2,\"type\":1,\"updateTime\":1597713871166},{\"createTime\":1597713871182,\"css\":\"fa-users\",\"href\":\"\",\"id\":1,\"name\":\"用户管理\",\"parentId\":0,\"permission\":\"\",\"sort\":3,\"type\":1,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"pages/concretetype/concretetypeList.html?type=level\",\"id\":29,\"name\":\"抗渗等级\",\"parentId\":26,\"permission\":\"\",\"sort\":3,\"type\":1,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"fa-codepen\",\"href\":\"\",\"id\":20,\"name\":\"企业管理\",\"parentId\":0,\"permission\":\"\",\"sort\":5,\"type\":1,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"fa-flask\",\"href\":\"\",\"id\":26,\"name\":\"混凝土类型\",\"parentId\":0,\"permission\":\"\",\"sort\":7,\"type\":1,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"fa-reddit\",\"href\":\"pages/dict/dictList.html\",\"id\":16,\"name\":\"字典管理\",\"parentId\":0,\"permission\":\"\",\"sort\":9,\"type\":1,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"fa-pencil-square-o\",\"href\":\"pages/user/changePassword.html\",\"id\":5,\"name\":\"修改密码\",\"parentId\":0,\"permission\":\"sys:user:password\",\"sort\":11,\"type\":1,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"fa-gears\",\"href\":\"\",\"id\":7,\"name\":\"系统设置\",\"parentId\":0,\"permission\":\"\",\"sort\":13,\"type\":1,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":40,\"name\":\"添加发货信息\",\"parentId\":33,\"permission\":\"order:addsent\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":35,\"name\":\"新增\",\"parentId\":33,\"permission\":\"order:add\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":25,\"name\":\"删除\",\"parentId\":20,\"permission\":\"enterprise:del\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":11,\"name\":\"删除\",\"parentId\":8,\"permission\":\"sys:menu:del\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":30,\"name\":\"查询\",\"parentId\":26,\"permission\":\"concreteType:query\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":39,\"name\":\"审核订单\",\"parentId\":33,\"permission\":\"order:addseller\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":15,\"name\":\"删除\",\"parentId\":12,\"permission\":\"sys:role:del\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":24,\"name\":\"新增\",\"parentId\":20,\"permission\":\"enterprise:add\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":10,\"name\":\"新增\",\"parentId\":8,\"permission\":\"sys:menu:add\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":19,\"name\":\"删除\",\"parentId\":16,\"permission\":\"dict:del\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":38,\"name\":\"删除\",\"parentId\":33,\"permission\":\"order:del\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":4,\"name\":\"新增\",\"parentId\":2,\"permission\":\"sys:user:add\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":14,\"name\":\"新增\",\"parentId\":12,\"permission\":\"sys:role:add\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":23,\"name\":\"查询\",\"parentId\":20,\"permission\":\"enterprise:query\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":42,\"name\":\"卖家确认\",\"parentId\":33,\"permission\":\"order:confirm:seller\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":9,\"name\":\"查询\",\"parentId\":8,\"permission\":\"sys:menu:query\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":18,\"name\":\"新增\",\"parentId\":16,\"permission\":\"dict:add\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":37,\"name\":\"查询我的订单\",\"parentId\":33,\"permission\":\"order:query:owns\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":3,\"name\":\"查询\",\"parentId\":2,\"permission\":\"sys:user:query\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":13,\"name\":\"查询\",\"parentId\":12,\"permission\":\"sys:role:query\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":32,\"name\":\"删除\",\"parentId\":26,\"permission\":\"concreteType:del\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":41,\"name\":\"买家确认\",\"parentId\":33,\"permission\":\"order:confirm:buyer\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":17,\"name\":\"查询\",\"parentId\":16,\"permission\":\"dict:query\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":36,\"name\":\"查询所有\",\"parentId\":33,\"permission\":\"order:query:all\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182},{\"createTime\":1597713871182,\"css\":\"\",\"href\":\"\",\"id\":31,\"name\":\"新增\",\"parentId\":26,\"permission\":\"concreteType:add\",\"sort\":100,\"type\":2,\"updateTime\":1597713871182}],\"phone\":\"\",\"status\":1,\"token\":\"ea15909b-8486-497f-97ba-af8dbb4e4b1e\",\"updateTime\":1589728889000,\"username\":\"admin\"}', '2020-08-19 05:24:31', '2020-08-18 09:24:31', '2020-08-18 09:24:31');

SET FOREIGN_KEY_CHECKS = 1;
