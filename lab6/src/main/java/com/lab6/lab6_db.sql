CREATE DATABASE IF NOT EXISTS sales_db

CREATE TABLE Seller
(
    id    INT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Client
(
    id    INT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    phone VARCHAR(50)  NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Product
(
    id    INT PRIMARY KEY,
    name  VARCHAR(100)   NOT NULL,
    price DECIMAL(12, 2) NOT NULL
);

CREATE TABLE Sale
(
    id         INT PRIMARY KEY,
    seller_id  INT  NOT NULL,
    client_id  INT  NOT NULL,
    product_id INT  NOT NULL,
    sale_date  DATE NOT NULL,
    FOREIGN KEY (seller_id) REFERENCES Seller (id),
    FOREIGN KEY (client_id) REFERENCES Client (id),
    FOREIGN KEY (product_id) REFERENCES Product (id)
);