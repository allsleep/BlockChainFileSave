SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_file_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_file_info`;
CREATE TABLE `tb_file_info` (
  `file_id` varchar(64) NOT NULL,
  `account_id` varchar(64) DEFAULT NULL,
  `file_name` varchar(64) DEFAULT NULL,
  `file_md5` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `account_id` varchar(32) CHARACTER SET latin1 NOT NULL,
  `username` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `account_id` varchar(32) NOT NULL,
  `author` varchar(32) DEFAULT NULL,
  `id_card` varchar(20) DEFAULT NULL,
  `phone_number` varchar(22) DEFAULT NULL,
  `sign_time` datetime DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
