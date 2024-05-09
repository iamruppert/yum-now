CREATE TABLE local_local_delivery_address
(

    local_local_delivery_address_id SERIAL NOT NULL,
    local_delivery_address_id       INT    NOT NULL,
    local_id                        INT    NOT NULL,
    PRIMARY KEY (local_local_delivery_address_id),
    CONSTRAINT local_local_delivery_address_address
        FOREIGN KEY (local_delivery_address_id)
            REFERENCES local_delivery_address (local_delivery_address_id),
    CONSTRAINT local_local_delivery_address_local
        FOREIGN KEY (local_id)
            REFERENCES local (local_id)
)