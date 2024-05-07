CREATE TABLE opinion
(
    opinion_id  SERIAL                               NOT NULL,
    description TEXT                                 NOT NULL,
    stars       INT CHECK (stars IN (1, 2, 3, 4, 5)) NOT NULL,
    local_id    INT                                  NOT NULL,
    customer_id INT                                  NOT NULL,
    PRIMARY KEY (opinion_id),
    CONSTRAINT fk_opinion_local
        FOREIGN KEY (local_id)
            REFERENCES local (local_id),
    CONSTRAINT fk_opinion_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (customer_id)
)