CREATE DATABASE classifier_service WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';

ALTER DATABASE classifier_service OWNER TO root;

\connect classifier_service

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


CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO root;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE security.concert_category (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title text
);


ALTER TABLE security.concert_category OWNER TO root;

CREATE TABLE security.countries (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(3) NOT NULL,
    description text
);


ALTER TABLE security.countries OWNER TO root;


ALTER TABLE ONLY security.concert_category
    ADD CONSTRAINT concert_category_pkey PRIMARY KEY (uuid);


ALTER TABLE ONLY security.countries
    ADD CONSTRAINT countries_pkey PRIMARY KEY (uuid);

ALTER TABLE ONLY security.countries
    ADD CONSTRAINT unique_title_key UNIQUE (title);

