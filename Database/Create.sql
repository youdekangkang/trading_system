CREATE DATABASE trading_system;

USE trading_system;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE,
    active BOOLEAN DEFAULT true,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    reserved_text VARCHAR(255),
    reserved_number DECIMAL(10, 2)
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
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    reserved_text VARCHAR(255),
    reserved_number DECIMAL(10, 2)
);

INSERT INTO users (username, password, email) VALUES ('testuser', 'password', 'test@example.com');

INSERT INTO stocks (stock_name, stock_symbol, current_price, pe_ratio, volume, market_cap, week_52_high, week_52_low) VALUES 
('Apple Inc.', 'AAPL', 150.00, 25.00, 1000000, 2000000000, 200.00, 120.00),
('Microsoft Corporation', 'MSFT', 280.00, 30.00, 1500000, 1800000000, 300.00, 250.00),
('Amazon.com', 'AMZN', 3100.00, 70.00, 500000, 2500000000, 3500.00, 2800.00),
('Tesla, Inc.', 'TSLA', 700.00, 100.00, 2000000, 800000000, 900.00, 400.00);



CREATE TABLE market_info (
    market_id INT AUTO_INCREMENT PRIMARY KEY,
    open_time TIME,
    close_time TIME,
    total_volume BIGINT,
    reserved_text VARCHAR(255),
    reserved_number DECIMAL(10, 2)
);

INSERT INTO market_info (open_time, close_time, total_volume, reserved_text, reserved_number) VALUES
('09:00:00', '17:00:00', 1000000,null , null),
('09:00:00', '17:00:00', 2000000,null ,null );


CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    stock_symbol VARCHAR(10),
    order_type ENUM('BUY', 'SELL'),
    quantity INT,
    price DECIMAL(10, 2),
    order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('OPEN', 'CLOSED', 'CANCELLED'),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (stock_symbol) REFERENCES stocks(stock_symbol)
);


CREATE TABLE trade_history (
    trade_id INT,
    order_id INT,
    user_id INT,
    stock_symbol VARCHAR(10),
    trade_type ENUM('BUY', 'SELL'),
    quantity INT,
    price DECIMAL(10, 2),
    trade_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (stock_symbol) REFERENCES stocks(stock_symbol)
);

-- 创建 roles 表
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- 创建 user_roles 表来存储用户和角色之间的关系
CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);



SHOW ENGINE INNODB STATUS;

SHOW TABLE STATUS WHERE Name = 'orders'