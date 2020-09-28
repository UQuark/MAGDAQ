package com.github.uquark0.magdaq.economy;

public class SellLimitOrder extends LimitOrder {
    public SellLimitOrder(int stockId, MoneyAmount price, int amount, Broker owner) {
        super(stockId, price, amount, owner);
    }
}
