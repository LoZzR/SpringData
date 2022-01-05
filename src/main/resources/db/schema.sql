drop table PERSON if exists;

CREATE TABLE PERSON
(
    ID INT PRIMARY KEY
    , FIRSTNAME VARCHAR2(50)
    , USERNAME VARCHAR2(50) NOT NULL
    , LASTNAME VARCHAR2(50)
    , PASSWORD VARCHAR2(50) NOT NULL
    , ADDRESS VARCHAR2(100)
    , EMAIL VARCHAR2(50) NOT NULL
    , HIRINGDATE VARCHAR2(50)
    , UNIQUE(EMAIL)
    , UNIQUE(USERNAME)
);