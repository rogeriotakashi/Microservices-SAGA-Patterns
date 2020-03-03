DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200),
  price DOUBLE
);


INSERT INTO Product (name,price) VALUES
  ('Coke',2.99);
  
INSERT INTO Product (name,price) VALUES
('Orange Juice',5.99);