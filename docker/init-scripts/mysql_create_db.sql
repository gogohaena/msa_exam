CREATE DATABASE msa_exam_auth;
CREATE USER 'msa_exam_auth'@'%' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON msa_exam_auth.* TO 'msa_exam_auth'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE msa_exam_product;
CREATE USER 'msa_exam_product'@'%' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON msa_exam_product.* TO 'msa_exam_product'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE msa_exam_order;
CREATE USER 'msa_exam_order'@'%' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON msa_exam_order.* TO 'msa_exam_order'@'%';
FLUSH PRIVILEGES;
