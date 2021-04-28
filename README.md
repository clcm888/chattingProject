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

CREATE TABLE noticeboarddata
(
type number(1),
writer VARCHAR2(20),
time VARCHAR2(30),
title VARCHAR2(50),
contents VARCHAR2(1000),
reply VARCHAR2(1000)
);

insert into maindata(notice,meetingSchedule)
values('분위기흐리기 금지!! 기분좋게 친목나눠요','2021-04-28 정모!!');

insert into memberdata(mid,mname,pw,gender,phoneNum,madmin,chatlog)
values('aaa','a','1234',1,'01011111111',1,'aaaaa');
insert into memberdata(mid,mname,pw,gender,phoneNum,madmin,chatlog)
values('bbb','b','1234',0,'01012341234',1,'bbbbb');

insert into noticeboarddata(type,writer,time,title,contents,reply)
values(1,'aaa','2020-07-01 18:18:18','4월28일 정모일정공지','참가 희망하시면 메세지 부탁드립니다.','');

commit;
```
<br>

![캡처](https://user-images.githubusercontent.com/81599227/116409212-93888a00-a86e-11eb-9e41-16666e298d4b.PNG)

