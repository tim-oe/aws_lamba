-- create database multi;
-- CREATE USER multi IDENTIFIED BY 'multi';
-- GRANT ALL PRIVILEGES ON *.* TO 'multi'@'%' WITH GRANT OPTION;
-- FLUSH PRIVILEGES;

CREATE TABLE my_test
(
    id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    name  VARCHAR(100) NOT NULL,
    value VARCHAR(100) NOT NULL,
    created_on timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY name_unq (name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;