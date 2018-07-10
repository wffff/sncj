-- -----------------------------------------------------------------------
-- Table oauth_client			客户
-- -----------------------------------------------------------------------
DROP TABLE IF EXISTS oauth_client CASCADE;
CREATE TABLE oauth_client (
  client_id              CHAR(40) PRIMARY KEY,
  client_secret          CHAR(40),
  resource_ids           VARCHAR(256),
  scope                  VARCHAR(256),
  grant_types            VARCHAR(256), -- really 70
  redirect_uri           VARCHAR(256),
  authorities            VARCHAR(256),
  access_token_validity  INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove            VARCHAR(256),
  type                   INTEGER, -- 客户类型 1:网站 2:移动
  site                   VARCHAR(50), -- 网站名称
  url                    VARCHAR(256), -- 网站地址
  enabled                BOOLEAN      DEFAULT FALSE, -- 是否通过审批
  last                   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP, -- 最后申请审批时间
  time                   TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP
);

-- -----------------------------------------------------------------------
-- Table oauth_client_token			客户令牌
-- -----------------------------------------------------------------------
DROP TABLE IF EXISTS oauth_client_token CASCADE;
CREATE TABLE oauth_client_token (
  authentication_id CHAR(40) PRIMARY KEY,
  token_id          CHAR(40),
  token             BYTEA,
  client_id         CHAR(40),
  username          VARCHAR(50)
);

-- -----------------------------------------------------------------------
-- Table oauth_access_token			访问令牌
-- -----------------------------------------------------------------------
DROP TABLE IF EXISTS oauth_access_token CASCADE;
CREATE TABLE oauth_access_token (
  authentication_id CHAR(40) PRIMARY KEY,
  authentication    BYTEA,
  token_id          CHAR(40),
  token             BYTEA,
  refresh_token     CHAR(40),
  client_id         CHAR(40),
  username          VARCHAR(50)
);

-- -----------------------------------------------------------------------
-- Table oauth_refresh_token			刷新令牌
-- -----------------------------------------------------------------------
DROP TABLE IF EXISTS oauth_refresh_token CASCADE;
CREATE TABLE oauth_refresh_token (
  token_id       CHAR(40) PRIMARY KEY,
  token          BYTEA,
  authentication BYTEA
);

-- -----------------------------------------------------------------------
-- Table oauth_code			认证代码
-- -----------------------------------------------------------------------
DROP TABLE IF EXISTS oauth_code CASCADE;
CREATE TABLE oauth_code (
  code           CHAR(40) PRIMARY KEY,
  authentication BYTEA
);

-- -----------------------------------------------------------------------
-- Table oauth_openid			开放ID
-- -----------------------------------------------------------------------
DROP TABLE IF EXISTS oauth_openid CASCADE;
CREATE TABLE oauth_openid (
  openid    CHAR(40) PRIMARY KEY,
  client_id CHAR(40),
  uid       BIGINT
);

-- -----------------------------------------------------------------------
-- Table oauth_approvals			审批(使用TOKEN APPROVAL)
-- -----------------------------------------------------------------------
-- DROP TABLE IF EXISTS oauth_approvals CASCADE;
-- CREATE TABLE oauth_approvals (
--   uid       BIGINT,
--   client_id CHAR(40),
--   scope     VARCHAR(256),
--   status    VARCHAR(10),
--   expires   TIMESTAMP,
--   last      TIMESTAMP
-- );

--################################################################################
-- DATA
--################################################################################
INSERT INTO oauth_user (username, password) VALUES ('ivan', '011c945f30ce2cbafc452f39840f025693339c42'); -- password: 1111
INSERT INTO oauth_user (username, password) VALUES ('ddw', 'fea7f657f56a2a448da7d4b535ee5e279caf3d9a'); -- password: 2222
INSERT INTO oauth_user (username, password) VALUES ('hande', '601f1889667efaebb33b8c12572835da3f027f78'); -- password: 123123

INSERT INTO oauth_role (name, description) VALUES ('ROLE_ADMIN', '管理员');
INSERT INTO oauth_role (name, description) VALUES ('ROLE_USER', '用户');
INSERT INTO oauth_role (name, description) VALUES ('ROLE_THIRD', '第三方接入');


INSERT INTO oauth_authority (uid, rid) VALUES (1, 1);
INSERT INTO oauth_authority (uid, rid) VALUES (1, 2);
INSERT INTO oauth_authority (uid, rid) VALUES (2, 2);
INSERT INTO oauth_authority (uid, rid) VALUES (3, 3);

INSERT INTO oauth_client (client_id, client_secret, resource_ids, scope, grant_types, redirect_uri, authorities, autoapprove, uid, type, site, url, enabled)
VALUES ('de9af4d7233e7fe43803c915bda11ab190062a82', '233b0a865b1dbb4de3e510fdbdb47e65b68fc2a7', 'GOMRO', 'read,write,trust', 'authorization_code,implicit,password,client_credentials,refresh_token', 'http://www.wogebi.com/login', 'ROLE_ADMIN,ROLE_USER', '', 1, 1, '固买网', 'http://www.gomro.cn', TRUE);
INSERT INTO oauth_client (client_id, client_secret, resource_ids, scope, grant_types, redirect_uri, authorities, autoapprove, uid, type, site, url, enabled)
VALUES ('3894f42d02b94cc6d21b259f77be58373454b48f', '0c9ece1c91585bc48f459457b6ca4f8b8db68535', 'GOMRO', 'read', 'password,refresh_token', null, null, '', 3, 1, '汉德',null, TRUE);