-- �������ݿ�����飬���� GOODS ��

CREATE TABLE GOODS
(
  gid    INT(10) primary key AUTO_INCREMENT,
  gname  VARCHAR(20)   NOT NULL UNIQUE,
  gprice DOUBLE(18, 2) NOT NULL,
  gnum   INT(11)       NOT NULL
);

