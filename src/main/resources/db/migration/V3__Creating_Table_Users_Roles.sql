CREATE TABLE `tb_users_roles` (
  `tb_users_username` varchar(255) NOT NULL,
  `roles_name` varchar(255) NOT NULL,
  KEY `FKhwlu2uu232vctxc6h56toc5ch` (`roles_name`),
  KEY `FK396k5a3scg2oji28kiqlv5l3v` (`tb_users_username`),
  CONSTRAINT `FK396k5a3scg2oji28kiqlv5l3v` FOREIGN KEY (`tb_users_username`) REFERENCES `tb_users` (`username`),
  CONSTRAINT `FKhwlu2uu232vctxc6h56toc5ch` FOREIGN KEY (`roles_name`) REFERENCES `tb_roles` (`name`)
)