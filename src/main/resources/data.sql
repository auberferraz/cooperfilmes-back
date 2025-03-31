CREATE TABLE IF NOT EXISTS tb_roles(role_id BIGINT PRIMARY KEY, name VARCHAR(255));
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (1, 'ANALISTA');
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (2, 'REVISOR');
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (3, 'APROVADOR');