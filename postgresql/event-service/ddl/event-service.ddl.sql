CREATE DATABASE event_service WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


ALTER DATABASE event_service OWNER TO root;

\connect event_service

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

CREATE TYPE security."enum type" AS (
	"FILMS" character varying(10),
	"CONCERTS" character varying(10)
);


ALTER TYPE security."enum type" OWNER TO root;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE security.concerts (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(50),
    description text,
    dt_event timestamp(3) without time zone,
    dt_dt_end_of_sale timestamp(3) without time zone,
    type character varying(10),
    status character varying(10),
    category character varying(100),
    author character varying(50)
);


ALTER TABLE security.concerts OWNER TO root;

CREATE TABLE security.films (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(50),
    description text,
    dt_event timestamp(3) without time zone,
    dt_dt_end_of_sale timestamp(3) without time zone,
    type character varying(10),
    status character varying(10),
    country character varying(100),
    release_year bigint,
    release_date character varying(30),
    duration bigint,
    author character varying(50)
);


ALTER TABLE security.films OWNER TO root;

ALTER TABLE ONLY security.concerts
    ADD CONSTRAINT concerts_pkey PRIMARY KEY (uuid);


ALTER TABLE ONLY security.films
    ADD CONSTRAINT films_pkey PRIMARY KEY (uuid);


