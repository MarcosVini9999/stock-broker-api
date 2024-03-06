CREATE DATABASE IF NOT EXISTS mandacaru_broker;
USE mandacaru_broker;




CREATE TABLE IF NOT EXISTS company (
                                       id VARCHAR(255) PRIMARY KEY,
                                       cnpj VARCHAR(255),
                                       capital FLOAT(10),
                                       name VARCHAR(255),
                                       nationality VARCHAR(255),
                                       ticker VARCHAR(255)
);




INSERT INTO company (id, cnpj, capital, name, nationality, ticker) VALUES
    ('empresa1', '12345678901234', 1500000.75, 'ABC Tech', 'Brazilian', 'ABCT');

INSERT INTO company (id, cnpj, capital, name, nationality, ticker) VALUES
    ('empresa2', '98765432109876', 1200000.50, 'XYZ Pharma', 'Brazilian', 'XYZP');

INSERT INTO company (id, cnpj, capital, name, nationality, ticker) VALUES
    ('empresa3', '55555555555555', 800000.25, 'Global Motors', 'Brazilian', 'DDDW');


-- Criação da tabela
CREATE TABLE stock (
                       id VARCHAR(255) PRIMARY KEY,
                       companyid VARCHAR(255),
                       details VARCHAR(255),
                       price FLOAT(10),
                       symbol VARCHAR(255),
                       variation FLOAT(10)
);

INSERT INTO stock (id, companyid, details, price, symbol, variation)
VALUES
    ('1', 'empresa1', 'Detalhes do estoque 1', 10.5, 'ABCT1', 0.2),
    ('2', 'empresa2', 'Detalhes do estoque 2', 20.3, 'XYZP2', -0.5),
    ('3', 'empresa3', 'Detalhes do estoque 3', 15.8, 'DDDW3', 0.1),
    ('4', 'empresa1', 'Detalhes do estoque 4', 8.2, 'ABCT4', -0.3),
    ('5', 'empresa2', 'Detalhes do estoque 5', 12.6, 'XYZP5', 0.4),
    ('6', 'empresa3', 'Detalhes do estoque 6', 18.7, 'DDDW6', -0.6),
    ('7', 'empresa1', 'Detalhes do estoque 7', 14.3, 'ABCT2', 0.2),
    ('8', 'empresa2', 'Detalhes do estoque 8', 22.1, 'XYZP2', -0.4),
    ('9', 'empresa3', 'Detalhes do estoque 9', 9.5, 'DDDW3', 0.3),
    ('10', 'empresa1', 'Detalhes do estoque 10', 16.9, 'ABCT4', -0.1);
-- Criação da tabela 'user'
CREATE TABLE user (
                      id INT (5) PRIMARY KEY,
                      firstname VARCHAR(255),
                      lastname VARCHAR(255),
                      username VARCHAR(255),
                      password VARCHAR(255),
                      confirmpassword VARCHAR(255)
);

-- Criação da tabela 'role'
CREATE TABLE role (
                      id INT (5) PRIMARY KEY,
                      created_by VARCHAR(255),
                      created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                      last_modified_by VARCHAR(255),
                      last_modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      description VARCHAR(255),
                      details VARCHAR(255)
);

-- Criação da tabela 'user_role' (relacionamento entre user e role)
CREATE TABLE user_role (
                           user_id INT,
                           role_id INT,
                           PRIMARY KEY (user_id, role_id),
                           FOREIGN KEY (user_id) REFERENCES user(id),
                           FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO user (id,firstname, lastname, username, password, confirmpassword)
VALUES
    (1,'John', 'Doe', 'Admin1', 'senha123', 'senha123'),
    (2,'Jane', 'Smith', 'Admi2', 'senha234', 'senha234'),
    (3,'Bob', 'Johnson', 'Admin3', 'senha345', 'senha345'),
    (4,'Sergio', 'Cassio', 'user1', 'senha456', 'senha456'),
    (5,'Ted', 'Gentil', 'user2', 'senha567', 'senha567'),
    (6,'Ben', 'Watson', 'user3', 'senha678', 'senha678'),
    (7,'Luffy', 'Mugiwara', 'user4', 'senha789', 'senha789'),
    (8,'Naruto', 'Uzumaki', 'user5', 'senha8910', 'senha8910');

-- Inserção de dados na tabela 'role'
INSERT INTO role (id, created_by, description, details)
VALUES
    (1, 'admin', 'ADMIN', 'Detalhes da função de administrador'),
    (2,'admin', 'USER', 'Detalhes da função de editor');

INSERT INTO user_role (user_id, role_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 2),
    (8, 2);
