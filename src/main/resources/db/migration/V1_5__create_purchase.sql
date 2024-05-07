CREATE TABLE purchase
(
    purchase_id         SERIAL                   NOT NULL,
    purchase_number     VARCHAR(255)             NOT NULL,
    total_price         NUMERIC(7, 2)            NOT NULL,
    time                TIMESTAMP WITH TIME ZONE NOT NULL,
    status              VARCHAR(255)             NOT NULL,
    local_id            INT                      NOT NULL,
    customer_id         INT                      NOT NULL,
    delivery_address_id INT                      NOT NULL,
    PRIMARY KEY (purchase_id),
    UNIQUE (purchase_number),
    CONSTRAINT fk_purchase_local
        FOREIGN KEY (local_id)
            REFERENCES local (local_id),
    CONSTRAINT fk_purchase_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (customer_id),
    CONSTRAINT fk_purchase_delivery_address
        FOREIGN KEY (delivery_address_id)
            REFERENCES delivery_address (delivery_address_id)
)