create sequence pools_seq
  start with 1
  increment by 1; --

create or replace trigger pools_trig
  before insert on pools
  for each row
  begin
    select pools_seq.nextval into :new.pool_id from dual;
  end; --