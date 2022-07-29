\connect user_service

INSERT INTO security.authorities(
id, role_name)
VALUES (1, 'ROLE_USER');

INSERT INTO security.authorities(
id, role_name)
VALUES (2, 'ROLE_ADMIN');

INSERT INTO security.users(
uuid, dt_create, dt_update, mail, nick, status, password)
VALUES ('4168e03d-776e-412a-ac8b-06e2e6e2c775', now(), now(), 'admin@gmail.com', 'admin', 'ACTIVATED', '$2a$10$ZfDcdDz/Agpui6BVhr5yp.LVcSL0ZA2a03ZP9e6srf/n.sQgg3Bsa');

INSERT INTO security.users_authorities(
users_uuid, authorities_id)
VALUES ('4168e03d-776e-412a-ac8b-06e2e6e2c775', 1);

INSERT INTO security.users_authorities(
users_uuid, authorities_id)
VALUES ('4168e03d-776e-412a-ac8b-06e2e6e2c775', 2);
