create database login;
use login;
create table logged
(
usr char(10),
pwd varchar(10),
role char(10)
);
insert into logged values('1','abc','student');
insert into logged values('2','def','staff');
insert into logged values('3','ghi','admin');

select * from logged;

create table staff
(
class char(10),
yr int,
peroid int,
attendance char(20)
);

select * from staff;


