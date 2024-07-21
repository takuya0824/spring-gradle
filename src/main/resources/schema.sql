DROP table if exists motorcycle;
CREATE table motorcycle(
moto_no int not null primary key,
moto_name varchar(100),
price int,
comment varchar(200),
brand_id int,
version int,
create_date_time datetime,
update_date_time datetime);

DROP table if exists brand;
CREATE table brand(
brand_id int not null primary key,
brand_name varchar(100));