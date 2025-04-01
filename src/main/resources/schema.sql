create table if not exists tb_roles(role_id bigint primary key, name varchar(255));

create table if not exists tb_status(status_id bigint primary key, name varchar(255));


-- mydb.tb_users definition

CREATE TABLE if not exists `tb_users` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_grd22228p1miaivbn9yg178pm` (`email`),
  KEY `FK2bwdk5w4y4ehty96tvk2w66ow` (`role_id`),
  CONSTRAINT `FK2bwdk5w4y4ehty96tvk2w66ow` FOREIGN KEY (`role_id`) REFERENCES `tb_roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

