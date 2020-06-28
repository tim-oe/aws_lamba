CREATE TABLE my_test
(
    id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    name  VARCHAR(100) NOT NULL,
    value VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY name_unq (name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;