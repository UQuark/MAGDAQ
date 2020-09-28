package com.github.uquark0.magdaq.economy;

public class MoneyAmount {
    public final long value;

    public MoneyAmount(int whole, int fraction) {
        this(whole * 10000 + fraction * 100);
    }

    public MoneyAmount(long value) {
        this.value = value;
    }

    public int getWhole() {
        return (int) value / 10000;
    }

    public int getFraction() {
        return (int) (value % 10000) / 100;
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", getWhole(), getFraction());
    }
}
