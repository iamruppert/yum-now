CREATE TABLE local_delivery_address_local
(

    local_delivery_address_local_id SERIAL NOT NULL,
    local_delivery_address_id       INT    NOT NULL,
    local_id                        INT    NOT NULL,
    PRIMARY KEY (local_delivery_address_id)
)