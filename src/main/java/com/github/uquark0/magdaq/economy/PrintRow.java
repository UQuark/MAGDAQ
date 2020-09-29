package com.github.uquark0.magdaq.economy;

public class PrintRow {
    public final MoneyAmount price;
    public final int volume;

    public PrintRow(MoneyAmount price, int volume) {
        this.price = price;
        this.volume = volume;
    }

    public PrintRow(Transaction transaction) {
        price = transaction.price;
        volume = transaction.volume;
    }
}
