DROP TABLE IF EXISTS courses;

CREATE TABLE courses
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)  NOT NULL,
    category    VARCHAR(20)   NOT NULL,
    rating      INT           NOT NULL,
    description VARCHAR(1000) NOT NULL
);
