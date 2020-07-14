/*
 Date: 14/07/2020 09:03:49
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
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_permission` VALUES (20, 0, '企业管理', 'fa-codepen', '', 1, '', 5);
INSERT INTO `sys_permission` VALUES (21, 20, '需方企业', '', 'pages/enterprise/enterpriseList.html?type=1', 1, '', 1);
INSERT INTO `sys_permission` VALUES (22, 20, '供方企业', '', 'pages/enterprise/enterpriseList.html?type=2', 1, '', 2);
INSERT INTO `sys_permission` VALUES (23, 20, '查询', '', '', 2, 'enterprise:query', 100);
INSERT INTO `sys_permission` VALUES (24, 20, '新增', '', '', 2, 'enterprise:add', 100);
INSERT INTO `sys_permission` VALUES (25, 20, '删除', '', '', 2, 'enterprise:del', 100);
INSERT INTO `sys_permission` VALUES (26, 0, '混凝土类型', 'fa-flask', '', 1, '', 7);
INSERT INTO `sys_permission` VALUES (27, 26, '添加剂', '', 'pages/concretetype/concretetypeList.html?type=additive', 1, '', 1);
INSERT INTO `sys_permission` VALUES (28, 26, '混凝土型号', '', 'pages/concretetype/concretetypeList.html?type=type', 1, '', 2);
INSERT INTO `sys_permission` VALUES (29, 26, '抗渗等级', '', 'pages/concretetype/concretetypeList.html?type=level', 1, '', 3);
INSERT INTO `sys_permission` VALUES (30, 26, '查询', '', '', 2, 'concreteType:query', 100);
INSERT INTO `sys_permission` VALUES (31, 26, '新增', '', '', 2, 'concreteType:add', 100);
INSERT INTO `sys_permission` VALUES (32, 26, '删除', '', '', 2, 'concreteType:del', 100);
INSERT INTO `sys_permission` VALUES (33, 0, '订单管理', '', '', 1, '', 2);
INSERT INTO `sys_permission` VALUES (34, 33, '订单列表', '', 'pages/order/orderList.html?', 1, '', 1);
INSERT INTO `sys_permission` VALUES (35, 33, '新增', '', '', 2, 'order:add', 100);
INSERT INTO `sys_permission` VALUES (36, 33, '查询所有', '', '', 2, 'order:query:all', 100);
INSERT INTO `sys_permission` VALUES (37, 33, '查询我的订单', '', '', 2, 'order:query:owns', 100);
INSERT INTO `sys_permission` VALUES (38, 33, '删除', '', '', 2, 'order:del', 100);
INSERT INTO `sys_permission` VALUES (39, 33, '审核订单', '', '', 2, 'order:addseller', 100);
INSERT INTO `sys_permission` VALUES (40, 33, '添加发货信息', '', '', 2, 'order:addsent', 100);
INSERT INTO `sys_permission` VALUES (41, 33, '买家确认', '', '', 2, 'order:confirm:buyer', 100);
INSERT INTO `sys_permission` VALUES (42, 33, '卖家确认', '', '', 2, 'order:confirm:seller', 100);
INSERT INTO `sys_permission` VALUES (43, 0, '今日价格', '', 'pages/dashboard.html', 1, '', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ADMIN', '超级管理员', '2020-05-17 23:20:29', '2020-07-11 23:25:09');
INSERT INTO `sys_role` VALUES (2, 'USER', '', '2017-08-01 21:47:31', '2020-07-11 23:25:15');
INSERT INTO `sys_role` VALUES (3, '需方', '', '2020-07-11 20:24:55', '2020-07-11 20:26:06');
INSERT INTO `sys_role` VALUES (4, '供方', '', '2020-07-11 20:25:55', '2020-07-11 20:26:13');

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
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (1, 26);
INSERT INTO `sys_role_permission` VALUES (1, 27);
INSERT INTO `sys_role_permission` VALUES (1, 28);
INSERT INTO `sys_role_permission` VALUES (1, 29);
INSERT INTO `sys_role_permission` VALUES (1, 30);
INSERT INTO `sys_role_permission` VALUES (1, 31);
INSERT INTO `sys_role_permission` VALUES (1, 32);
INSERT INTO `sys_role_permission` VALUES (1, 33);
INSERT INTO `sys_role_permission` VALUES (1, 34);
INSERT INTO `sys_role_permission` VALUES (1, 35);
INSERT INTO `sys_role_permission` VALUES (1, 36);
INSERT INTO `sys_role_permission` VALUES (1, 37);
INSERT INTO `sys_role_permission` VALUES (1, 38);
INSERT INTO `sys_role_permission` VALUES (1, 39);
INSERT INTO `sys_role_permission` VALUES (1, 40);
INSERT INTO `sys_role_permission` VALUES (1, 41);
INSERT INTO `sys_role_permission` VALUES (1, 42);
INSERT INTO `sys_role_permission` VALUES (1, 43);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 9);
INSERT INTO `sys_role_permission` VALUES (2, 12);
INSERT INTO `sys_role_permission` VALUES (2, 13);
INSERT INTO `sys_role_permission` VALUES (2, 20);
INSERT INTO `sys_role_permission` VALUES (2, 21);
INSERT INTO `sys_role_permission` VALUES (2, 22);
INSERT INTO `sys_role_permission` VALUES (2, 23);
INSERT INTO `sys_role_permission` VALUES (2, 24);
INSERT INTO `sys_role_permission` VALUES (2, 25);
INSERT INTO `sys_role_permission` VALUES (2, 26);
INSERT INTO `sys_role_permission` VALUES (2, 27);
INSERT INTO `sys_role_permission` VALUES (2, 28);
INSERT INTO `sys_role_permission` VALUES (2, 29);
INSERT INTO `sys_role_permission` VALUES (2, 30);
INSERT INTO `sys_role_permission` VALUES (2, 31);
INSERT INTO `sys_role_permission` VALUES (2, 32);
INSERT INTO `sys_role_permission` VALUES (2, 33);
INSERT INTO `sys_role_permission` VALUES (2, 34);
INSERT INTO `sys_role_permission` VALUES (2, 35);
INSERT INTO `sys_role_permission` VALUES (2, 36);
INSERT INTO `sys_role_permission` VALUES (2, 38);
INSERT INTO `sys_role_permission` VALUES (2, 39);
INSERT INTO `sys_role_permission` VALUES (2, 43);
INSERT INTO `sys_role_permission` VALUES (3, 5);
INSERT INTO `sys_role_permission` VALUES (3, 16);
INSERT INTO `sys_role_permission` VALUES (3, 17);
INSERT INTO `sys_role_permission` VALUES (3, 26);
INSERT INTO `sys_role_permission` VALUES (3, 30);
INSERT INTO `sys_role_permission` VALUES (3, 33);
INSERT INTO `sys_role_permission` VALUES (3, 35);
INSERT INTO `sys_role_permission` VALUES (3, 37);
INSERT INTO `sys_role_permission` VALUES (3, 38);
INSERT INTO `sys_role_permission` VALUES (3, 41);
INSERT INTO `sys_role_permission` VALUES (4, 5);
INSERT INTO `sys_role_permission` VALUES (4, 33);
INSERT INTO `sys_role_permission` VALUES (4, 37);
INSERT INTO `sys_role_permission` VALUES (4, 40);
INSERT INTO `sys_role_permission` VALUES (4, 42);

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
INSERT INTO `sys_role_user` VALUES (3, 2);
INSERT INTO `sys_role_user` VALUES (4, 2);
INSERT INTO `sys_role_user` VALUES (5, 3);
INSERT INTO `sys_role_user` VALUES (6, 4);
INSERT INTO `sys_role_user` VALUES (7, 3);
INSERT INTO `sys_role_user` VALUES (8, 4);
INSERT INTO `sys_role_user` VALUES (9, 3);

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
  `eid` int(11) NULL DEFAULT NULL,
  `eName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$dqV5omL.uFKaW/q7FhxGs.cW5SLmFY3zxH7Y7h4ZoPT9LG9z3DPNG', '超级管理员', '', 1, '创投有限公司', 1, '2017-04-10 15:21:38', '2020-05-17 23:21:29');
INSERT INTO `sys_user` VALUES (2, 'user', '$2a$10$ooGb4wjT7Hg3zgU2RhZp6eVu3jvG29i/U4L6VRwiZZ4.DZ0OOEAHu', '管理员', '', 1, '创投有限公司', 1, '2017-08-01 21:47:18', '2017-08-01 21:47:18');
INSERT INTO `sys_user` VALUES (5, 'a111', '$2a$10$C1y7vwVrJAKR0I9tW/abrOQOxRAXcnsCC7zqYbqytX29ZwsyCDYBu', '张三', '18811112222', 2, '需方-A有限公司', 1, '2020-07-10 11:38:57', '2020-07-11 20:26:30');
INSERT INTO `sys_user` VALUES (6, 'a222', '$2a$10$qAwh8961oVP7Ak9RDrfO6.Fr5wqJmExVYq0EaPcZRgs8CQ.m7NxS.', '李四', '18811113333', 4, '供方-A公司', 1, '2020-07-10 11:39:33', '2020-07-11 20:26:36');
INSERT INTO `sys_user` VALUES (7, 'b111', '$2a$10$rw0iLbqRhmNJCnWwDy.Vqu0JtiK3kVptoMSbFE8s0YwmVTsDT5BgK', '杜坤', '18811111234', 3, '需方-B有限公司', 1, '2020-07-10 11:54:47', '2020-07-11 20:27:20');
INSERT INTO `sys_user` VALUES (8, 'b222', '$2a$10$pYlSUmXlPDKDDigqBtbi5OwH9vdJvc2S.ktD8zXVyIMjkhTOJQCxG', '陆柯', '18811114321', 5, '供方-B公司', 1, '2020-07-10 11:55:56', '2020-07-11 20:27:00');

-- ----------------------------
-- Table structure for t_concrete_type
-- ----------------------------
DROP TABLE IF EXISTS `t_concrete_type`;
CREATE TABLE `t_concrete_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `k` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `money` decimal(10, 3) NULL DEFAULT 0.000,
  `sort` int(11) NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_concrete_type
-- ----------------------------
INSERT INTO `t_concrete_type` VALUES (1, 'additive', '1', '减水剂', 0.000, 1, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (2, 'additive', '2', '引气剂', 0.600, 2, '2020-06-29 09:58:24', '2020-06-29 20:55:41');
INSERT INTO `t_concrete_type` VALUES (3, 'additive', '3', '泵送剂', 0.000, 3, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (4, 'additive', '4', '缓凝剂', 0.000, 4, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (5, 'additive', '5', '早强剂', 0.000, 5, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (6, 'additive', '6', '速凝剂', 0.000, 6, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (7, 'additive', '7', '防水剂', 0.000, 7, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (8, 'additive', '8', '阻锈剂', 0.000, 8, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (9, 'additive', '9', '加气剂', 0.000, 9, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (10, 'additive', '10', '膨胀剂', 0.000, 10, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (11, 'additive', '11', '防冻剂', 0.000, 11, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (12, 'additive', '12', '着色剂', 0.000, 12, '2020-06-29 09:58:24', '2020-06-29 09:58:24');
INSERT INTO `t_concrete_type` VALUES (16, 'level', '1', 'P6', 0.000, 1, '2020-06-29 17:03:24', '2020-06-29 17:03:24');
INSERT INTO `t_concrete_type` VALUES (17, 'level', '2', 'P8', 0.000, 2, '2020-06-29 17:03:38', '2020-06-29 17:03:38');
INSERT INTO `t_concrete_type` VALUES (19, 'type', '1', 'C10', 0.000, 1, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (20, 'type', '2', 'C15', 0.000, 2, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (21, 'type', '3', 'C20', 0.000, 3, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (22, 'type', '4', 'C25', 0.000, 4, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (23, 'type', '5', 'C30', 0.000, 5, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (24, 'type', '6', 'C35', 0.000, 6, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (25, 'type', '7', 'C40', 0.000, 7, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (26, 'type', '8', 'C45', 0.000, 8, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (27, 'type', '9', 'C50', 0.000, 9, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (28, 'type', '10', 'C55', 0.000, 10, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (29, 'type', '11', 'C60', 0.000, 11, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (30, 'type', '12', 'C65', 0.000, 12, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (31, 'type', '13', 'C70', 0.000, 13, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (32, 'type', '14', 'C75', 0.000, 14, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (33, 'type', '15', 'C80', 0.000, 15, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (34, 'type', '16', 'C85', 0.000, 16, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (35, 'type', '17', 'C90', 0.000, 17, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (36, 'type', '18', 'C95', 0.000, 18, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (37, 'type', '19', 'C100', 0.000, 19, '2020-06-29 16:58:28', '2020-06-29 16:58:28');
INSERT INTO `t_concrete_type` VALUES (38, 'price', '1', '不含税', 450.000, 1, '2020-07-11 23:57:27', '2020-07-12 00:35:37');
INSERT INTO `t_concrete_type` VALUES (39, 'price', '2', '含税', 500.000, 1, '2020-07-11 23:57:27', '2020-07-12 00:29:51');

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
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `t_dict` VALUES (10, 'isFinance', '1', '30天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (11, 'isFinance', '2', '60天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (12, 'isFinance', '3', '90天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (13, 'isFinance', '4', '120天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (14, 'isFinance', '5', '150天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (15, 'isFinance', '6', '180天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (16, 'isFinance', '7', '210天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (17, 'isFinance', '8', '240天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (18, 'isFinance', '9', '270天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (19, 'isFinance', '10', '300天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (20, 'isFinance', '11', '330天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (21, 'isFinance', '12', '360天(财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (22, 'isNotFinance', '1', '30天(非财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (23, 'isNotFinance', '2', '60天(非财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (24, 'isNotFinance', '3', '90天(非财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (25, 'isNotFinance', '4', '120天(非财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (26, 'isNotFinance', '5', '150天(非财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (27, 'isNotFinance', '6', '180天(非财政项目)', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (28, 'pumpingType', '1', '地泵', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (29, 'pumpingType', '2', '天泵', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (30, 'pumpingType', '3', '拖泵', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (31, 'pumpingType', '4', '车载泵', '2020-06-30 10:49:16', '2020-06-30 10:49:16');
INSERT INTO `t_dict` VALUES (32, 'enterpriseType', '1', '需方', '2020-06-30 21:52:40', '2020-06-30 21:52:40');
INSERT INTO `t_dict` VALUES (33, 'enterpriseType', '2', '供方', '2020-06-30 21:53:03', '2020-06-30 21:53:03');
INSERT INTO `t_dict` VALUES (34, 'orderStatus', '1', '待审核', '2020-07-01 09:05:18', '2020-07-01 09:05:49');
INSERT INTO `t_dict` VALUES (35, 'orderStatus', '2', '待发货', '2020-07-01 09:05:35', '2020-07-01 09:05:35');
INSERT INTO `t_dict` VALUES (36, 'orderStatus', '3', '已发货', '2020-07-01 09:06:09', '2020-07-01 09:06:09');
INSERT INTO `t_dict` VALUES (37, 'orderStatus', '4', '已签收', '2020-07-01 09:06:21', '2020-07-01 09:06:21');
INSERT INTO `t_dict` VALUES (38, 'orderStatus', '5', '已完成', '2020-07-01 09:06:35', '2020-07-01 09:06:35');

-- ----------------------------
-- Table structure for t_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `t_enterprise`;
CREATE TABLE `t_enterprise`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `eInfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '企业信息',
  `creditCode` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码/税号',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位地址',
  `telephone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `type` tinyint(1) NOT NULL COMMENT '类型（0：贸易公司，1：需方，2：供方）',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_enterprise
-- ----------------------------
INSERT INTO `t_enterprise` VALUES (1, '创投有限公司', NULL, NULL, NULL, NULL, 0, 1, '2020-06-01 00:00:00', '2020-06-01 00:00:00');
INSERT INTO `t_enterprise` VALUES (2, '需方-A有限公司', '企业信息1', '123456789011111111', '河南省 禹州市 XX路1号', '18812341234', 1, 1, '2020-06-28 17:01:43', '2020-06-30 22:56:41');
INSERT INTO `t_enterprise` VALUES (3, '需方-B有限公司', '企业信息2', '123456789022222222', '河南省 禹州市 XX路1号', '18812341234', 1, 1, '2020-06-28 17:10:42', '2020-06-30 22:56:32');
INSERT INTO `t_enterprise` VALUES (4, '供方-A公司', '企业信息3', '123456789033333333', '河南省 禹州市 XX路1号', '18812341234', 2, 1, '2020-06-28 17:11:01', '2020-06-30 22:56:55');
INSERT INTO `t_enterprise` VALUES (5, '供方-B公司', '企业信息4', '123456789044444444', '河南省 禹州市 XX路1号', '18812341234', 2, 1, '2020-06-30 22:57:16', '2020-06-30 22:57:16');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buyer_enterprise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称-需方',
  `buyer_enterprise_id` bigint(20) NULL DEFAULT NULL,
  `proname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程名称',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '施工部位',
  `nun_order` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单量(预计用量)',
  `concrete_additive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加剂',
  `concrete_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '混凝土型号',
  `concrete_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抗渗压等级',
  `pumping_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '泵送方式',
  `sendTime` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `is_finance` tinyint(1) NULL DEFAULT NULL COMMENT '是否财政项目',
  `pay_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结算方式',
  `seller_enterprise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称-供方',
  `seller_enterprise_id` bigint(20) NULL DEFAULT NULL,
  `num_cend` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货量',
  `p_jianzhan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监站人员',
  `p_driver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '司机',
  `num_receive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收量',
  `p_receive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人',
  `p_pangzhan_A` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '旁站人A-需方人员',
  `p_pangzhan_B` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '旁站人B-供方人员',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '订单状态(1:待审核,2:待发货,3:已发货,4:已签收,5:已完成)',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '上次修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心地下室', '23', '泵送剂', 'C20', 'P8', '拖泵', NULL, 1, '210天(财政项目)', '供方-A公司', 4, '22', '{\"name\":\"李一一\",\"phone\":\"15988889999\"}', '{\"name\":\"金伢\",\"phone\":\"13200001111\"}', '22', '{\"name\":\"吴克\",\"phone\":\"13700009876\"}', '{\"name\":\"郑开具\",\"phone\":\"15900005555\"}', '{\"name\":\"刘维\",\"phone\":\"13733337777\"}', 5, '2020-07-09 17:12:07', '2020-07-11 15:42:50');
INSERT INTO `t_order` VALUES (2, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心一楼', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-07-03 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (3, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心二楼', '50', '泵送剂', 'C15', 'P8', '车载泵', NULL, 0, '30天(非财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-07-09 16:36:37', '2020-07-09 16:36:37');
INSERT INTO `t_order` VALUES (4, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中三楼', '111', '减水剂', 'C10', 'P6', '地泵', NULL, 0, '30天(非财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-07-09 17:09:29', '2020-07-11 01:24:06');
INSERT INTO `t_order` VALUES (5, '需方-B有限公司', 3, '禹州市数字港大厦', '地下室', '23', '泵送剂', 'C20', 'P8', '拖泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-07-09 17:12:07', '2020-07-09 17:12:07');
INSERT INTO `t_order` VALUES (6, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心一楼-东', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-08 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (7, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心一楼-西', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-07 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (8, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心一楼-南', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-06 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (9, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心一楼-北', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-05 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (10, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心二楼-东', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-04 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (11, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心二楼-西', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-03 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (12, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心二楼-南', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-02 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (13, '需方-A有限公司', 2, '禹州市中原云都数据湖项目', '大数据中心二楼-北', '21', '泵送剂', 'C15', 'P8', '车载泵', NULL, 1, '210天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-01 17:48:37', '2020-07-03 17:48:37');
INSERT INTO `t_order` VALUES (14, '需方-B有限公司', 3, '数字港大厦项目', '一楼', '50', '泵送剂', 'C30', 'P6', '地泵', NULL, 1, '30天(财政项目)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-07-11 20:15:12', '2020-07-13 11:20:43');

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

SET FOREIGN_KEY_CHECKS = 1;
