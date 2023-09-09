BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, vehicle, users_vehicle, work_order, users_work_order, status, work_order_status, service, work_order_service_status, invoice, invoice_service, user_invoice;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	first_name varchar(25) not null,
	last_name varchar(25) not null,
	password_hash varchar(200) NOT NULL,
	is_activated boolean not null default true,
	email varchar(50) unique,
	phone varchar(10),
	role varchar(50) NOT NULL,
	badge varchar(25),


	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

create table vehicle
(
	vehicle_id serial,
	make varchar(25) not null,
	model varchar(25) not null,
	year varchar(4) not null,
	color varchar(25) not null,
	plate_number varchar(15),
	mileage int,

	constraint pk_vehicle_id primary key (vehicle_id)
);

create table users_vehicle
(
	user_id int not null,
	vehicle_id int not null,

	constraint pk_users_vehicle primary key (user_id, vehicle_id),
	constraint fk_user_id foreign key (user_id) references users (user_id),
	constraint fk_vehicle_id foreign key (vehicle_id) references vehicle (vehicle_id)
);

create table work_order
(
	work_order_id serial,
	vehicle_id int not null,
	time_adjustment decimal not null default 0.0,
	is_approved boolean not null default false,
	is_completed boolean not null default false,

	constraint pk_work_order_id primary key (work_order_id),
	constraint fk_vehicle_id foreign key (vehicle_id) references vehicle (vehicle_id)
);

create table users_work_order
(
	user_id int not null,
	work_order_id int not null,

	constraint pk_users_work_order primary key (user_id, work_order_id),
	constraint fk_user_id foreign key (user_id) references users (user_id),
	constraint fk_work_order_id foreign key (work_order_id) references work_order (work_order_id)
);

create table status
(
	status_id serial,
	status_description varchar(100) not null,

	constraint pk_status_id primary key (status_id),
	constraint ck_status_description check (status_description in ('Pending Customer Approval', 'Declined', 'Not Started', 'In Progress', 'Completed'))
);

create table work_order_status
(
	work_order_id int not null,
	status_id int not null,
	status_change_timestamp timestamp not null,

	constraint pk_work_order_status primary key (work_order_id, status_id),
	constraint fk_work_order_id foreign key (work_order_id) references work_order (work_order_id),
	constraint fk_status_id foreign key (status_id) references status (status_id)
-- 	constraint ck_status_id check (status_id in (select status_id from status where status_description in (
-- 		'Pending Customer Approval', 'Not Started', 'In Progress', 'Completed')))
);

create table service
(
	service_id serial,
	service_description varchar(100) not null,
	service_category varchar(100),
	service_price decimal not null,
	service_time decimal not null default 0.5,

	constraint pk_service_id primary key (service_id),
	constraint ch_service_price check (service_price >= 0.00)
);

create table work_order_service_status
(
	work_order_id int not null,
	service_id int not null,
	work_order_service_status_id int not null,
	status_change_timestamp timestamp not null,

	constraint pk_work_order_service primary key (work_order_id, service_id, work_order_service_status_id),
	constraint fk_work_order_id foreign key (work_order_id) references work_order (work_order_id),
	constraint fk_service_id foreign key (service_id) references service (service_id),
	constraint fk_work_order_service_status_id foreign key (work_order_service_status_id) references status (status_id)
-- 	constraint ck_work_order_service_status_id check (work_order_service_status_id in (
-- 		select status_id from status where status_description in (
-- 			'Pending Customer Approval', 'Declined', 'Not Started', 'In Progress', 'Completed')))
);


create table invoice
(
	invoice_id serial,
	user_id int not null,
	work_order_id int not null,
	is_paid boolean not null default false,

	constraint pk_invoice_id primary key (invoice_id),
	constraint fk_user_id foreign key (user_id) references users (user_id),
	constraint fk_work_order_id foreign key (work_order_id) references work_order (work_order_id)
);

create table invoice_service
(
	invoice_id int not null,
	service_id int not null,

	constraint pk_invoice_service primary key (invoice_id, service_id),
	constraint fk_invoice_id foreign key (invoice_id) references invoice (invoice_id),
	constraint fk_service_id foreign key (service_id) references service (service_id)
);

create table user_invoice
(
	user_id int not null,
	invoice_id int not null,

	constraint pk_user_invoice primary key (user_id, invoice_id),
	constraint fk_user_id foreign key (user_id) references users (user_id),
	constraint fk_invoice_id foreign key (invoice_id) references invoice (invoice_id)
);

COMMIT TRANSACTION;