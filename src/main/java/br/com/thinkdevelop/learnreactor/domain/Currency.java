package br.com.thinkdevelop.learnreactor.domain;

public enum Currency {
    USD, EUR, BRL;

    public static Currency fromValue(String value) {
        for (Currency currency : values())
            if (currency.name().equalsIgnoreCase(value)) {
                return currency;
            }
        return null;
    }
}