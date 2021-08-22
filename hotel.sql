/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2020-12-15 00:19:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Aid` char(5) NOT NULL,
  `Apassword` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Aname` varchar(20) NOT NULL,
  PRIMARY KEY (`Aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('12345', '96e79218965eb72c92a549dd5a330112', 'admin1');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `Cid` char(7) NOT NULL,
  `Cname` varchar(10) NOT NULL,
  `Cpassword` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Ctellnum` char(11) NOT NULL,
  PRIMARY KEY (`Cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1111111', 'jdj', 'e10adc3949ba59abbe56e057f20f883e', '11111111111');
INSERT INTO `customer` VALUES ('1231231', 'cm', 'e10adc3949ba59abbe56e057f20f883e', '17712345678');
INSERT INTO `customer` VALUES ('1234123', 'jerry', 'e10adc3949ba59abbe56e057f20f883e', '17712344567');
INSERT INTO `customer` VALUES ('1234567', 'jin', 'e10adc3949ba59abbe56e057f20f883e', '15166810534');
INSERT INTO `customer` VALUES ('1234568', 'Jack', '96e79218965eb72c92a549dd5a330112', '13812345678');
INSERT INTO `customer` VALUES ('7654321', 'mike', 'e10adc3949ba59abbe56e057f20f883e', '18145678951');

-- ----------------------------
-- Table structure for deposit
-- ----------------------------
DROP TABLE IF EXISTS `deposit`;
CREATE TABLE `deposit` (
  `Oid` char(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Dprice` float NOT NULL,
  `Dstate` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deposit
-- ----------------------------
INSERT INTO `deposit` VALUES ('12345670011234', '1080', '0');
INSERT INTO `deposit` VALUES ('12345670011245', '360', '0');
INSERT INTO `deposit` VALUES ('12345670013734', '1080', '1');
INSERT INTO `deposit` VALUES ('12345670013800', '2340', '0');
INSERT INTO `deposit` VALUES ('12345670014957', '2700', '0');
INSERT INTO `deposit` VALUES ('12345670015712', '2040', '0');
INSERT INTO `deposit` VALUES ('12345670021452', '1440', '0');
INSERT INTO `deposit` VALUES ('12345670032429', '540', '0');

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `Oid` char(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Econtent` text NOT NULL,
  `Elevel` int NOT NULL,
  PRIMARY KEY (`Oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES ('12345670011234', 'nice', '5');
INSERT INTO `evaluate` VALUES ('12345670011245', '不错', '4');
INSERT INTO `evaluate` VALUES ('12345670013734', '不错', '4');
INSERT INTO `evaluate` VALUES ('12345670014957', '还行', '3');
INSERT INTO `evaluate` VALUES ('12345670015712', '还可以', '4');

-- ----------------------------
-- Table structure for facilities
-- ----------------------------
DROP TABLE IF EXISTS `facilities`;
CREATE TABLE `facilities` (
  `Fid` char(2) NOT NULL,
  `Fname` varchar(20) NOT NULL,
  `Fprice` float NOT NULL,
  PRIMARY KEY (`Fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of facilities
-- ----------------------------
INSERT INTO `facilities` VALUES ('01', '空调', '3000');
INSERT INTO `facilities` VALUES ('02', '电视', '3500');
INSERT INTO `facilities` VALUES ('03', '双人床', '2000');
INSERT INTO `facilities` VALUES ('04', '单人床', '1500');
INSERT INTO `facilities` VALUES ('05', '浴缸', '7000');
INSERT INTO `facilities` VALUES ('06', '柜式空调', '3200');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `Oid` char(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Cid` char(7) NOT NULL,
  `Rid` char(3) NOT NULL,
  `Begin_time` datetime NOT NULL,
  `Finish_time` datetime NOT NULL,
  PRIMARY KEY (`Oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('12345670011234', '1234567', '001', '2020-11-27 20:41:14', '2020-12-02 15:03:30');
INSERT INTO `order` VALUES ('12345670011245', '1234567', '002', '2020-11-27 22:01:24', '2020-11-29 15:03:42');
INSERT INTO `order` VALUES ('12345670013734', '1234567', '002', '2000-11-17 00:00:00', '2020-11-25 15:03:51');
INSERT INTO `order` VALUES ('12345670013800', '1234567', '001', '2020-11-27 20:38:00', '2020-12-09 15:03:58');
INSERT INTO `order` VALUES ('12345670014957', '1234567', '001', '2020-11-27 21:49:57', '2020-12-11 15:04:05');
INSERT INTO `order` VALUES ('12345670015712', '1234567', '002', '2020-11-27 21:57:12', '2020-12-13 15:04:09');
INSERT INTO `order` VALUES ('12345670021452', '1234567', '002', '2020-12-02 15:45:24', '2020-12-14 23:08:35');
INSERT INTO `order` VALUES ('12345670032429', '1234567', '001', '2020-12-16 00:00:00', '2020-12-19 00:00:00');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `Pid` int NOT NULL,
  `Pname` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('10', '人事');
INSERT INTO `position` VALUES ('20', '采购');
INSERT INTO `position` VALUES ('30', '餐饮');
INSERT INTO `position` VALUES ('40', '工程');
INSERT INTO `position` VALUES ('50', '销售');
INSERT INTO `position` VALUES ('60', '财务');
INSERT INTO `position` VALUES ('70', '前厅');
INSERT INTO `position` VALUES ('80', '客房');
INSERT INTO `position` VALUES ('90', '后勤');
INSERT INTO `position` VALUES ('100', '安保');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `Rid` char(3) NOT NULL,
  `Rstate` int NOT NULL,
  `Rtype` varchar(20) NOT NULL,
  `Rprice` float NOT NULL,
  `Rname` varchar(40) NOT NULL,
  PRIMARY KEY (`Rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('001', '1', '双人间', '180', '标准双人间');
INSERT INTO `room` VALUES ('002', '1', '单人间', '120', '标准单人间');
INSERT INTO `room` VALUES ('003', '0', '单人间', '120', '标准单人间');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `Sid` char(8) NOT NULL,
  `Sname` varchar(20) NOT NULL,
  `Spassword` varchar(32) NOT NULL,
  `Sposition` int NOT NULL,
  `onjob` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`Sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('20112101', 'Jack', '96e79218965eb72c92a549dd5a330112', '10', '0');
INSERT INTO `staff` VALUES ('20112102', 'Maria', '96e79218965eb72c92a549dd5a330112', '10', '0');
INSERT INTO `staff` VALUES ('20112103', 'Larry', 'e10adc3949ba59abbe56e057f20f883e', '20', '0');
INSERT INTO `staff` VALUES ('20112601', 'Mike', 'e10adc3949ba59abbe56e057f20f883e', '30', '1');
INSERT INTO `staff` VALUES ('20112602', '铁子', 'e10adc3949ba59abbe56e057f20f883e', '50', '1');
INSERT INTO `staff` VALUES ('20112603', 'Jenny', 'e10adc3949ba59abbe56e057f20f883e', '20', '0');
INSERT INTO `staff` VALUES ('20112801', 'Kate', 'e10adc3949ba59abbe56e057f20f883e', '80', '1');
INSERT INTO `staff` VALUES ('20120211', '123', 'e10adc3949ba59abbe56e057f20f883e', '50', '1');
INSERT INTO `staff` VALUES ('20120401', 'mimima', 'e10adc3949ba59abbe56e057f20f883e', '30', '1');
