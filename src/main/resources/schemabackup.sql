--DROP TABLE USER;
--CREATE TABLE IF NOT EXISTS USER (ID INT PRIMARY KEY, USERNAME VARCHAR(30), EMAIL VARCHAR(30),PASSWORD VARCHAR(30) );
drop table authorities ;
drop table users;

create table users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	email varchar(100) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);