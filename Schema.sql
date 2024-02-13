SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SELECT pg_catalog.set_config('search_path', '', false);
SET xmloption = content;
SET row_security = off;

SET search_path TO heal_trip_test;

DROP SCHEMA IF EXISTS heal_trip_test CASCADE;

CREATE SCHEMA heal_trip_test;

SET search_path TO heal_trip_test;

ALTER SCHEMA heal_trip_test OWNER TO codewizards_admin;

SET default_tablespace = '';

SET default_with_oids = false;

SET default_table_access_method = heap;

DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS booking CASCADE;
DROP TABLE IF EXISTS department CASCADE;
DROP TABLE IF EXISTS doctor CASCADE;
DROP TABLE IF EXISTS feedback CASCADE;
DROP TABLE IF EXISTS hospital CASCADE;
DROP TABLE IF EXISTS hospital_organizer CASCADE;
DROP TABLE IF EXISTS hotel CASCADE;
DROP TABLE IF EXISTS hotel_organizer CASCADE;
DROP TABLE IF EXISTS patient CASCADE;
DROP TABLE IF EXISTS retreat CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE address (
    address_id int4 NOT NULL,
    country varchar NOT NULL,
    city varchar NOT NULL,
    address_detail varchar NOT NULL
);

CREATE TABLE booking (
    booking_id int4 NOT NULL,
    patient_id int4 NOT NULL,
    hospital_id int4 NOT NULL,
    hotel_id int4,
    doctor_id int4 NOT NULL,
    start_date timestamp NOT NULL,
    end_date timestamp NOT NULL,
    booking_date timestamp NOT NULL,
    status int4 NOT NULL,
    retreat_id int4 NOT NULL
);

CREATE TABLE department (
    department_id int4 NOT NULL,
    department_name varchar NOT NULL
);

CREATE TABLE doctor (
    doctor_id int4 NOT NULL,
    hospital_id int4 NOT NULL,
    department_id int4 NOT NULL,
    experience_year int4 NOT NULL,
    doctor_name varchar NOT NULL
);

CREATE TABLE feedback (
    feedback_id int4 NOT NULL,
    booking_id int4 NOT NULL,
    comment varchar NOT NULL,
    rating int4 NOT NULL
);

CREATE TABLE hospital (
    hospital_id int4 NOT NULL,
    bed_capacity int4 NOT NULL,
    hospital_name varchar NOT NULL,
    address_id int4 NOT NULL,
    contact_phone varchar NOT NULL
);

CREATE TABLE hospital_organizer (
    hospital_organizer_id int4 NOT NULL,
    hospital_id int4 NOT NULL
);

CREATE TABLE hotel (
    hotel_id int4 NOT NULL,
    hotel_name varchar NOT NULL,
    address_id int4 NOT NULL,
    contact_phone varchar NOT NULL,
    bed_capacity int4 NOT NULL
);

CREATE TABLE hotel_organizer (
    hotel_organizer_id int4 NOT NULL,
    hotel_id int4 NOT NULL
);

CREATE TABLE patient (
    patient_id int4 NOT NULL,
    birth_year int4 NOT NULL,
    gender bpchar NOT NULL,
    patient_height int4 NOT NULL,
    patient_weight int4 NOT NULL
);

CREATE TABLE retreat (
    retreat_id int4 NOT NULL,
    retreat_name varchar NOT NULL,
    department_id int4 NOT NULL,
    description varchar NOT NULL
);

CREATE TABLE "user" (
    user_id int4 NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    email varchar NOT NULL,
    phone_number varchar NOT NULL,
    user_password varchar NOT NULL,
    user_role varchar NOT NULL
);

ALTER TABLE ONLY address
    ADD CONSTRAINT pk_address PRIMARY KEY (address_id);

ALTER TABLE ONLY booking
    ADD CONSTRAINT pk_booking PRIMARY KEY (booking_id);

ALTER TABLE ONLY department
    ADD CONSTRAINT pk_department PRIMARY KEY (department_id);

ALTER TABLE ONLY doctor
    ADD CONSTRAINT pk_doctor PRIMARY KEY (doctor_id);

ALTER TABLE ONLY feedback
    ADD CONSTRAINT pk_feedback PRIMARY KEY (feedback_id);

ALTER TABLE ONLY hospital
    ADD CONSTRAINT pk_hospital PRIMARY KEY (hospital_id);

ALTER TABLE ONLY hospital_organizer
    ADD CONSTRAINT pk_hospital_organizer PRIMARY KEY (hospital_organizer_id);

ALTER TABLE ONLY hotel
    ADD CONSTRAINT pk_hotel PRIMARY KEY (hotel_id);

ALTER TABLE ONLY hotel_organizer
    ADD CONSTRAINT pk_hotel_organizer PRIMARY KEY (hotel_organizer_id);

ALTER TABLE ONLY patient
    ADD CONSTRAINT pk_patient PRIMARY KEY (patient_id);

ALTER TABLE ONLY retreat
    ADD CONSTRAINT pk_retreat PRIMARY KEY (retreat_id);

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk_user PRIMARY KEY (user_id);

ALTER TABLE ONLY booking
    ADD CONSTRAINT fk_booking_patient FOREIGN KEY (patient_id) REFERENCES patient;

ALTER TABLE ONLY booking
    ADD CONSTRAINT fk_booking_hospital FOREIGN KEY (hospital_id) REFERENCES hospital;

ALTER TABLE ONLY booking
    ADD CONSTRAINT fk_booking_hotel FOREIGN KEY (hotel_id) REFERENCES hotel;

ALTER TABLE ONLY booking
    ADD CONSTRAINT fk_booking_doctor FOREIGN KEY (doctor_id) REFERENCES doctor;

ALTER TABLE ONLY booking
    ADD CONSTRAINT fk_booking_retreat FOREIGN KEY (retreat_id) REFERENCES retreat;

ALTER TABLE ONLY doctor
    ADD CONSTRAINT fk_doctor_hospital FOREIGN KEY (hospital_id) REFERENCES hospital;

ALTER TABLE ONLY doctor
    ADD CONSTRAINT fk_doctor_department FOREIGN KEY (department_id) REFERENCES department;

ALTER TABLE ONLY feedback
    ADD CONSTRAINT fk_feedback_booking FOREIGN KEY (booking_id) REFERENCES booking;

ALTER TABLE ONLY hospital
    ADD CONSTRAINT fk_hospital_address FOREIGN KEY (address_id) REFERENCES address;

ALTER TABLE ONLY hospital_organizer
    ADD CONSTRAINT fk_hospital_organizer_hospital FOREIGN KEY (hospital_id) REFERENCES hospital;

ALTER TABLE ONLY hotel
    ADD CONSTRAINT fk_hotel_address FOREIGN KEY (address_id) REFERENCES address;

ALTER TABLE ONLY hotel_organizer
    ADD CONSTRAINT fk_hotel_organizer_hotel FOREIGN KEY (hotel_id) REFERENCES hotel;

ALTER TABLE ONLY retreat
    ADD CONSTRAINT fk_retreat_department FOREIGN KEY (department_id) REFERENCES department;


