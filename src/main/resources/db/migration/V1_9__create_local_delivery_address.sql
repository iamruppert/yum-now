CREATE TABLE local_delivery_address
(
    local_delivery_address_id SERIAL       NOT NULL,
    code                      VARCHAR(255) NOT NULL,
    country                   VARCHAR(255) NOT NULL,
    city                      VARCHAR(255) NOT NULL,
    postal_code               VARCHAR(255) NOT NULL,
    street                    VARCHAR(255) NOT NULL,
    PRIMARY KEY (local_delivery_address_id)
)