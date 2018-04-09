create sequence lend_seq
start with 1
increment by 1;

create or replace trigger lend_trig
before insert on Lenders
for each row 
begin 
select lend_seq.nextval into :new.Lender_ID from dual;
end;

