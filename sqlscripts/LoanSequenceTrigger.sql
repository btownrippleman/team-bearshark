create sequence Loans_seq
start with 1 
increment by 1;
--
create or replace trigger Loan_trig
before insert on Loans
for each row begin 
select Loans_seq.nextval into :new.Loan_ID from dual;
end; --