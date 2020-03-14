DROP TABLE IF EXISTS Order_Services_Status;
DROP TABLE IF EXISTS Services_For_Approval;

CREATE TABLE Order_Services_Status (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  order_id INT NOT NULL,
  status VARCHAR(20),
  service_name VARCHAR(50)
);

CREATE TABLE Services_For_Approval(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  service_name VARCHAR(50),
  operation VARCHAR(50)
);


INSERT INTO Services_For_Approval (service_name, operation) VALUES
  ('Customer-Service','reserveCredit');
  
INSERT INTO Services_For_Approval (service_name, operation) VALUES
  ('Stock-Service','processOrder');