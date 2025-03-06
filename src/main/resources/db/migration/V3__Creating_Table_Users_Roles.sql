CREATE TABLE `tb_users_roles` (
  `tb_users_username` VARCHAR(255) NOT NULL,
  `roles_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`tb_users_username`, `roles_name`), 
  INDEX `idx_roles_name` (`roles_name`),
  INDEX `idx_users_username` (`tb_users_username`),
  CONSTRAINT `FK_tb_users_roles_users`
    FOREIGN KEY (`tb_users_username`) REFERENCES `tb_users` (`username`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tb_users_roles_roles`
    FOREIGN KEY (`roles_name`) REFERENCES `tb_roles` (`name`)
    ON DELETE CASCADE ON UPDATE CASCADE
);
