DROP TABLE IF EXISTS Stock;

CREATE TABLE Stock (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT UNIQUE,
  quantity INT NOT NULL
);

INSERT INTO Stock (product_id, quantity) VALUES (1,5);
INSERT INTO Stock (product_id, quantity) VALUES (2,5);