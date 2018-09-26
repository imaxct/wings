-- MySQL dump 10.16  Distrib 10.1.35-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: student
-- ------------------------------------------------------
-- Server version	10.1.35-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `wings_admin`
--

DROP TABLE IF EXISTS `wings_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wings_admin` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wings_course`
--

DROP TABLE IF EXISTS `wings_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wings_course` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `course_name` varchar(64) NOT NULL COMMENT '课程名',
  `course_desc` text COMMENT '课程描述',
  `course_time` varchar(64) DEFAULT NULL COMMENT '课程时间',
  `not_poor_num` int(11) DEFAULT NULL COMMENT '非贫困生数量',
  `total_num` int(11) DEFAULT NULL COMMENT '总数',
  `grade_limit` varchar(64) DEFAULT NULL COMMENT '允许报名的年级',
  `available_num` int(11) DEFAULT NULL COMMENT '当前剩余总数',
  `deadline` datetime DEFAULT NULL COMMENT '报名截止日期',
  `course_location` varchar(64) DEFAULT NULL COMMENT '上课地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wings_select`
--

DROP TABLE IF EXISTS `wings_select`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wings_select` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `student_id` bigint(19) unsigned NOT NULL,
  `course_id` bigint(19) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wings_setting`
--

DROP TABLE IF EXISTS `wings_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wings_setting` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `setting_name` varchar(64) DEFAULT NULL,
  `setting_value` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wings_setting_setting_name_uindex` (`setting_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wings_student`
--

DROP TABLE IF EXISTS `wings_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wings_student` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `student_no` varchar(12) NOT NULL,
  `student_name` varchar(10) NOT NULL,
  `id_no` varchar(18) DEFAULT NULL,
  `academy` varchar(64) DEFAULT NULL COMMENT '学院',
  `campus` varchar(32) DEFAULT NULL COMMENT '校区',
  `major` varchar(64) DEFAULT NULL COMMENT '专业',
  `poor_level` varchar(10) NOT NULL DEFAULT 'NOT_POOR' COMMENT '是否困难生',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `class_name` varchar(10) DEFAULT NULL COMMENT '班名',
  `nation` varchar(10) DEFAULT NULL COMMENT '民族',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `wings_student_student_no_uindex` (`student_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-26  9:53:51
