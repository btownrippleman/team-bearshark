create table pools (
	pool_id number,
	initial_total number,
	initial_apr decimal(5,4),
	creation_date date,

	constraint pools_pk primary key (pool_id)
);