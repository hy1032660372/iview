DROP TABLE IF EXISTS account;
CREATE TABLE `account` (
  `id` char(32) NOT NULL COMMENT ' 主键 id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
