CREATE TABLE `img` (
  `img_id` int(11) NOT NULL auto_increment,
  `imgname` varchar(255) NOT NULL,
  `imgdata` mediumblob NOT NULL,
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#使用mediumblob类型可以存储更大的图片内容