CREATE DATABASE trading_system;

USE trading_system;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE,
    active BOOLEAN DEFAULT true,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE stocks (
    stock_id INT AUTO_INCREMENT PRIMARY KEY,
    stock_name VARCHAR(100) NOT NULL,
    stock_symbol VARCHAR(10) NOT NULL UNIQUE,
    current_price DECIMAL(10, 2),
    pe_ratio DECIMAL(10, 2),
    volume BIGINT,
    market_cap BIGINT,
    week_52_high DECIMAL(10, 2),
    week_52_low DECIMAL(10, 2),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users (username, password, email) VALUES ('testuser', 'password', 'test@example.com');

INSERT INTO stocks (stock_name, stock_symbol, current_price, pe_ratio, volume, market_cap, week_52_high, week_52_low) VALUES 
('Apple Inc.', 'AAPL', 150.00, 25.00, 1000000, 2000000000, 200.00, 120.00),
('Microsoft Corporation', 'MSFT', 280.00, 30.00, 1500000, 1800000000, 300.00, 250.00),
('Amazon.com', 'AMZN', 3100.00, 70.00, 500000, 2500000000, 3500.00, 2800.00),
('Tesla, Inc.', 'TSLA', 700.00, 100.00, 2000000, 800000000, 900.00, 400.00);
