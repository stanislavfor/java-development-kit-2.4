-- schema.sql
CREATE TABLE IF NOT EXISTS employees (
     id INT AUTO_INCREMENT PRIMARY KEY,
     phone_number VARCHAR(20),
     employee_name VARCHAR(100),
     work_experience INT
);
