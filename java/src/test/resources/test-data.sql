BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,first_name,last_name) VALUES ('user1','user1','ROLE_USER','first1','last1'); -- 1
INSERT INTO users (username,password_hash,role,first_name,last_name) VALUES ('user2','user2','ROLE_USER','first2','last2'); -- 2
INSERT INTO users (username,password_hash,role,first_name,last_name) VALUES ('user3','user3','ROLE_USER','first3','last3'); -- 3

insert into service (service_description, service_category, service_price, service_time) values ('Tune Up', 'Auto Repair Services', 75.00, 0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Diagnostic Test', 'Auto Repair Services',25.00,0.5);

insert into status (status_description) values ('Pending Customer Approval'); -- id: 1
insert into status (status_description) values ('Declined'); -- 2
insert into status (status_description) values ('Not Started'); -- 3
insert into status (status_description) values ('In Progress'); -- 4
insert into status (status_description) values ('Completed'); -- 5


insert into vehicle (make, model, year, color) values ('make', 'model', 'year', 'color');
insert into work_order (vehicle_id) values(1); -- 1
insert into users_work_order (user_id, work_order_id) values (2, 1); -- employees that worked on work_order
insert into work_order_service_status (work_order_id, service_id, work_order_service_status_id, status_change_timestamp)
    values (1, 1, 5, current_timestamp); -- services worked on in work order
insert into invoice (user_id, work_order_id) values (1, 1);

COMMIT TRANSACTION;
