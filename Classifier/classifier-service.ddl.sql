CREATE DATABASE classifier WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


ALTER DATABASE classifier OWNER TO postgres;

\connect classifier

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


CREATE SCHEMA spring;


ALTER SCHEMA spring OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE spring.concert_category (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title text
);


ALTER TABLE spring.concert_category OWNER TO postgres;

CREATE TABLE spring.countries (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(3) NOT NULL,
    description text
);


ALTER TABLE spring.countries OWNER TO postgres;


ALTER TABLE ONLY spring.concert_category
    ADD CONSTRAINT concert_category_pkey PRIMARY KEY (uuid);


ALTER TABLE ONLY spring.countries
    ADD CONSTRAINT countries_pkey PRIMARY KEY (uuid);

ALTER TABLE ONLY spring.countries
    ADD CONSTRAINT unique_title_key UNIQUE (title);

