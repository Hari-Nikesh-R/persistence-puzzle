DROP TABLE IF EXISTS person;

CREATE TABLE person (
    id SERIAL NOT NULL,
    name VARCHAR,
    PRIMARY KEY ( id )
);