create table Loans(
Loan_ID number,
Loan_APR decimal(5,4),
Loan_Principal number,
Loan_Start_Date date,
Loan_Last_Update date,
Loan_is_Adjustable number (1,0),
Borrower_ID number,
Lender_ID number,

constraint id_pk primary key(Loan_ID),
constraint lend_fk foreign key (Lender_ID) references Lenders(Lender_ID)

);--
 
create table Lenders(
Lender_ID number,
Lender_Name varchar2 (20),
Lender_Zipcode varchar2(5),

constraint lend_pk primary key(Lender_ID)
);
--

 create table Pool_Loans(
Loan_ID number, 
Pool_ID number,

constraint loan_pool_pk primary key (Loan_ID, Pool_ID),
constraint loan_fk foreign key (Loan_ID) references Loans (Loan_ID),
constraint pool_fk foreign key (Pool_ID) references Pools (Pool_ID)

);--



