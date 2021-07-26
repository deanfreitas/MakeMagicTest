CREATE TABLE IF NOT EXISTS character_school
(
    id             INTEGER     NOT NULL AUTO_INCREMENT,
    name_character VARCHAR(50) NOT NULL,
    role_character VARCHAR(50) NOT NULL,
    school         VARCHAR(50) NOT NULL,
    house          VARCHAR(50) NOT NULL,
    patronus       VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
