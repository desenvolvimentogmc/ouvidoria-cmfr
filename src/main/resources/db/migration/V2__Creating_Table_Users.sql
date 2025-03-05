CREATE TABLE `tb_users` (
  `username` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `must_update_password` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
)