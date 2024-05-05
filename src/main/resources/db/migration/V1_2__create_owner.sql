CREATE TABLE owner
(
    owner_id SERIAL       NOT NULL,
    name     VARCHAR(255) NOT NULL,
    surname  VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    address  VARCHAR(255) NOT NULL,
    PRIMARY KEY (owner_id),
    UNIQUE (email)
);