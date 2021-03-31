CREATE TABLE maindata
(
notice VARCHAR2(1000),
meetingSchedule VARCHAR2(1000)
);

commit;

DROP TABLE maindata;

insert into maindata(notice,meetingSchedule)
values('°¡ÁÂ','Á¤¸ð°¡ÁÂ');


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

DROP TABLE noticeboarddata;

insert into noticeboarddata(type,writer,time,title,contents,reply)
values(1,'aaa','2020-07-01 18:18:18','°¡°¡°¡','·¹µð¿À','´ñ±Û');


SELECT * FROM noticeboarddata;