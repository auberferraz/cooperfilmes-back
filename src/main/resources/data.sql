CREATE TABLE IF NOT EXISTS tb_roles(role_id BIGINT PRIMARY KEY, name VARCHAR(255));
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (1, 'ANALISTA');
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (2, 'REVISOR');
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (3, 'APROVADOR');

CREATE TABLE IF NOT EXISTS tb_status(status_id BIGINT PRIMARY KEY, name VARCHAR(255));
INSERT IGNORE INTO tb_status (status_id, name) VALUES (1, 'AGUARDANDO_ANALISE');
INSERT IGNORE INTO tb_status (status_id, name) VALUES (2, 'EM_ANALISE');
INSERT IGNORE INTO tb_status (status_id, name) VALUES (3, 'AGUARDANDO_REVISAO');
INSERT IGNORE INTO tb_status (status_id, name) VALUES (4, 'EM_REVISAO');
INSERT IGNORE INTO tb_status (status_id, name) VALUES (5, 'AGUARDANDO_APROVACAO');
INSERT IGNORE INTO tb_status (status_id, name) VALUES (6, 'EM_APROVACAO');
INSERT IGNORE INTO tb_status (status_id, name) VALUES (7, 'APROVADO');
INSERT IGNORE INTO tb_status (status_id, name) VALUES (8, 'RECUSADO');