DROP TABLE IF EXISTS Order_Services_Status;

CREATE TABLE Order_Services_Status (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  order_id INT NOT NULL,
  status VARCHAR(20),
  service_name VARCHAR(50)
);

