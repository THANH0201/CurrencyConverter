package dao;

import entity.Currency;
import java.sql.*;
import datasource.MariaDbConnection;
import java.util.*;
import view.CurrencyView;

public class CurrencyDao {
    public List<Currency> getCurrencies() {
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT abbreviation, name, rate FROM currency";
        List<Currency> currencies = new ArrayList<>();

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                String abbreviation = rs.getString(1);
                String name = rs.getString(2);
                double rate = rs.getDouble(3);
                Currency currency = new Currency(abbreviation, name, rate);
                currencies.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }
    //get rate
    public double getRate(String abbreviation) {
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT rate FROM currency WHERE abbreviation = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, abbreviation);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

