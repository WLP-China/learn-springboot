SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_brand
-- ----------------------------
DROP TABLE IF EXISTS `pms_brand`;
CREATE TABLE `pms_brand`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_letter` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首字母',
  `sort` int(11) NULL DEFAULT NULL,
  `factory_status` int(1) NULL DEFAULT NULL COMMENT '是否为品牌制造商：0->不是；1->是',
  `show_status` int(1) NULL DEFAULT NULL,
  `product_count` int(11) NULL DEFAULT NULL COMMENT '产品数量',
  `product_comment_count` int(11) NULL DEFAULT NULL COMMENT '产品评论数量',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌logo',
  `big_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专区大图',
  `brand_story` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '品牌故事',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品牌表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_brand
-- ----------------------------
INSERT INTO `pms_brand` VALUES (1, '万和', 'W', 0, 1, 1, 100, 100, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(5).jpg', '', 'Victoria\'s Secret的故事');
INSERT INTO `pms_brand` VALUES (2, '三星', 'S', 100, 1, 1, 100, 100, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (1).jpg', NULL, '三星的故事');
INSERT INTO `pms_brand` VALUES (3, '华为', 'H', 100, 1, 1, 100, 100, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/17f2dd9756d9d333bee8e60ce8c03e4c_222_222.jpg', NULL, 'Victoria\'s Secret的故事');
INSERT INTO `pms_brand` VALUES (4, '格力', 'G', 30, 1, 1, 100, 100, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/dc794e7e74121272bbe3ce9bc41ec8c3_222_222.jpg', NULL, 'Victoria\'s Secret的故事');
INSERT INTO `pms_brand` VALUES (5, '方太', 'F', 20, 1, 1, 100, 100, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (4).jpg', NULL, 'Victoria\'s Secret的故事');
INSERT INTO `pms_brand` VALUES (6, '小米', 'M', 500, 1, 1, 100, 100, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/1e34aef2a409119018a4c6258e39ecfb_222_222.png', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180518/5afd7778Nf7800b75.jpg', '小米手机的故事');
INSERT INTO `pms_brand` VALUES (21, 'OPPO', 'O', 0, 1, 1, 88, 500, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(6).jpg', '', 'string');
INSERT INTO `pms_brand` VALUES (49, '七匹狼', 'S', 200, 1, 1, 77, 400, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/18d8bc3eb13533fab466d702a0d3fd1f40345bcd.jpg', NULL, 'BOOB的故事');
INSERT INTO `pms_brand` VALUES (50, '海澜之家', 'H', 200, 1, 1, 66, 300, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/99d3279f1029d32b929343b09d3c72de_222_222.jpg', '', '海澜之家的故事');
INSERT INTO `pms_brand` VALUES (51, '苹果', 'A', 200, 1, 1, 55, 200, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', NULL, '苹果的故事');
INSERT INTO `pms_brand` VALUES (58, 'NIKE', 'N', 0, 1, 1, 33, 100, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/timg (51).jpg', '', 'NIKE的故事');
INSERT INTO `pms_brand` VALUES (101, '101', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NIKE的故事');
INSERT INTO `pms_brand` VALUES (102, '098还好', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NIKE的故事');

SET FOREIGN_KEY_CHECKS = 1;
