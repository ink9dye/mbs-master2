-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: mbs_db
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_info`
--

DROP TABLE IF EXISTS `admin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_info` (
                              `operator_id` int NOT NULL AUTO_INCREMENT,
                              `operator_name` varchar(205) DEFAULT NULL,
                              `operator_pass` varchar(205) DEFAULT NULL,
                              PRIMARY KEY (`operator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_info`
--

LOCK TABLES `admin_info` WRITE;
/*!40000 ALTER TABLE `admin_info` DISABLE KEYS */;
INSERT INTO `admin_info` VALUES (2,'root','202cb962ac59075b964b07152d234b70');
/*!40000 ALTER TABLE `admin_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_his_info`
--

DROP TABLE IF EXISTS `book_his_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_his_info` (
                                 `hid` int NOT NULL AUTO_INCREMENT,
                                 `aid` int DEFAULT NULL,
                                 `bid` varchar(205) DEFAULT NULL,
                                 `card` varchar(205) DEFAULT NULL,
                                 `book_name` varchar(205) DEFAULT NULL,
                                 `admin_name` varchar(205) DEFAULT NULL,
                                 `username` varchar(205) DEFAULT NULL,
                                 `begin_time` date DEFAULT NULL,
                                 `end_time` date DEFAULT NULL,
                                 `status` int DEFAULT NULL,
                                 PRIMARY KEY (`hid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_his_info`
--

LOCK TABLES `book_his_info` WRITE;
/*!40000 ALTER TABLE `book_his_info` DISABLE KEYS */;

-- 插入借书历史记录
INSERT INTO `book_his_info` (`aid`, `bid`, `card`, `book_name`, `admin_name`, `username`, `begin_time`, `end_time`, `status`)
VALUES
    (12, 4, '108', '悲伤的物理学', 'ink', '王小明', '2024-10-10', '2024-10-25', 2),  -- 借书：2024年10月10日，归还：2024年10月25日
    (13, 4, '109', '凡人琐事:我的回忆', 'ink', '王小明', '2024-10-15', '2024-10-30', 2),  -- 借书：2024年10月15日，归还：2024年10月30日
    (14, 4, '110', '康熙的红票:全球化中的清朝', 'ink', '王小明', '2024-11-01', '2024-11-16', 2),  -- 借书：2024年11月1日，归还：2024年11月16日
    (18, 4, '114', '璩家花园', 'ink', '王小明', '2024-12-20', '2025-01-05', 1);  -- 借书：2024年12月5日，归还：2024年12月20日

/*!40000 ALTER TABLE `book_his_info` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `book_type_info`
--

DROP TABLE IF EXISTS `book_type_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_type_info` (
                                  `tid` int NOT NULL AUTO_INCREMENT,
                                  `name` varchar(20) DEFAULT NULL,
                                  PRIMARY KEY (`tid`),
                                  UNIQUE KEY `book_type_info_pk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_type_info`
--

LOCK TABLES `book_type_info` WRITE;
/*!40000 ALTER TABLE `book_type_info` DISABLE KEYS */;
INSERT INTO `book_type_info` VALUES (69,'文学小说'),(70,'杂志期刊'),(87,'测试'),(86,'编程语言'),(85,'计算机丛书');
/*!40000 ALTER TABLE `book_type_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books_info`
--

DROP TABLE IF EXISTS `books_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE `books_info` (
                              `bid` int NOT NULL AUTO_INCREMENT,
                              `name` varchar(205) NOT NULL,
                              `card` varchar(205) NOT NULL,
                              `author` varchar(205) DEFAULT NULL,
                              `num` int NOT NULL,
                              `press` varchar(205) DEFAULT NULL,
                              `type` varchar(205) DEFAULT NULL,
                              `status` int DEFAULT '2',
                              `publish_date` DATE DEFAULT NULL COMMENT '出版日期',
                              PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `books_info`
LOCK TABLES `books_info` WRITE;
/*!40000 ALTER TABLE `books_info` DISABLE KEYS */;

-- 插入数据并指定出版日期
INSERT INTO `books_info` (`bid`, `name`, `card`, `author`, `num`, `press`, `type`, `status`, `publish_date`)
VALUES
    (106, '原则', '1006', '雷·达里奥', 1, '中信出版社', '计算机丛书', 2, '2017-09-19'), -- 计算机丛书/自我管理（这里选择计算机丛书作为类型）
    (107, '三体', '1007', '刘慈欣', 1, '重庆出版社', '文学小说', 2, '2006-05-01'), -- 科幻小说（归为文学小说）
    (108, '悲伤的物理学', '1008', '格奥尔格·戈斯波丁诺夫', 1, '上海人民出版社', '文学小说', 2, '2024-10-01'), -- 文学小说（2024年新出版）
    (109, '凡人琐事:我的回忆', '1009', '章开沅', 1, '广西师范大学出版社', '文学小说', 2, '2024-10-01'), -- 文学小说（回忆录，2024年新出版）
    (110, '康熙的红票:全球化中的清朝', '1010', '孙立天', 1, '商务印书馆', '文学小说', 2, '2024-03-01'), -- 文学小说（历史题材，2024年新出版）
    (111, '满世界寻找敦煌', '1011', '荣新江', 1, '中华书局', '文学小说', 2, '2024-05-01'), -- 文学小说（旅行文学，2024年新出版）
    (112, '爱因斯坦陪审团', '1012', '杰弗里·克林斯滕', 1, '湖南科学技术出版社', '文学小说', 2, '2023-11-01'), -- 文学小说（科学题材，2023年新出版）
    (113, '奥本海默传:美国“原子弹之父”的胜利与悲剧', '1013', '凯·伯德、马丁·J.舍温', 1, '中信出版集团·漫游者', '文学小说', 2, '2023-08-01'), -- 文学小说（传记，2023年新出版）
    (114, '璩家花园', '1014', '叶兆言', 1, '译林出版社', '文学小说', 1, '2024-09-01'); -- 文学小说（2024年新出版）


/*!40000 ALTER TABLE `books_info` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
                             `aid` int NOT NULL AUTO_INCREMENT,
                             `username` varchar(20) NOT NULL,
                             `name` varchar(20) DEFAULT NULL,
                             `password` varchar(64) NOT NULL,
                             `email` varchar(255) DEFAULT NULL,
                             `phone` varchar(20) DEFAULT NULL,
                             `status` int DEFAULT '1',
                             `lend_num` int DEFAULT '30' COMMENT '可借阅天数',
                             `max_num` int DEFAULT '15' COMMENT '可以借阅数量',
                             PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (4,'ink','王小明','1d2bcbf67ddeecc2c1654b4af76b9c11','1617562300@qq.com','15877666111',1,30,15);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-22 18:09:49
