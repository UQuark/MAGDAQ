package com.github.uquark0.magdaq.economy;

public class Transaction {
    public final MoneyAmount price;
    public final int volume;
    private final Order a, b;

    public Transaction(Order a, Order b) {
        this.a = a;
        this.b = b;

        LimitOrder limitA = (LimitOrder) a;
        LimitOrder limitB = (LimitOrder) b;

        if (limitA.id < limitB.id)
            price = limitA.price;
        else
            price = limitB.price;

        volume = Math.min(limitA.amount, limitB.amount);
    }

    public void perform() {
        a.amount -= volume;
        b.amount -= volume;
        a.owner.performTransaction(this, a);
        b.owner.performTransaction(this, b);
    }

    @Override
    public String toString() {
        return String.format("%s %d", price.toString(), volume);
    }
}
