DROP DATABASE IF EXISTS shortener_db;

CREATE DATABASE shortener_db;

USE shortener_db;

CREATE TABLE tb_user (
	login VARCHAR(20) PRIMARY KEY,
	senha VARCHAR(256) NOT NULL
);

CREATE TABLE tb_link (
	curto VARCHAR(12) PRIMARY KEY,
	original VARCHAR(256) NOT NULL,
	user_login VARCHAR(20),
	FOREIGN KEY (user_login) REFERENCES tb_user(login)
);

CREATE TABLE tb_access (
	link VARCHAR(12),
	endereco_ip VARCHAR(15),
	FOREIGN KEY (link) REFERENCES tb_link(curto) ON DELETE CASCADE ON UPDATE CASCADE
);