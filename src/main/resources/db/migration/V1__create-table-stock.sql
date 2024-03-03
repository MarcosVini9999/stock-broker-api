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
    ('empresa1', '12345678901234', 1500000.75, 'ABC Tech', 'Brazilian', 'ABCT2');

INSERT INTO company (id, cnpj, capital, name, nationality, ticker) VALUES
    ('empresa2', '98765432109876', 1200000.50, 'XYZ Pharma', 'Brazilian', 'XYZP2');

INSERT INTO company (id, cnpj, capital, name, nationality, ticker) VALUES
    ('empresa3', '55555555555555', 800000.25, 'Global Motors', 'Brazilian', 'XYZP2');


# INSERT INTO contact (id, firstname, lastname, phone, email, mobile, remark) VALUES
#     (11, 'Lucas', 'Mendes', '9924-4576', 'cuida@gmail.com','cururu', 'hahaha');

# INSERT INTO contact (id, firstname, lastname, phone, email, mobile, remark) VALUES
#     (18, 'Bento', 'Sasuke', '9925-5673', 'manfs@gmail.com','sapin', 'hehehe');
#
# INSERT INTO contact (id, firstname, lastname, phone, email, mobile, remark) VALUES
#     (5, 'Marcia', 'Rebeca', '9821-4566', 'rebeca@gmail.com','tegio', 'hihihi');