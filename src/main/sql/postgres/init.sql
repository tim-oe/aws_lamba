-- sudo -u postgres psql
-- create database multi;
-- ALTER DATABASE multi OWNER TO multi;
-- create user multi with encrypted password 'multi';
-- https://poanchen.github.io/blog/2018/03/07/How-to-fix-permission-denied-for-relation-some_table_name-in-PostgreSQL
-- this needs to be run after table is created
-- ALTER TABLE ps_test OWNER TO multi;
-- grant all privileges on database multi to multi;

CREATE TABLE ps_test
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100) NOT NULL UNIQUE,
    value VARCHAR(100) NOT NULL,
    created_on TIMESTAMP DEFAULT NOW()
);
