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

SET search_path TO heal_trip_atakan; --DO NOT FORGET TO CHANGE THE SCHEMA NAME--

DROP SCHEMA IF EXISTS heal_trip_atakan CASCADE; --DO NOT FORGET TO CHANGE THE SCHEMA NAME--

CREATE SCHEMA heal_trip_atakan; --DO NOT FORGET TO CHANGE THE SCHEMA NAME--

SET search_path TO heal_trip_atakan; --DO NOT FORGET TO CHANGE THE SCHEMA NAME--

ALTER SCHEMA heal_trip_atakan OWNER TO codewizards_admin; --DO NOT FORGET TO CHANGE THE SCHEMA NAME--

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
DROP TABLE IF EXISTS hospital_image CASCADE;
DROP TABLE IF EXISTS hotel_image CASCADE;

CREATE TABLE address (
    id int4 GENERATED ALWAYS AS IDENTITY,
    country varchar NOT NULL,
    city varchar NOT NULL,
    address_detail varchar NOT NULL
);

CREATE TABLE booking (
    id int4 GENERATED ALWAYS AS IDENTITY,
    patient_id int4 NOT NULL,
    hospital_id int4 NOT NULL,
    hotel_id int4,
    doctor_id int4 NOT NULL,
    start_date timestamp NOT NULL,
    end_date timestamp NOT NULL,
    booking_date timestamp NOT NULL,
    status varchar NOT NULL,
    retreat_id int4 NOT NULL
);

CREATE TABLE department (
    id int4 GENERATED ALWAYS AS IDENTITY,
    department_name varchar NOT NULL
);

CREATE TABLE doctor (
    id int4 GENERATED ALWAYS AS IDENTITY,
    hospital_id int4 NOT NULL,
    department_id int4 NOT NULL,
    experience_year int4 NOT NULL,
    doctor_name varchar NOT NULL,
    doctor_image varchar NOT NULL
);

CREATE TABLE feedback (
    id int4 GENERATED ALWAYS AS IDENTITY,
    booking_id int4 NOT NULL,
    comment varchar NOT NULL,
    rating int4 NOT NULL
);

CREATE TABLE hospital (
    id int4 GENERATED ALWAYS AS IDENTITY,
    bed_capacity int4 NOT NULL,
    hospital_name varchar NOT NULL,
    address_id int4 NOT NULL,
    contact_phone varchar NOT NULL
);

CREATE TABLE hospital_organizer (
    id int4 GENERATED ALWAYS AS IDENTITY,
    hospital_id int4 NOT NULL
);

CREATE TABLE hotel (
    id int4 GENERATED ALWAYS AS IDENTITY,
    hotel_name varchar NOT NULL,
    address_id int4 NOT NULL,
    contact_phone varchar NOT NULL,
    bed_capacity int4 NOT NULL
);

CREATE TABLE hotel_organizer (
    id int4 GENERATED ALWAYS AS IDENTITY,
    hotel_id int4 NOT NULL
);

CREATE TABLE retreat (
    id int4 GENERATED ALWAYS AS IDENTITY,
    retreat_name varchar NOT NULL,
    department_id int4 NOT NULL,
    description varchar NOT NULL
);

CREATE TABLE users (
    id int4 GENERATED ALWAYS AS IDENTITY,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    email varchar NOT NULL,
    phone_number varchar NOT NULL,
    user_password varchar NOT NULL,
    user_role varchar NOT NULL,
    active boolean NOT NULL
);

CREATE TABLE patient (
    birth_year int4 NOT NULL,
    gender bpchar NOT NULL,
    patient_height int4 NOT NULL,
    patient_weight int4 NOT NULL
) INHERITS (users);

CREATE TABLE hospital_image (
    id int4 GENERATED ALWAYS AS IDENTITY,
    hospital_id int4 NOT NULL,
    image varchar NOT NULL
);

CREATE TABLE hotel_image (
    id int4 GENERATED ALWAYS AS IDENTITY,
    hotel_id int4 NOT NULL,
    image varchar NOT NULL
);

ALTER TABLE ONLY address
    ADD CONSTRAINT pk_address PRIMARY KEY (id);

ALTER TABLE ONLY booking
    ADD CONSTRAINT pk_booking PRIMARY KEY (id);

ALTER TABLE ONLY department
    ADD CONSTRAINT pk_department PRIMARY KEY (id);

ALTER TABLE ONLY doctor
    ADD CONSTRAINT pk_doctor PRIMARY KEY (id);

ALTER TABLE ONLY feedback
    ADD CONSTRAINT pk_feedback PRIMARY KEY (id);

ALTER TABLE ONLY hospital
    ADD CONSTRAINT pk_hospital PRIMARY KEY (id);

ALTER TABLE ONLY hospital_organizer
    ADD CONSTRAINT pk_hospital_organizer PRIMARY KEY (id);

ALTER TABLE ONLY hotel
    ADD CONSTRAINT pk_hotel PRIMARY KEY (id);

ALTER TABLE ONLY hotel_organizer
    ADD CONSTRAINT pk_hotel_organizer PRIMARY KEY (id);


ALTER TABLE ONLY retreat
    ADD CONSTRAINT pk_retreat PRIMARY KEY (id);

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_user PRIMARY KEY (id);

ALTER TABLE ONLY hospital_image
    ADD CONSTRAINT pk_hospital_image PRIMARY KEY (id);

ALTER TABLE ONLY hotel_image
    ADD CONSTRAINT pk_hotel_image PRIMARY KEY (id);

--add fk for booking patient
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

ALTER TABLE ONLY hospital_image
    ADD CONSTRAINT fk_hospital_image_hospital FOREIGN KEY (hospital_id) REFERENCES hospital;

ALTER TABLE ONLY hotel_image
    ADD CONSTRAINT fk_hotel_image_hotel FOREIGN KEY (hotel_id) REFERENCES hotel;


