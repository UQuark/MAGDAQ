package com.github.uquark0.magdaq.economy;

import java.util.ArrayList;
import java.util.HashMap;

public class Market {
    private final HashMap<Integer, MarketMaker> marketMakers = new HashMap<>();

    public Market() {

    }

    public void addStock(int stockId) {
        if (marketMakers.containsKey(stockId))
            return;
        marketMakers.put(stockId, new MarketMaker(stockId));
    }
}
