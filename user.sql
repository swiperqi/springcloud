/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : springsecurity

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 25/05/2020 15:01:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色（ROLE_*）',
  `enabled` tinyint(4) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'provider', '$2a$10$gxtsAJyZmdyr6TJUl99IguFOP3PJcTVAaOxrRxpXzlq75StpECi.y', 'ROLE_provider,ROLE_system', 1);
INSERT INTO `user` VALUES (2, 'admin', '$2a$10$pqaZomYlD9/4zp3eZuDZt.C4gusjkMURx27Za9HeF1u74d1s2AtMG', 'ROLE_provider,ROLE_admin', 1);
INSERT INTO `user` VALUES (3, 'system', '$2a$10$Fw/OcC8C.s.hl38jL6x7JeIagZCjQBQZ5FRqgAHlbA49RD2uDrS7S', 'ROLE_system', 1);

SET FOREIGN_KEY_CHECKS = 1;
