# chattingProject (채팅을 활용한 모임커뮤니티 어플 프로젝트)<br>
## 개발환경
- JDK1.8
- TOMCAT 8.5
- eclipse photon버전
- OracleDB
- 인코딩(UTF-8)
***

## OracleDriverManager 설정파일
- DBConn.java
- <br>

## Oracle테이블생성
```SQL
CREATE TABLE maindata
(
notice VARCHAR2(1000),
meetingSchedule VARCHAR2(1000)
);

commit;

DROP TABLE maindata;

insert into maindata(notice,meetingSchedule)
values('메인화면','규칙');


SELECT * FROM maindata;


CREATE TABLE memberdata
(
    mid varchar2(20) CONSTRAINT memberdata_id_pk PRIMARY key,
    mname varchar2(20),
    pw varchar2(20),
    gender number(1),
    phoneNum VARCHAR2(12),
    madmin number(5),
    chatlog VARCHAR2(20)
);

commit;

DROP TABLE memberdata;

insert into memberdata(mid,mname,pw,gender,phoneNum,madmin,chatlog)
values('aaa','a','1234',1,'01011111111',1,'aaaaa');
insert into memberdata(mid,mname,pw,gender,phoneNum,madmin,chatlog)
values('bbb','b','1234',0,'01012341234',1,'bbbbb');

SELECT * FROM memberdata;



CREATE TABLE noticeboarddata
(
type number(1),
writer VARCHAR2(20),
time VARCHAR2(30),
title VARCHAR2(50),
contents VARCHAR2(1000),
reply VARCHAR2(1000)
);

commit;
```
<br>
