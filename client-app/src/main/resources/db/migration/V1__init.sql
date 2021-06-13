CREATE TABLE IF NOT EXISTS order (
    id int NOT NULL PRIMARY KEY,
    name varchar(50),
    price double precision,
    status varchar(15),
    customer_name varchar(250),
    phone_number varchar(20),
    address varchar(250),
    created timestamp,
    updated timestamp,
    delivered timestamp

);
CREATE SEQUENCE IF NOT EXISTS order_sequence INCREMENT 1 START 1 OWNED BY order.id;