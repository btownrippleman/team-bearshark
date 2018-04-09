CREATE USER bearshark
IDENTIFIED BY password
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users; --

GRANT connect to bearshark; --
GRANT resource to bearshark; --
GRANT create session TO bearshark; --
GRANT create table TO bearshark; --
GRANT create view TO bearshark; --