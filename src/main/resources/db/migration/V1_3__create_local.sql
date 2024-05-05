CREATE TABLE local
(
    local_id    SERIAL       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    owner_id    INT          NOT NULL,
    PRIMARY KEY (local_id),
    UNIQUE (name),
    CONSTRAINT fk_local_owner
        FOREIGN KEY (owner_id)
            REFERENCES owner (owner_id)
)