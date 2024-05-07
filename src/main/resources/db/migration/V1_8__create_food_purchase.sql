CREATE TABLE food_purchase
(
    food_purchase_id SERIAL        NOT NULL,
    quantity         INT           NOT NULL,
    total_price      NUMERIC(7, 2) NOT NULL,
    food_id          INT           NOT NULL,
    purchase_id      INT           NOT NULL,
    PRIMARY KEY (food_purchase_id),
    CONSTRAINT fk_food_purchase_food
        FOREIGN KEY (food_id)
            REFERENCES food (food_id),
    CONSTRAINT fk_food_purchase_purchase
        FOREIGN KEY (purchase_id)
            REFERENCES purchase (purchase_id)
)