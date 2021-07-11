-- 删除数据表
/*DROP TABLE grade ;
DROP TABLE sporter ;
DROP TABLE item ;
PURGE RECYCLEBIN ;*/
-- 创建数据表
CREATE TABLE sporter(
  sporterid   NUMBER(4) ,
  name      VARCHAR2(30)  NOT NULL ,
  sex     VARCHAR2(10)  ,
  department    VARCHAR2(30)  NOT NULL ,
  CONSTRAINT pk_sporterid PRIMARY KEY (sporterid) ,
  CONSTRAINT ck_sex CHECK (sex IN ('男','女'))
) ;
CREATE TABLE item(
  itemid    VARCHAR2(4) ,
  itemname    VARCHAR2(30)  NOT NULL ,
  location    VARCHAR2(30)  NOT NULL ,
  CONSTRAINT pk_itemid PRIMARY KEY (itemid) 
) ;
CREATE TABLE grade(
  sporterid   NUMBER(4) ,
  itemid    VARCHAR2(4)  ,
  mark      NUMBER(1) ,
  CONSTRAINT fk_sporterid FOREIGN KEY (sporterid) REFERENCES sporter(sporterid) ON DELETE CASCADE ,
  CONSTRAINT fk_itemid FOREIGN KEY (itemid) REFERENCES item(itemid) ON DELETE CASCADE ,
  CONSTRAINT ck_mark CHECK (mark IN (6,4,2,0))
) ;


INSERT INTO sporter(sporterid,name,sex,department) VALUES (1001,'李明','男','计算机系') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1002,'张三','男','数学系') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1003,'李四','男','计算机系') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1004,'王二','男','物理系') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1005,'李娜','女','心理系') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1006,'孙丽','女','数学系') ;


INSERT INTO item(itemid,itemname,location) VALUES ('x001','男子五千米','一操场') ;
INSERT INTO item(itemid,itemname,location) VALUES ('x002','男子标枪','一操场') ;
INSERT INTO item(itemid,itemname,location) VALUES ('x003','男子跳远','二操场') ;
INSERT INTO item(itemid,itemname,location) VALUES ('x004','女子跳高','二操场') ;
INSERT INTO item(itemid,itemname,location) VALUES ('x005','女子三千米','三操场') ;


INSERT INTO grade(sporterid,itemid,mark) VALUES (1001,  'x001', 6) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1002,  'x001', 4) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1003,  'x001', 2) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1004,  'x001', 0) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1001,  'x003', 4) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1002,  'x003', 6) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1004,  'x003', 2) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1003,  'x003', 0) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1005,  'x004', 6) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1006,  'x004', 4) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1001,  'x004', 2) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1002,  'x004', 0) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1003,  'x002', 6) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1005,  'x002', 4) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1006,  'x002', 2) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1001,  'x002', 0) ;
COMMIT ;