CREATE TABLE IF NOT EXISTS users
(
    username VARCHAR(50)  NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN      NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX IF NOT EXISTS ix_auth_username ON authorities (username, authority);

-- 외래 키 제약 때문에 authorities 테이블부터 삭제
DELETE FROM authorities;
DELETE FROM users;

INSERT INTO users VALUES ('bach', '{noop}Bach@12345', '1');
INSERT INTO authorities VALUES ('bach', 'read');

INSERT INTO users VALUES ('admin', '{bcrypt}$2a$12$xs3tHGLZAH0coBPWCYNdWeA0045orNyAJVzsgvzxPFZtxB9I60llK', '1');
INSERT INTO authorities VALUES ('admin', 'admin');

CREATE TABLE IF NOT EXISTS customer
(
    "id"    SERIAL PRIMARY KEY,
    "email" VARCHAR(45)  NOT NULL,
    "pwd"   VARCHAR(200) NOT NULL,
    "role"  VARCHAR(45)  NOT NULL
);

DELETE FROM customer;

INSERT INTO customer (email, pwd, role)
VALUES ('happy@example.com', '{noop}happy@12345', 'read');
INSERT INTO customer (email, pwd, role)
VALUES ('admin@example.com', '{bcrypt}$2a$12$xs3tHGLZAH0coBPWCYNdWeA0045orNyAJVzsgvzxPFZtxB9I60llK', 'admin');
