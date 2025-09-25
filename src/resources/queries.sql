
--Queries:
-- A query that retrieves all the currencies from the database.
SELECT * FROM currency;
-- A query that retrieves the currency with the abbreviation (or other abbreviation, if you don't have EUR in your database).EUR
SELECT * FROM currency WHERE abbreviation = "EUR";
-- A query that retrieves the number of currencies in the database.
SELECT COUNT(*) AS Total_Currencies FROM currency;
-- A query that retrieves the currency with the highest exchange rate.
SELECT * FROM Currency WHERE rate = (SELECT MAX(rate) FROM Currency);

