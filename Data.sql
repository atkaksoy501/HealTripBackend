INSERT INTO address (address_id, country, city, address_detail) VALUES (1, 'Turkey', 'Antalya', 'Konyaaltı');
INSERT INTO address (address_id, country, city, address_detail) VALUES (2, 'Turkey', 'Antalya', 'Lara');
INSERT INTO address (address_id, country, city, address_detail) VALUES (3, 'Turkey', 'Antalya', 'Kemer');

INSERT INTO users (user_id, first_name, last_name, email, phone_number, user_password, user_role, active) VALUES (1, 'Ali', 'Veli', 'abc@abc.com', '123456789', '123456', 'patient', true);
INSERT INTO users (user_id, first_name, last_name, email, phone_number, user_password, user_role, active) VALUES (2, 'Ayşe', 'Fatma', 'abcd@abc.com', '123456789', '123456', 'hotel_organizer', true);
INSERT INTO users (user_id, first_name, last_name, email, phone_number, user_password, user_role, active) VALUES (3, 'Mehmet', 'Osman', 'xxx@xxx.com', '123456789', '123456', 'hospital_organizer', true);

INSERT INTO department (department_id, department_name) VALUES (1, 'Cardiology');
INSERT INTO department (department_id, department_name) VALUES (2, 'Dermatology');
INSERT INTO department (department_id, department_name) VALUES (3, 'Endocrinology');

INSERT INTO hospital (hospital_id, bed_capacity, hospital_name, address_id, contact_phone) VALUES (1, 100, 'Memorial', 1, '0242 123 45 67');
INSERT INTO hospital (hospital_id, bed_capacity, hospital_name, address_id, contact_phone) VALUES (2, 200, 'Medical Park', 2, '0242 123 45 68');
INSERT INTO hospital (hospital_id, bed_capacity, hospital_name, address_id, contact_phone) VALUES (3, 300, 'Anadolu', 3, '0242 123 45 69');

INSERT INTO doctor (doctor_id, hospital_id, department_id, experience_year, doctor_name, doctor_image) VALUES (1, 1, 1, 5, 'Dr. Ahmet', 'dGVzdA==');
INSERT INTO doctor (doctor_id, hospital_id, department_id, experience_year, doctor_name, doctor_image) VALUES (2, 2, 2, 10, 'Dr. Mehmet', 'dGVzdA==');
INSERT INTO doctor (doctor_id, hospital_id, department_id, experience_year, doctor_name, doctor_image) VALUES (3, 3, 3, 15, 'Dr. Ayşe', 'dGVzdA==');

INSERT INTO hospital_organizer (hospital_organizer_id, hospital_id) VALUES (1, 1);
INSERT INTO hospital_organizer (hospital_organizer_id, hospital_id) VALUES (2, 2);
INSERT INTO hospital_organizer (hospital_organizer_id, hospital_id) VALUES (3, 3);

INSERT INTO hotel (hotel_id, hotel_name, address_id, contact_phone, bed_capacity) VALUES (1, 'Rixos', 1, '0242 123 45 67', 100);
INSERT INTO hotel (hotel_id, hotel_name, address_id, contact_phone, bed_capacity) VALUES (2, 'Sheraton', 2, '0242 123 45 68', 200);
INSERT INTO hotel (hotel_id, hotel_name, address_id, contact_phone, bed_capacity) VALUES (3, 'Hilton', 3, '0242 123 45 69', 300);

INSERT INTO hotel_organizer (hotel_organizer_id, hotel_id) VALUES (1, 1);
INSERT INTO hotel_organizer (hotel_organizer_id, hotel_id) VALUES (2, 2);
INSERT INTO hotel_organizer (hotel_organizer_id, hotel_id) VALUES (3, 3);

INSERT INTO patient (patient_id, birth_year, gender, patient_height, patient_weight) VALUES (1, 1990, 'M', 180, 80);
INSERT INTO patient (patient_id, birth_year, gender, patient_height, patient_weight) VALUES (2, 2009, 'F', 170, 90);
INSERT INTO patient (patient_id, birth_year, gender, patient_height, patient_weight) VALUES (3, 1970, 'M', 185, 100);

INSERT INTO retreat (retreat_id, retreat_name, department_id, description) VALUES (1, 'Cardiology Retreat', 1, 'Cardiology Retreat');
INSERT INTO retreat (retreat_id, retreat_name, department_id, description) VALUES (2, 'Dermatology Retreat', 2, 'Dermatology Retreat');
INSERT INTO retreat (retreat_id, retreat_name, department_id, description) VALUES (3, 'Endocrinology Retreat', 3, 'Endocrinology Retreat');

INSERT INTO booking (booking_id, patient_id, hospital_id, hotel_id, doctor_id, start_date, end_date, booking_date, status, retreat_id) VALUES (1, 1, 1, 1, 1, '2024-01-01', '2024-01-10', '2024-01-01', 'active', 1);
INSERT INTO booking (booking_id, patient_id, hospital_id, hotel_id, doctor_id, start_date, end_date, booking_date, status, retreat_id) VALUES (2, 2, 2, 2, 2, '2024-01-01', '2024-01-10', '2024-01-01', 'active', 2);
INSERT INTO booking (booking_id, patient_id, hospital_id, hotel_id, doctor_id, start_date, end_date, booking_date, status, retreat_id) VALUES (3, 3, 3, 3, 3, '2024-01-01', '2024-01-10', '2024-01-01', 'active', 3);

INSERT INTO feedback (feedback_id, booking_id, comment, rating) VALUES (1, 1, 'Good', 5);
INSERT INTO feedback (feedback_id, booking_id, comment, rating) VALUES (2, 2, 'Bad', 1);
INSERT INTO feedback (feedback_id, booking_id, comment, rating) VALUES (3, 3, 'Normal', 3);

INSERT INTO hospital_image (hospital_image_id, hospital_id, image) VALUES (1, 1, 'dGVzdA==');
INSERT INTO hospital_image (hospital_image_id, hospital_id, image) VALUES (2, 2, 'dGVzdA==');
INSERT INTO hospital_image (hospital_image_id, hospital_id, image) VALUES (3, 3, 'dGVzdA==');

INSERT INTO hotel_image (hotel_image_id, hotel_id, image) VALUES (1, 1, 'dGVzdA==');
INSERT INTO hotel_image (hotel_image_id, hotel_id, image) VALUES (2, 2, 'dGVzdA==');
INSERT INTO hotel_image (hotel_image_id, hotel_id, image) VALUES (3, 3, 'dGVzdA==');