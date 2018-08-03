---------合同表---------
DROP SEQUENCE IF EXISTS seq_contract CASCADE;
DROP TABLE IF EXISTS t_contract CASCADE;
CREATE SEQUENCE seq_contract;
CREATE TABLE t_contract (
  id                          INTEGER PRIMARY KEY   DEFAULT nextval('seq_contract'), --合同编号
  content                   varchar(500)  , --合同内容
  company_name        varchar(500)     , --签订的公司名称
  contact_id                        VARCHAR(500), --联系人名称
  phone                VARCHAR(500), --电话
  fax                 VARCHAR(500), --传真
  saleman_id                       VARCHAR(500), --业务员
  amount                        numeric (22,2), --合同金额
    del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--表约束、索引
CREATE INDEX ON t_contract (id);

--------附件表-----------
DROP SEQUENCE IF EXISTS seq_attachment CASCADE;
DROP TABLE IF EXISTS t_attachment CASCADE;
CREATE SEQUENCE seq_attachment;
CREATE TABLE t_attachment (
  id                          INTEGER PRIMARY KEY   DEFAULT nextval('seq_attachment'), --附件编号
  contract_id                  INTEGER not null  , --合同ID
  type integer , --0回款情况 1备注 2执行情况
  content                  varchar(500)   , --附件内容
  url                  varchar(500)   , --附件地址
    del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--表约束、索引
CREATE INDEX ON t_attachment (id);
CREATE INDEX ON t_attachment (contract_id);

------人员表-------
DROP SEQUENCE IF EXISTS seq_user CASCADE;
DROP TABLE IF EXISTS t_user CASCADE;
CREATE SEQUENCE seq_user;
CREATE TABLE t_user (
  id                          INTEGER PRIMARY KEY   DEFAULT nextval('seq_user'), --用户编号
  role varchar (500), --类型 管理员 普通用户
  username varchar (500), --用户名称
  password                  varchar(500)   , --密码
  fullname                  varchar(500)   , --用户全称
   del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--表约束、索引
CREATE INDEX ON t_user (id);