
CREATE TABLE IF NOT EXISTS payments(
   payment_id VARCHAR(255) PRIMARY KEY,
   amount NUMERIC(38, 2) NOT NULL,
   created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
    user_id VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    state VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_type VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
     order_id VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY,
     products VARCHAR(255) NOT NULL,
     quantity INTEGER NOT NULL,
     payment VARCHAR(255) NOT NULL UNIQUE,
     customer VARCHAR(255) NOT NULL
);

ALTER TABLE orders ADD CONSTRAINT ORDER_PAYMENT_ID_FK
    FOREIGN KEY (payment) REFERENCES payments(payment_id);

ALTER TABLE orders ADD CONSTRAINT ORDER_CUSTOMER_ID_FK
    FOREIGN KEY (customer) REFERENCES users(user_id);