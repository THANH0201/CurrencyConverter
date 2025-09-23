package model;

public class Currency {
    private String abbreviation;
    private String name;
    private double rate;

    public Currency(String abbreviation, String name, double rate) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rate = rate;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}
