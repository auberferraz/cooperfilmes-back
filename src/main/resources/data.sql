CREATE TABLE IF NOT EXISTS tb_roles(role_id BIGINT PRIMARY KEY, name VARCHAR(255));
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (1, 'analista');
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (2, 'revisor');
INSERT IGNORE INTO tb_roles (role_id, name) VALUES (3, 'aprovador');