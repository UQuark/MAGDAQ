package com.github.uquark0.magdaq.economy;

public abstract class Order {
    public final int stockId;
    public final int amount;
    public final Broker owner;

    public Order(int stockId, int amount, Broker owner) {
        this.stockId = stockId;
        this.amount = amount;
        this.owner = owner;
    }
}
