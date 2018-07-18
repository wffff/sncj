--------用户表
DROP SEQUENCE IF EXISTS seq_user CASCADE;
CREATE SEQUENCE seq_user;
DROP TABLE IF EXISTS t_user CASCADE;
create table t_user
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('seq_user'),
  organization_id INTEGER ,
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

CREATE INDEX ON t_user (organization_id);

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
  type    INTEGER,
  icon_cls    varchar(50),
  menu_sort    INTEGER default 0,
  tab_sort    INTEGER default 0,
  func_sort    INTEGER default 0,
  module_sort    INTEGER default 0,
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

DROP SEQUENCE IF EXISTS seq_organization CASCADE;
DROP TABLE IF EXISTS t_organization CASCADE;
CREATE SEQUENCE seq_organization;
CREATE TABLE t_organization (
  id                          INTEGER PRIMARY KEY   DEFAULT nextval('seq_organization'),
  parent_id                   INTEGER,
  organization_type        INTEGER      NOT NULL,
  name                        VARCHAR(500) NOT NULL,
  company_name                VARCHAR(500) NOT NULL,
  description                 VARCHAR(500),
  memo                        VARCHAR(500),
  del                         BOOLEAN      NOT NULL DEFAULT FALSE,
  last                        TIMESTAMP(0)          DEFAULT NULL,
  time                        TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--表约束、索引
CREATE INDEX ON t_organization (parent_id);
CREATE INDEX ON t_organization (organization_type);




insert  into t_user(organization_id, username, password, mobile, email, name)values (null ,'admin','$2a$10$3dusHzT1ZdVW2Bqa.7qtE.1hx..zPyUYOSg0UvRP8Mboa2nheRyEC'
,null ,null ,'系统管理员');
insert into t_user_role(user_id, role_Id) values (1,1);
insert into t_role(name, description) values ('Role_Admin','系统管理员');
insert into t_permission(name, url, pid, icon_cls,menu_sort,tab_sort,func_sort,module_sort,type) values ('用户中心','#',null ,'span_navL05',0,0,0,1,0);
insert into t_permission(name, url, pid, icon_cls,menu_sort,tab_sort,func_sort,module_sort,type) values ('用户管理','user/manage',1 ,null ,1,0,0,1,0);
insert into t_permission(name, url, pid, icon_cls,menu_sort,tab_sort,func_sort,module_sort,type) values ('用户','user',1 ,null ,1,1,0,1,1);
insert into t_permission(name, url, pid, icon_cls,menu_sort,tab_sort,func_sort,module_sort,type) values ('增加','userAdd',1 ,null ,1,1,1,1,2);
insert into t_role_permission(role_id, permission_id) values (1,1);

insert into t_organization(parent_id, organization_type, name, company_name, description, memo)values (null ,0,'集团','测试集团','测试','没啥') --1
insert into t_organization(parent_id, organization_type, name, company_name, description, memo)values (1 ,1,'分公司','测试分公司','测试','没啥') --2

insert into t_organization(parent_id, organization_type, name, company_name, description, memo)values (2 ,2,'车间1','测试车间','测试','没啥') --3
insert into t_organization(parent_id, organization_type, name, company_name, description, memo)values (2 ,2,'车间2','测试车间','测试','没啥') --4

insert into t_organization(parent_id, organization_type, name, company_name, description, memo)values (3 ,3,'设备1','测试设备','测试','没啥')
insert into t_organization(parent_id, organization_type, name, company_name, description, memo)values (3 ,3,'设备2','测试设备','测试','没啥')
insert into t_organization(parent_id, organization_type, name, company_name, description, memo)values (4 ,3,'设备3','测试设备','测试','没啥')
insert into t_organization(parent_id, organization_type, name, company_name, description, memo)values (4 ,3,'设备4','测试设备','测试','没啥')