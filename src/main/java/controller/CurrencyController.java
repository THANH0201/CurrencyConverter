package controller;
// CurrencyController.java
import model.Currency;
import view.CurrencyView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyController {
    private List<Currency> currencies;
    private CurrencyView view;

    public CurrencyController(CurrencyView view) {
        this.view = view;
        this.currencies = loadCurrenciesFromDatabase();
        /*
        currencies = List.of(
                new Currency("USD", "US Dollar", 1.0),
                new Currency("EUR", "Euro", 0.85),
                new Currency("JPY", "Japanese Yen", 110.0),
                new Currency("VND", "Viet Nam Dong", 27000)
        );
*/
        view.getSource().getItems().addAll(currencies);
        view.getTarget().getItems().addAll(currencies);

        view.getConvert().setOnAction(e -> convert());
    }

    private List<Currency> loadCurrenciesFromDatabase() {
        List<Currency> list = new ArrayList<>();
        String jdbcUrl = "jdbc:mariadb://localhost:3307/queries";
        String username = "appuser";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT abbreviation, name, rate FROM Currency")) {
            while (rs.next()) {
                String abbr = rs.getString("abbreviation");
                String name = rs.getString("name");
                double rate = rs.getDouble("rate");

                list.add(new Currency(abbr, name, rate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void convert() {
        try {
            double amount = Double.parseDouble(view.getInput().getText());
            Currency source = view.getSource().getValue();
            Currency target = view.getTarget().getValue();

            if (source == null || target == null) {
                view.getError().setText("Please select both currencies.");
                return;
            }
            // reference: usd
            double result = amount * target.getRate()/source.getRate();

            view.getResult().setText(String.format("%.2f", result));
            view.getResultUnitLabel().setText(target.getAbbreviation());
            view.getError().setText("");
        } catch (NumberFormatException ex) {
            view.getError().setText("Invalid amount. Please enter a number into Amount box.");
        }
    }
}



