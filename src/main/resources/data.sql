insert ignore into tb_roles (role_id, name) values (1, 'ANALISTA');
insert ignore into tb_roles (role_id, name) values (2, 'REVISOR');
insert ignore into tb_roles (role_id, name) values (3, 'APROVADOR');


insert ignore into tb_status (status_id, name) values (1, 'AGUARDANDO_ANALISE');
insert ignore into tb_status (status_id, name) values (2, 'EM_ANALISE');
insert ignore into tb_status (status_id, name) values (3, 'AGUARDANDO_REVISAO');
insert ignore into tb_status (status_id, name) values (4, 'EM_REVISAO');
insert ignore into tb_status (status_id, name) values (5, 'AGUARDANDO_APROVACAO');
insert ignore into tb_status (status_id, name) values (6, 'EM_APROVACAO');
insert ignore into tb_status (status_id, name) values (7, 'APROVADO');
insert ignore into tb_status (status_id, name) values (8, 'RECUSADO');


insert ignore into mydb.tb_users
(id, email, name, password, role_id)
values('a', 'analista@gmail.com', 'analista', '$2a$10$7kuuyuqiyouqynkdpzztzocdptlxnlaeo3k1lvzsmm6qn0btns7tg', 1);

insert ignore into mydb.tb_users
(id, email, name, password, role_id)
values('b', 'revisor@gmail.com', 'revisor', '$2a$10$7kuuyuqiyouqynkdpzztzocdptlxnlaeo3k1lvzsmm6qn0btns7tg', 2);

insert ignore into mydb.tb_users
(id, email, name, password, role_id)
values('c', 'aprovador1@gmail.com', 'aprovador1', '$2a$10$7kuuyuqiyouqynkdpzztzocdptlxnlaeo3k1lvzsmm6qn0btns7tg', 3);

insert ignore into mydb.tb_users
(id, email, name, password, role_id)
values('d', 'aprovador2@gmail.com', 'aprovador2', '$2a$10$7kuuyuqiyouqynkdpzztzocdptlxnlaeo3k1lvzsmm6qn0btns7tg', 3);

insert ignore into mydb.tb_users
(id, email, name, password, role_id)
values('e', 'aprovador3@gmail.com', 'aprovador3', '$2a$10$7kuuyuqiyouqynkdpzztzocdptlxnlaeo3k1lvzsmm6qn0btns7tg', 3);
