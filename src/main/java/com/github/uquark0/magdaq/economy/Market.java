package com.github.uquark0.magdaq.economy;

import java.util.HashMap;

public class Market {
    private final HashMap<Integer, MarketMaker> marketMakers = new HashMap<>();

    public Market() {

    }

    public void addStock(int stockId) {
        if (marketMakers.containsKey(stockId))
            return;
        marketMakers.put(stockId, new MarketMaker(stockId, this));
    }

    public void sendOrder(Order order) {
        marketMakers.get(order.stockId).sendOrder(order);
    }

    public void notify(Transaction transaction) {

    }
}
