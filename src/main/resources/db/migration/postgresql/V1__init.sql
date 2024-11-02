CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    password VARCHAR,
    email VARCHAR(100)
);

CREATE TABLE wallet (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    value NUMERIC(10,2)
);

CREATE TABLE users_wallet (
    user_id INTEGER,
    wallet_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (wallet_id) REFERENCES wallet(id)
);

CREATE TABLE wallet_items (
    id SERIAL PRIMARY KEY,
    wallet_id INTEGER,
    date DATE,
    type VARCHAR(2),
    description VARCHAR(500),
    value NUMERIC(10,2),
    FOREIGN KEY (wallet_id) REFERENCES wallet(id)
);