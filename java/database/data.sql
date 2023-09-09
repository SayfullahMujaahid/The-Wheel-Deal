BEGIN TRANSACTION;

INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('user', 'User FName', 'User LName','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','Customer');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('admin','Admin FName', 'Admin LName', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','Admin');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('sayf', 'Sayf', 'Mujaahid','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','Customer');

insert into service (service_description, service_category, service_price, service_time) values ('Tune Up', 'Auto Repair Services', 75.00, 0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Diagnostic Test', 'Auto Repair Services',25.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Car Maintenance Schedule', 'Auto Repair Services',35.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Engine Repair','Auto Repair Services',2499.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Fuel Pump Replacement', 'Auto Repair Services',999.00, 0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Headlight Replacement', 'Auto Repair Services',25.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Power Window Repair', 'Auto Repair Services',99.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Timing Belts & Chains', 'Auto Repair Services',799.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Transmission Repair', 'Auto Repair Services',749.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Windshield Wipers', 'Auto Repair Services',15.00, 0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Cooling System Service', 'Auto Repair Services',15.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Oil Change', 'Oil Change Services',25.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Air & Oil Filters', 'Oil Change Services',15.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Oil, Anti-Freeze & Brake Fluids', 'Oil Change Services',15.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Brake Pads', 'Brake Services',50.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Rotor Discs', 'Brake Services',150.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Performance Tires', 'Tire & Wheel Services',499.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Car Tires', 'Tire & Wheel Services',299.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('SUV & Truck Tires', 'Tire & Wheel Services',399.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Tire Repair', 'Tire & Wheel Services',99.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Tire Rotation & Replacement', 'Tire & Wheel Services',50.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Wheel Alignment', 'Tire & Wheel Services',50.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Wheel Bearings', 'Tire & Wheel Services',75.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Wheel Balancing', 'Tire & Wheel Services',75.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Car Batteries', 'Battery Service',149.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Alternator & Starter Repair', 'Battery Service',99.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Exhaust & Muffler Repair', 'Exhaust & Catalytic Converters',99.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Catalytic Converters', 'Exhaust & Catalytic Converters',759.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Emissions Repair', 'Exhaust & Catalytic Converters',149.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Performance Exhaust', 'Exhaust & Catalytic Converters',299.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Rack & Pinion ', 'Springs', 1499.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Struts & Shock Absorbers', 'Springs',1099.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Power Steering & Chassis', 'Springs',99.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Suspension Lowering', 'Springs', 649.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Coil Spring Suspension', 'Springs',599.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Axle Repair', 'CV Joints & Driveshafts',799.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Clutch Repair', 'CV Joints & Driveshafts',1199.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('CV Joints & Driveshaft', 'CV Joints & Driveshafts',1099.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Air Conditioner Service', 'Air Conditioning',149.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('AC Condenser & Evaporator', 'Air Conditioning',999.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Fuel Injector Service', 'Miscellaneous Services',349.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Water Pump Repair & Service', 'Miscellaneous Services',649.00,0.5);
insert into service (service_description, service_category, service_price, service_time) values ('Radiator Repair & Service', 'Miscellaneous Services',249.00,0.5);

insert into status (status_description) values ('Pending Customer Approval');
insert into status (status_description) values ('Declined');
insert into status (status_description) values ('Not Started');
insert into status (status_description) values ('In Progress');
insert into status (status_description) values ('Completed');

-- faking vehicles temporarily.
insert into vehicle (make, model, year, color, plate_number, mileage)
values ('Toyota', 'Camry', 2022, 'Blue', 'FGZ-1324', 56050),
 ('Honda', 'Civic', 2023, 'White', 'HGY-2334', 21442);

insert into users_vehicle (user_id, vehicle_id)
values (1,1), (1,2);




COMMIT TRANSACTION;