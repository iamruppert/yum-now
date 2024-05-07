CREATE TABLE delivery_address
(
    delivery_address_id SERIAL       NOT NULL,
    code                VARCHAR(255) NOT NULL,
    country             VARCHAR(255) NOT NULL,
    city                VARCHAR(255) NOT NULL,
    postal_code         VARCHAR(255) NOT NULL,
    street              VARCHAR(255) NOT NULL,
    building_number     INT          NOT NULL,
    apartment_number    INT,
    PRIMARY KEY (delivery_address_id)
);