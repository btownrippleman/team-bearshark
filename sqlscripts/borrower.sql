--drop sequence borrower_seq;
--drop table borrower;

create table borrower(
  borrower_id number,
  first_name varchar2(100),
  last_name varchar2(100),
  credit_score number,
  zip_code varchar2(5),
  constraint borrower_pk primary key (borrower_id)
  )
  
create sequence borrower_seq
   start with 1
   increment by 1;

create or replace trigger borrower_trig
  before insert on borrower
  for each row
  begin
    select borrower_seq.nextval into :new.borrower_id from dual;
  end; 