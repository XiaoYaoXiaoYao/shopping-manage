
CREATE TABLE gsales
(
       gsid  INT(10) PRIMARY KEY AUTO_INCREMENT,
       gid   INT(10) REFERENCES goods (gid)    NOT NULL,
       sid   INT(10) REFERENCES salesman (sid) NOT NULL,
       sdate DATE DEFAULT SYSDATE              NOT NULL,
       snum  INT(11)                           NOT NULL
);

