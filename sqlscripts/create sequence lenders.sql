create sequence lender_seq
  start with 1
  increment by 1; --

create or replace trigger lender_trig
  before insert on lenders
  for each row
  begin
    select lender_seq.nextval into :new.lender_id from dual;
  end; --