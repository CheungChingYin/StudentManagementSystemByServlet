CREATE DATABASE  IF NOT EXISTS `student` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `student`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: student
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `permission` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1,'admin','admin',1),(3,'test','123456',1),(4,'abc','abc',1),(9,'ABC','Test',0),(13,'张三','BlnHmS4miWI4TrF/r+iDZA==',1),(14,'李四','1231',0),(15,'李白','Ftek/KdELdo62TyacmWX5A==',0),(16,'长江','123',1),(17,'王贺','123443',1),(18,'测试','12424',1),(19,'程咬金','12414',0),(20,'王伟','hcxtD/ICBEN5tBAywktytw==',0);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college` (
  `id` int(2) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (1,'机电工程学院'),(2,'电子信息学院'),(3,'工商管理学院'),(4,'财经管理学院'),(5,'汽车工程学院');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major` (
  `id` int(2) NOT NULL,
  `name` varchar(45) NOT NULL,
  `college_id` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `major_college` (`college_id`),
  CONSTRAINT `major_college` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,'数控技术',1),(2,'模具设计与制造',1),(3,'机械制造与自动化',1),(4,'机电设备维修与管理',1),(5,'工业机器人',1),(6,'电气自动化',1),(7,'机械设计与制造',1),(8,'工业设计',2),(9,'艺术设计',2),(10,'光伏工程技术',2),(11,'光伏发电技术与应用',2),(12,'物联网应用技术',2),(13,'计算机应用技术',2),(14,'通讯技术',2),(15,'电子信息工程技术',2),(16,'物流管理',3),(17,'电子商务',3),(18,'市场营销',3),(19,'国际贸易实务',3),(20,'工商企业管理',3),(21,'商务管理',3),(22,'会计',4),(23,'金融管理与实务',4),(24,'财务管理',4),(25,'酒店管理',4),(26,'旅游管理',4),(27,'食品营养与检测',5),(28,'汽车技术服务与营销',5),(29,'汽车检测与维修技术',5);
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` char(15) NOT NULL,
  `name` varchar(45) NOT NULL,
  `sex` int(1) NOT NULL,
  `birth` date NOT NULL,
  `schoolday` date NOT NULL,
  `major_id` int(2) NOT NULL,
  `college_id` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_college` (`college_id`),
  KEY `student_major` (`major_id`),
  CONSTRAINT `student_college` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`),
  CONSTRAINT `student_major` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('123271613302481','张三丰',0,'2000-05-05','2017-09-01',24,4),('123271614487138','白居易',1,'1996-09-30','2017-09-01',22,4),('123271631102102','李四',0,'1998-01-31','2017-09-01',17,3),('123271631102109','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102110','王五',1,'1998-03-30','2016-09-01',24,4),('123271631102112','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102119','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102120','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102121','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102122','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102123','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102125','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102126','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102127','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102128','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102130','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102131','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102132','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102133','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102134','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102135','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102136','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102138','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102140','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102141','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102142','王五',1,'1998-03-30','2016-09-01',6,1),('123271631102150','王五',1,'1998-03-30','2016-09-01',3,1),('123271631102155','王博堂',0,'2018-10-08','2018-10-15',19,3),('123271631102188','孙一峰',1,'2018-10-29','2018-10-29',23,4),('123271631102201','杜甫',1,'1998-01-30','2018-09-01',4,1),('123271631102874','白骨精',0,'2018-10-08','2018-10-23',17,3),('123271631102879','孔子',1,'2018-10-04','2018-10-23',26,4),('123271631103258','沙僧',1,'2018-10-07','2018-10-03',26,4),('123271631112101','李白',1,'1997-08-05','2016-09-01',13,2),('123271631123051','李白',0,'2018-10-09','2018-10-14',29,5),('123279846613281','唐三藏',0,'2018-10-09','2018-10-14',24,4),('123587463102058','银角大王',1,'2018-10-10','2018-10-07',3,1),('153287961130258','猪八戒',1,'1989-02-13','2016-01-22',19,3),('575468132460128','孙悟空',1,'2018-09-11','2018-09-01',28,5);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-19 14:38:49
