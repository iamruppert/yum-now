CREATE TABLE confirmation
(
    confirmation_id     SERIAL                   NOT NULL,
    confirmation_number VARCHAR(255)             NOT NULL,
    total_price         NUMERIC(7, 2)            NOT NULL,
    time                TIMESTAMP WITH TIME ZONE NOT NULL,
    purchase_id         INT                      NOT NULL,
    PRIMARY KEY (confirmation_id),
    UNIQUE (confirmation_number),
    constraint fk_confirmation_purchase
        FOREIGN KEY (purchase_id)
            REFERENCES purchase (purchase_id)
)