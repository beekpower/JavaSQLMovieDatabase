-- MySQL dump 10.13  Distrib 5.6.27, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: movies
-- ------------------------------------------------------
-- Server version	5.6.27-0ubuntu0.15.04.1

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
-- Table structure for table `actors`
--

DROP TABLE IF EXISTS `actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actors` (
  `actor_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `gender` varchar(1) NOT NULL,
  PRIMARY KEY (`actor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actors`
--

LOCK TABLES `actors` WRITE;
/*!40000 ALTER TABLE `actors` DISABLE KEYS */;
INSERT INTO `actors` VALUES (17,'Tobey','Maguire','M'),(18,'Willem ','Dafoe','M'),(19,'Kirsten ','Dunst','F'),(20,'James','Franco','M'),(21,'Shia','LaBeouf','M'),(22,'Tyrese','Gibson','M'),(23,'Josh','Duhamel','M'),(24,'Anthony','Anderson','M'),(25,'Megan','Fox','F'),(26,'Johnny','Knoxville','M'),(27,'Alan','Ritchson','M'),(28,'Noel','Fisher','M'),(29,'Sam ','Worthington','M'),(30,'Zoe','Saldana','F'),(31,'Stephen','Lang','M'),(32,'Andrew','Strelke','M'),(33,'Nick','VanBeek','M'),(34,'Tom','Cruise','M');
/*!40000 ALTER TABLE `actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directors`
--

DROP TABLE IF EXISTS `directors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directors` (
  `director_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `gender` varchar(1) NOT NULL,
  PRIMARY KEY (`director_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directors`
--

LOCK TABLES `directors` WRITE;
/*!40000 ALTER TABLE `directors` DISABLE KEYS */;
INSERT INTO `directors` VALUES (3,'Andrew','Strelke','M'),(4,'Nick','VanBeek','M'),(5,'Darius','Bowens','F'),(9,'Tyler','Scacca','M'),(11,'Sam','Raimi','M'),(13,'James','Cameron','M'),(14,'Michael','Bay','M'),(16,'John','Lancey','M');
/*!40000 ALTER TABLE `directors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_actors`
--

DROP TABLE IF EXISTS `movie_actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_actors` (
  `movie_id` int(10) unsigned NOT NULL,
  `actor_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`movie_id`,`actor_id`),
  KEY `movie_actors_actors_FK` (`actor_id`),
  CONSTRAINT `movie_actors_actors_FK` FOREIGN KEY (`actor_id`) REFERENCES `actors` (`actor_id`) ON DELETE CASCADE,
  CONSTRAINT `movie_actors_movies_FK` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_actors`
--

LOCK TABLES `movie_actors` WRITE;
/*!40000 ALTER TABLE `movie_actors` DISABLE KEYS */;
INSERT INTO `movie_actors` VALUES (36,17),(36,18),(36,19),(44,19),(32,21),(35,22),(44,22),(32,24),(32,25),(35,25),(35,26),(44,26),(33,28),(33,29),(35,29),(33,30),(33,31),(34,32),(37,32),(34,33),(37,33);
/*!40000 ALTER TABLE `movie_actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movies` (
  `movie_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `parental_rating` varchar(5) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `duration` int(10) unsigned NOT NULL,
  `genre` text NOT NULL,
  `director_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `movies_directors_FK` (`director_id`),
  CONSTRAINT `movies_directors_FK` FOREIGN KEY (`director_id`) REFERENCES `directors` (`director_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (32,'Transformers','PG',2009,2,'Acton',14),(33,'Avatar','PG',2012,2,'Adventure',14),(34,'Terminator','R',1984,2,'Action',4),(35,'Teenage Mutant Ninga Turtle','PG',2013,2,'Action',11),(36,'Spider-Man','PG',2002,2,'Action/Adventure',5),(37,'Hercules','G',1994,2,'Childrens',16),(44,'3','3',3,3,'3',3);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `review_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `movie_id` int(10) unsigned NOT NULL,
  `author` text NOT NULL,
  `title` text NOT NULL,
  `review` text NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `reviews_movies_FK` (`movie_id`),
  CONSTRAINT `reviews_movies_FK` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,44,'New label','New label','','2015-12-11 21:37:34'),(2,44,'','','','2015-12-11 21:37:37'),(3,44,'','','','2015-12-11 21:37:39'),(4,44,'','','','2015-12-11 21:37:42'),(5,44,'','','','2015-12-11 21:37:44'),(6,44,'','','','2015-12-11 21:37:46'),(7,44,'','','','2015-12-11 21:37:46'),(8,44,'','','','2015-12-11 21:37:51'),(9,44,'','','','2015-12-11 21:37:52'),(10,44,'','','','2015-12-11 21:37:52'),(11,44,'','','','2015-12-11 21:37:52'),(12,44,'','','','2015-12-11 21:37:53'),(13,44,'','','','2015-12-11 21:37:55'),(14,44,'','','','2015-12-11 21:37:55'),(15,44,'','','','2015-12-11 21:37:56'),(16,44,'','','','2015-12-11 21:37:56'),(17,44,'','','','2015-12-11 21:37:57'),(18,44,'','','','2015-12-11 21:37:57'),(20,32,'','','','2015-12-11 22:09:52'),(21,32,'','','','2015-12-11 22:09:55'),(28,32,'','','','2015-12-11 22:46:41'),(32,32,'d','d','d','2015-12-11 22:52:00');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'movies'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-11 23:50:32
