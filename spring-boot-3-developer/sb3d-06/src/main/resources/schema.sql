DROP TABLE IF EXISTS article;

CREATE TABLE article
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(2000) NOT NULL
);
