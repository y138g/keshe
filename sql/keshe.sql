/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : keshe

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 19/06/2023 00:14:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `AdminID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `Password` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `PhoneNumber` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AdminID`) USING BTREE,
  UNIQUE INDEX `Username`(`Username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo`  (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NULL DEFAULT NULL,
  `PowerBankID` int(11) NULL DEFAULT NULL,
  `StartTime` datetime NULL DEFAULT NULL,
  `EndTime` datetime NULL DEFAULT NULL,
  `TotalCost` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`OrderID`) USING BTREE,
  INDEX `UserID`(`UserID`) USING BTREE,
  INDEX `PowerBankID`(`PowerBankID`) USING BTREE,
  CONSTRAINT `orderinfo_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderinfo_ibfk_2` FOREIGN KEY (`PowerBankID`) REFERENCES `powerbank` (`PowerBankID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for powerbank
-- ----------------------------
DROP TABLE IF EXISTS `powerbank`;
CREATE TABLE `powerbank`  (
  `PowerBankID` int(11) NOT NULL AUTO_INCREMENT,
  `Brand` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `Model` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `Capacity` int(11) NULL DEFAULT NULL COMMENT '容量',
  `RemainingPower` int(11) NULL DEFAULT NULL COMMENT '剩余电量',
  `Status` int(20) NULL DEFAULT 0 COMMENT '默认0为可用 1不可用',
  PRIMARY KEY (`PowerBankID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `Password` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `PhoneNumber` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`) USING BTREE,
  UNIQUE INDEX `Username`(`Username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
