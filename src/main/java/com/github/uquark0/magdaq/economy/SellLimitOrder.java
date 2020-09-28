package com.github.uquark0.magdaq.economy;

public class SellLimitOrder extends LimitOrder {
    public SellLimitOrder(int stockId, MoneyAmount price, int amount, Broker owner) {
        super(stockId, price, amount, owner);
    }

    @Override
    public int compareTo(LimitOrder limitOrder) {
        if (!(limitOrder instanceof SellLimitOrder))
            throw new IllegalArgumentException("Can compare only with SellLimitOrder");
        return Long.compare(price.value, limitOrder.price.value);
    }
}
