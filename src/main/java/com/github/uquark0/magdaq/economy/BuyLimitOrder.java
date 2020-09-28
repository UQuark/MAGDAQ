package com.github.uquark0.magdaq.economy;

public class BuyLimitOrder extends LimitOrder {
    public BuyLimitOrder(int stockId, MoneyAmount price, int amount, Broker owner) {
        super(stockId, price, amount, owner);
    }
}
