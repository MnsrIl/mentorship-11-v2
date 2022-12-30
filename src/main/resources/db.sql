CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    age INTEGER
);

CREATE TABLE authorities(
    id SERIAL PRIMARY KEY,
    authority VARCHAR(25) NOT NULL
);

CREATE TABLE users_authorities(
    user_id INTEGER NOT NULL REFERENCES users(id),
    authority_id INTEGER NOT NULL REFERENCES authorities(id),
    PRIMARY KEY (user_id, authority_id)
);

INSERT INTO authorities (authority)
    VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users(email, password, first_name, last_name, age)
    VALUES ('admin@admin.ru', '$2a$10$4wdoCIWG8oCKqhxQfRag9.5YohaHLjs1RS4fe8QQ97kcdU.05335S', 'Admin', 'Istrator', 18);

INSERT INTO users_authorities(user_id, authority_id)
    VALUES (1, 1), (1, 2);