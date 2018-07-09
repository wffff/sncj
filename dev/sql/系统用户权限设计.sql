--------用户表
DROP SEQUENCE IF EXISTS seq_user CASCADE;
CREATE SEQUENCE seq_user;
DROP TABLE IF EXISTS t_user CASCADE;
create table t_user
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('seq_user'),
  username varchar(100)                                          not null,
  password varchar(100)                                         not null,
  mobile   varchar(20),
  email    varchar(50),
  name     varchar(50),
  enabled  boolean      default true,
  expired  boolean      default false,
  locked   boolean      default false,
  limited  boolean      default false,
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--------角色表
DROP SEQUENCE IF EXISTS seq_role CASCADE;
CREATE SEQUENCE seq_role;
DROP TABLE IF EXISTS t_role CASCADE;
create table t_role
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('seq_role'),
  name     varchar(50),
  description    varchar(50),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--------用户角色表
DROP TABLE IF EXISTS t_user_role CASCADE;
create table t_user_role
(
  user_id        INTEGER NOT NULL ,
  role_Id        INTEGER NOT NULL
);


--------资源表
DROP SEQUENCE IF EXISTS seq_permission CASCADE;
CREATE SEQUENCE seq_permission;
DROP TABLE IF EXISTS t_permission CASCADE;
create table t_permission
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('seq_permission'),
  name     varchar(50),
  description    varchar(50),
  url    varchar(50),
  pid    INTEGER,
  method    varchar(50),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--------用户资源表
DROP TABLE IF EXISTS t_role_permission CASCADE;
create table t_role_permission
(
  role_id        INTEGER NOT NULL ,
  permission_id        INTEGER NOT NULL
);

insert into t_user(username, password, mobile, email, name) values ('admin','123','123','123','123');
insert into t_user_role(user_id, role_Id) values (1,1);
insert into t_role(name, description) values ('Role_Admin','系统管理员');
insert into t_permission(name, description, url, pid, method) values ('Role_homepage','首页','/admin',null ,null );
insert into t_role_permission(role_id, permission_id) values (1,1);

