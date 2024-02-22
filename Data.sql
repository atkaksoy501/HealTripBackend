INSERT INTO address (id, country, city, address_detail) VALUES (DEFAULT, 'Turkey', 'Antalya', 'Konyaaltı');
INSERT INTO address (id, country, city, address_detail) VALUES (DEFAULT, 'Turkey', 'Antalya', 'Lara');
INSERT INTO address (id, country, city, address_detail) VALUES (DEFAULT, 'Turkey', 'Antalya', 'Kemer');

INSERT INTO users (id, first_name, last_name, email, phone_number, user_password, user_role, active) VALUES (DEFAULT, 'Ali', 'Veli', 'abc@abc.com', '123456789', '123456', 'patient', true);
INSERT INTO users (id, first_name, last_name, email, phone_number, user_password, user_role, active) VALUES (DEFAULT, 'Ayşe', 'Fatma', 'abcd@abc.com', '123456789', '123456', 'hotel_organizer', true);
INSERT INTO users (id, first_name, last_name, email, phone_number, user_password, user_role, active) VALUES (DEFAULT, 'Mehmet', 'Osman', 'xxx@xxx.com', '123456789', '123456', 'hospital_organizer', true);

INSERT INTO department (id, department_name) VALUES (DEFAULT, 'Cardiology');
INSERT INTO department (id, department_name) VALUES (DEFAULT, 'Dermatology');
INSERT INTO department (id, department_name) VALUES (DEFAULT, 'Endocrinology');

INSERT INTO hospital (id, bed_capacity, hospital_name, address_id, contact_phone) VALUES (DEFAULT, 100, 'Memorial', 1, '0242 123 45 67');
INSERT INTO hospital (id, bed_capacity, hospital_name, address_id, contact_phone) VALUES (DEFAULT, 200, 'Medical Park', 2, '0242 123 45 68');
INSERT INTO hospital (id, bed_capacity, hospital_name, address_id, contact_phone) VALUES (DEFAULT, 300, 'Anadolu', 3, '0242 123 45 69');

INSERT INTO doctor (id, hospital_id, department_id, experience_year, doctor_name, doctor_image) VALUES (DEFAULT, 1, 1, 5, 'Dr. Ahmet', 'dGVzdA==');
INSERT INTO doctor (id, hospital_id, department_id, experience_year, doctor_name, doctor_image) VALUES (DEFAULT, 2, 2, 10, 'Dr. Mehmet', 'dGVzdA==');
INSERT INTO doctor (id, hospital_id, department_id, experience_year, doctor_name, doctor_image) VALUES (DEFAULT, 3, 3, 15, 'Dr. Ayşe', 'dGVzdA==');

INSERT INTO hospital_organizer (id, hospital_id) VALUES (DEFAULT, 1);
INSERT INTO hospital_organizer (id, hospital_id) VALUES (DEFAULT, 2);
INSERT INTO hospital_organizer (id, hospital_id) VALUES (DEFAULT, 3);

INSERT INTO hotel (id, hotel_name, address_id, contact_phone, bed_capacity) VALUES (DEFAULT, 'Rixos', 1, '0242 123 45 67', 100);
INSERT INTO hotel (id, hotel_name, address_id, contact_phone, bed_capacity) VALUES (DEFAULT, 'Sheraton', 2, '0242 123 45 68', 200);
INSERT INTO hotel (id, hotel_name, address_id, contact_phone, bed_capacity) VALUES (DEFAULT, 'Hilton', 3, '0242 123 45 69', 300);

INSERT INTO hotel_organizer (id, hotel_id) VALUES (DEFAULT, 1);
INSERT INTO hotel_organizer (id, hotel_id) VALUES (DEFAULT, 2);
INSERT INTO hotel_organizer (id, hotel_id) VALUES (DEFAULT, 3);

-- INSERT INTO patient (patient_id, birth_year, gender, patient_height, patient_weight) VALUES (1, 1990, 'M', 180, 80);
-- INSERT INTO patient (patient_id, birth_year, gender, patient_height, patient_weight) VALUES (2, 2009, 'F', 170, 90);
-- INSERT INTO patient (patient_id, birth_year, gender, patient_height, patient_weight) VALUES (3, 1970, 'M', 185, 100);

INSERT INTO retreat (id, retreat_name, department_id, description) VALUES (DEFAULT, 'Cardiology Retreat', 1, 'Cardiology Retreat');
INSERT INTO retreat (id, retreat_name, department_id, description) VALUES (DEFAULT, 'Dermatology Retreat', 2, 'Dermatology Retreat');
INSERT INTO retreat (id, retreat_name, department_id, description) VALUES (DEFAULT, 'Endocrinology Retreat', 3, 'Endocrinology Retreat');

INSERT INTO booking (id, patient_id, hospital_id, hotel_id, doctor_id, start_date, end_date, booking_date, status, retreat_id) VALUES (DEFAULT, 1, 1, 1, 1, '2024-01-01', '2024-01-10', '2024-01-01', 'active', 1);
INSERT INTO booking (id, patient_id, hospital_id, hotel_id, doctor_id, start_date, end_date, booking_date, status, retreat_id) VALUES (DEFAULT, 2, 2, 2, 2, '2024-01-01', '2024-01-10', '2024-01-01', 'active', 2);
INSERT INTO booking (id, patient_id, hospital_id, hotel_id, doctor_id, start_date, end_date, booking_date, status, retreat_id) VALUES (DEFAULT, 3, 3, 3, 3, '2024-01-01', '2024-01-10', '2024-01-01', 'active', 3);

INSERT INTO feedback (id, booking_id, comment, rating) VALUES (DEFAULT, 1, 'Good', 5);
INSERT INTO feedback (id, booking_id, comment, rating) VALUES (DEFAULT, 2, 'Bad', 1);
INSERT INTO feedback (id, booking_id, comment, rating) VALUES (DEFAULT, 3, 'Normal', 3);

-- INSERT INTO hospital_image (id, hospital_id, image) VALUES (DEFAULT, 1, 'dGVzdA==');
-- INSERT INTO hospital_image (id, hospital_id, image) VALUES (DEFAULT, 2, 'dGVzdA==');
-- INSERT INTO hospital_image (id, hospital_id, image) VALUES (DEFAULT, 3, 'dGVzdA==');
--
-- INSERT INTO hotel_image (id, hotel_id, image) VALUES (DEFAULT, 1, 'dGVzdA==');
-- INSERT INTO hotel_image (id, hotel_id, image) VALUES (DEFAULT, 2, 'dGVzdA==');
-- INSERT INTO hotel_image (id, hotel_id, image) VALUES (DEFAULT, 3, 'dGVzdA==');