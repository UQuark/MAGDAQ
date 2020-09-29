package com.github.uquark0.magdaq.economy;

import java.util.ArrayList;

public class QuotationRow {
    public final MoneyAmount price;
    public final int volume;

    public QuotationRow(MoneyAmount price, int volume) {
        this.price = price;
        this.volume = volume;
    }
}
