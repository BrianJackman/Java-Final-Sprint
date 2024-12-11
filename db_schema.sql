-- Create the users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(10) NOT NULL,
    role_id INTEGER
);

-- Create the products table
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    quantity INTEGER NOT NULL,
    seller_id INTEGER NOT NULL,
    FOREIGN KEY (seller_id) REFERENCES users(id)
);

-- Insert sample users
INSERT INTO users (username, password, email, role, role_id) VALUES
('admin1', '$2a$10$T5LjGmYiOucc5kglyDIxNez31Z7mSSiQzxSUb/e3ziko3tq75EVGW', 'admin1@example.com', 'admin', 1),
('seller1', '$2a$10$T5LjGmYiOucc5kglyDIxNez31Z7mSSiQzxSUb/e3ziko3tq75EVGW', 'seller1@example.com', 'seller', 2),
('buyer1', '$2a$10$T5LjGmYiOucc5kglyDIxNez31Z7mSSiQzxSUb/e3ziko3tq75EVGW', 'buyer1@example.com', 'buyer', 3);

-- Insert sample products
INSERT INTO products (name, price, quantity, seller_id) VALUES
('Product 1', 19.99, 100, (SELECT id FROM users WHERE username = 'seller1')),
('Product 2', 29.99, 200, (SELECT id FROM users WHERE username = 'seller1')),
('Product 3', 39.99, 150, (SELECT id FROM users WHERE username = 'seller1'));