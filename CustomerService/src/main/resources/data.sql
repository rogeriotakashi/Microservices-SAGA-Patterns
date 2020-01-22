DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(200) NOT NULL,
  name VARCHAR(200) NOT NULL,
  total_availible DOUBLE NOT NULL
);

INSERT INTO Customer (username, name, total_availible) VALUES
  ('rogi', 'rogerio', 1000.00);
