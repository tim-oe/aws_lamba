-- sudo -u postgres psql
-- create database lambda;
-- ALTER DATABASE lambda OWNER TO lambda;
-- create user lambda with encrypted password 'lambda';
-- https://poanchen.github.io/blog/2018/03/07/How-to-fix-permission-denied-for-relation-some_table_name-in-PostgreSQL
-- this needs to be run after table is created
-- ALTER TABLE ps_test OWNER TO lambda;
-- grant all privileges on database lambda to lambda;

CREATE TABLE ps_test
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100) NOT NULL UNIQUE,
    value VARCHAR(100) NOT NULL
);
