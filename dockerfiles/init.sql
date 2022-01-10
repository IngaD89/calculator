DROP TABLE IF EXISTS historydb;
CREATE TABLE historydb (
    id serial  PRIMARY KEY ,
    operation varchar(255) NOT NULL,
    x DOUBLE PRECISION NOT NULL,
    y DOUBLE PRECISION NOT NULL,
    result DOUBLE PRECISION NOT NULL,
    dateTime TIMESTAMP DEFAULT now() NOT NULL

);


INSERT INTO historydb(id, operation, x, y, result, dateTime)
VALUES (1, '+', 2.3, 3.4, 5.7, '2021-09-21T10:15:30'),
       (2, '+', 2.5, 3.5, 6.0, '2021-09-21T10:15:30');