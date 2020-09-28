package com.github.uquark0.magdaq.economy;

public class BuyLimitOrder extends LimitOrder {
    public BuyLimitOrder(int stockId, MoneyAmount price, int amount, Broker owner) {
        super(stockId, price, amount, owner);
    }

    @Override
    public int compareTo(LimitOrder limitOrder) {
        if (!(limitOrder instanceof BuyLimitOrder))
            throw new IllegalArgumentException("Can compare only with BuyLimitOrder");
        return Long.compare(limitOrder.price.value, price.value);
    }
}
