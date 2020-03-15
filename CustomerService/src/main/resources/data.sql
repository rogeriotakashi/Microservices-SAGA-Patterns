DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Operation_History;


CREATE TABLE Customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(200) NOT NULL,
  name VARCHAR(200) NOT NULL,
  total_availible DOUBLE NOT NULL
);

CREATE TABLE Operation_History (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  order_id INT NOT NULL,
  username VARCHAR(200) NOT NULL,
  operation_type VARCHAR(200) NOT NULL,
  total_operation DOUBLE NOT NULL
);

INSERT INTO Customer (username, name, total_availible) VALUES
  ('rogi', 'rogerio', 1000.00);
