package com.github.uquark0.magdaq.economy;

public abstract class LimitOrder extends Order implements Comparable<LimitOrder> {
    public MoneyAmount price;

    public LimitOrder(int stockId, MoneyAmount price, int amount, Broker owner) {
        super(stockId, amount, owner);
        this.price = price;
    }
}
