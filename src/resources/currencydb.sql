-- A statement for dropping the previous version of the database, if it exists.
DROP DATABASE IF EXISTS queries;

-- A statement for creating the database.
CREATE DATABASE queries;
USE queries;

-- A statement for creating a table for storing the Currency objects.
CREATE TABLE Currency (
      abbreviation VARCHAR(3) NOT NULL,
      name VARCHAR(50) NOT NULL,
      rate DECIMAL(5, 2) NOT NULL,
      PRIMARY KEY (abbreviation)
);

-- Statements for populating the table with data. You should include at least eight currencies with up-to-date exchange rates in the table.
INSERT INTO Currency (abbreviation, name, rate) VALUES
    ('USD', 'US Dollar', 1.00),
    ('EUR', 'Euro', 0.85),
    ('GBP', 'British Pound', 0.73),
    ('JPY', 'Japanese Yen', 147.51),
    ('CAD', 'Canadian Dollar', 1.38),
    ('CHF', 'Swiss Franc', 0.79),
    ('AUD', 'Australian Dollar', 0.65),
    ('THB', 'Thai Baht', 36.20),
    ('SGD', 'Singapore Dollar', 1.37),
    ('MXN', 'Mexican Peso', 17.45);

-- A statement for dropping the user account appuser, if it EXISTS.
DROP USER IF EXISTS 'appuser'@'localhost' ;

-- A statement for creating the user account appuser.
CREATE user 'appuser'@'localhost' IDENTIFIED BY '123456';

-- Statements for granting the privileges to the user account appuser. Think of your application: what privileges does it need? The user account should have only the privileges it needs, and no more.
GRANT CREATE, SELECT, INSERT, UPDATE, DELETE, DROP ON queries.* TO 'appuser'@'localhost';
FLUSH PRIVILEGES;
