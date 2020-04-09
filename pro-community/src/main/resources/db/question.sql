SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `tag` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `gmt_create` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `creator` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `comment_count` int(11) NULL DEFAULT 0 COMMENT '回复数',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '浏览数',
  `like_count` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
