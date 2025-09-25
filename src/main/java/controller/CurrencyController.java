package controller;
import dao.CurrencyDao;
import entity.Currency;
import view.CurrencyView;

import java.util.List;

public class CurrencyController {
    private final CurrencyView view;
    public CurrencyController(CurrencyView view) {
        this.view = view;
        CurrencyDao currencyDao = new CurrencyDao();
        List<Currency> currencies = currencyDao.getCurrencies();
        if (currencies != null) {
            view.getSource().getItems().addAll(currencies);
            view.getTarget().getItems().addAll(currencies);
        } else {
            view.getError().setText("Error loading.");
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
            double result = amount * source.getRate()/target.getRate();

            view.getResult().setText(String.format("%.2f", result));
            view.getResultUnitLabel().setText(target.getAbbreviation());
            view.getError().setText("1 " + source.getAbbreviation() + " = " + String.format("%.2f",source.getRate()/target.getRate()) + " " + target.getAbbreviation());
        } catch (NumberFormatException ex) {
            view.getError().setText("Invalid amount. Please enter a number into Amount box.");
        }
    }

}



