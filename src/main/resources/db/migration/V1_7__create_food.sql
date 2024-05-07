CREATE TABLE food
(
    food_id     SERIAL        NOT NULL,
    name        VARCHAR(255)  NOT NULL,
    category    VARCHAR(255)  NOT NULL,
    description TEXT          NOT NULL,
    price       NUMERIC(7, 2) NOT NULL,
    calories    INT           NOT NULL,
    local_id    INT           NOT NULL,
    PRIMARY KEY (food_id),
    CONSTRAINT fk_food_local
        FOREIGN KEY (local_id)
            REFERENCES local (local_id)
)