/*
SQLyog Ultimate v8.32 
MySQL - 5.1.71-community : Database - book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `book`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(50) DEFAULT NULL,
  `ausername` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `touxiang` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`aid`,`aname`,`ausername`,`password`,`phone`,`touxiang`) values (24,'张三','zhangsan','lx123456789','13195547850','/c/d/09263eea-e933-442b-acce-95b128deb784_1d1.jpg'),(25,'于一辰','yyc123','qwerqwer1','17856770965','/2/2/19c3d20f-08bc-4522-9f94-606d8413ec51_u=2438045982,1929993786&fm=26&gp=0.jpg'),(26,'王旭','wangxu','1234567a','13556489234','/8/3/8e926c1e-a73b-4b38-8ab3-2c1bd3252605_u=2659731809,1719435190&fm=26&gp=0.jpg'),(27,'王冰玉','wangbingyu','w123456','13556489234','/2/d/203504f1-5963-4344-84bd-23ca68b911e0_2.png'),(31,'柳松','dfs','s123456','13856751613','upload/4/7/670c5fed-3e8a-4d29-a22f-3de0e184333f_05405U3E-4.jpg'),(38,'淡定','aaaa','a123456','13856751613','upload/1/1/6d3bc41f-b4b8-4168-a849-c357d8841f33_22025I2Y-3.jpg'),(39,'安安','liu','a12345','13556489234','upload/c/d/f3202225-6ff9-46e5-b330-56d3882f1016_1d1.jpg');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `flId` int(100) DEFAULT NULL,
  `bname` varchar(50) DEFAULT NULL,
  `money` varchar(30) DEFAULT NULL,
  `press` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `stock` int(100) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `FK_book` (`flId`),
  CONSTRAINT `FK_book` FOREIGN KEY (`flId`) REFERENCES `fenlei` (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`bid`,`flId`,`bname`,`money`,`press`,`author`,`stock`) values (140,3,'植物图鉴','56','上海科学出版社','cccc',9),(141,4,'人与动物','28','上海科学出版社','dddd',9),(145,5,'JAVA从入门到精通','68','清华大学出版社','hhhh',2),(146,1,'浩瀚的宇宙','52','清华大学出版社','iiii',0),(153,13,'安徽地理','52','清华大学出版社','杨丽娜',10),(154,13,'简单地质2','42','北京大学出版社','尔新',11),(155,13,'中国地质分析','156','北京大学出版社','大范甘迪',52),(156,13,'中国地理','52','中国文化出版社','发给',11),(158,13,'中国美丽城市','56','中国文化出版社','爱仕达',10),(160,4,'天王星','52','北京大学出版社','阿萨德',11),(161,10,'植物生态环境','111','北京大学出版社','阿萨德',5);

/*Table structure for table `bt` */

DROP TABLE IF EXISTS `bt`;

CREATE TABLE `bt` (
  `btid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`btid`),
  KEY `FK_bt1` (`bookid`),
  KEY `FK_bt` (`userid`),
  CONSTRAINT `FK_bt` FOREIGN KEY (`userid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_bt1` FOREIGN KEY (`bookid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `bt` */

insert  into `bt`(`btid`,`userid`,`bookid`,`time`) values (1,1,145,'2019-03-12'),(2,33,155,'2019-03-15'),(3,1,155,'2019-05-06'),(4,1,158,'2019-05-31'),(6,1,158,'2019-05-31'),(7,1,158,'2019-05-31'),(8,1,158,'2019-05-31'),(9,1,140,'2019-05-31'),(10,1,141,'2019-05-31');

/*Table structure for table `fenlei` */

DROP TABLE IF EXISTS `fenlei`;

CREATE TABLE `fenlei` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `fenlei` */

insert  into `fenlei`(`fid`,`fname`) values (1,'卫生学2'),(2,'物理学'),(3,'数学'),(4,'天文学'),(5,'计算机科学'),(6,'国学'),(7,'经典名著'),(8,'生化学'),(9,'自然科学'),(10,'植物学'),(11,'运动康复学'),(13,'地质学'),(16,'aaaaw2');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `regtime` varchar(50) DEFAULT NULL,
  `touxiang` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`uname`,`username`,`password`,`phone`,`regtime`,`touxiang`) values (1,'李四','lisi','123456','123456789','2019-3-19','upload/c/d/6d48f861-a83a-4b6c-b5b6-fc781e42f259_1d1.jpg'),(33,'小白','liuyv','123456','18759679881','2019-05-06','upload/6/d/f7ce19e8-511a-48fc-adbf-36075c020467_dog.png'),(35,'伏可','fuke','123456qwe','18759679881','2019-05-29','upload/3/f/93b1edf0-b86c-4864-88ea-ef74a4d6add0_002.jpg'),(36,'阿紫','aizi','aizi1123456','18759679881','2019-05-07','upload/3/f/7076493d-ef09-4c3a-94a6-0a3414527814_001.jpg'),(38,'王晓敏','wangxiaomin','wangxiaomin1234','18759679881','2019-05-13','upload/4/5/ba49374b-a0fc-47b7-b2fe-3d7a71cf83f5_037.jpg'),(39,'任明玉','renmingyv','renmingyu123','18759679881','2019-05-13','upload/4/0/39b6587b-1e89-409d-bed5-ec65547ce676_006.jpg'),(40,'李辰笛','lichendi','lichendi123333','18759679881','2019-05-12','upload/a/e/572b4747-9f51-4bea-bae3-60659a9e8a4a_15150618_230525266181_2.jpg'),(41,'胡八一','hubayi','hubayi123','18759679881','2019-05-12','upload/6/6/f75f5bbf-bd65-4573-ae0e-86ba0ca64084_038.jpeg'),(42,'小五','xiaowu','xiaowu123','18759679881','2019-05-05','upload/5/5/7fca7003-850d-4b7c-a1ed-d30aa80258b5_dag.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
