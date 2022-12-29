INSERT INTO authorities (authority)
    VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users(username, password, name, smart, avatar_url, age, profession)
    VALUES ('admin', '$2a$10$lnskmuGjw.8yk65OjIRgFuB5MljxlsmtHPYFYsckI92x2i.UCDq1K', 'admin', true, null, 18, 'Administrator');

INSERT INTO users_authorities(user_id, authority_id)
    VALUES (1, 1), (1, 2);