CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE wallet (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    amount NUMERIC(10,2)
);

CREATE TABLE users_wallet (
    id SERIAL PRIMARY KEY,
    wallet_id INTEGER REFERENCES wallet(id),
    users_id INTEGER REFERENCES users(id)
);

CREATE TABLE wallet_items (
    id SERIAL PRIMARY KEY,
    wallet_id INTEGER REFERENCES wallet(id),
    date DATE,
    type VARCHAR(2),
    description VARCHAR(500),
    amount NUMERIC(10,2)
);