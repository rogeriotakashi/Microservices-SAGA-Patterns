DROP TABLE IF EXISTS Products;

CREATE TABLE Products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200),
  price DOUBLE
);


INSERT INTO Products (name,price) VALUES
  ('Coke',2.99);