package controller;
import dao.CurrencyDao;
import datasource.MariaDbConnection;
import entity.Currency;
import view.CurrencyView;

import java.util.List;

public class CurrencyController {
    private final CurrencyView view;
    private final CurrencyDao currencyDao;


    public CurrencyController(CurrencyView view) {
        this.view = view;
        this.currencyDao = new CurrencyDao();
        //display connection failed
        if (MariaDbConnection.getConnection() == null) {
            view.getError().setText("Connection failed, try again.");
            return;
        }

        List<Currency> currencies = currencyDao.getCurrencies();
        if (currencies != null) {
            view.getSource().getItems().addAll(currencies);
            view.getTarget().getItems().addAll(currencies);
        } else {
            view.getError().setText("Error loading database.");
        }
        view.getConvert().setOnAction(e -> convert());
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
            double sourceRate = currencyDao.getRate(source.getAbbreviation());
            double targetRate = currencyDao.getRate(target.getAbbreviation());
            double result = amount * sourceRate/targetRate;

            view.getResult().setText(String.format("%.2f", result));
            view.getResultUnitLabel().setText(target.getAbbreviation());
            view.getError().setText("1 " + source.getAbbreviation() + " = " + String.format("%.2f",sourceRate/targetRate) + " " + target.getAbbreviation());
        } catch (NumberFormatException ex) {
            view.getError().setText("Invalid amount. Please enter a number into Amount box.");
        }
    }

}



