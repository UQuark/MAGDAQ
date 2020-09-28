package com.github.uquark0.magdaq.economy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MarketMaker {

    private final ArrayList<BuyLimitOrder> buyLimitOrders = new ArrayList<>();
    private final ArrayList<SellLimitOrder> sellLimitOrders = new ArrayList<>();
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private final int stockId;
    private final Market owner;
    private int volume;
    private int orderId;

    public MarketMaker(int stockId, Market owner) {
        this.stockId = stockId;
        this.owner = owner;
    }

    public void sendOrder(Order order) {
        if (order.stockId != stockId)
            throw new IllegalArgumentException("Invalid MarketMaker");
        order.id = getOrderId();
        if (order instanceof BuyLimitOrder)
            buyLimitOrders.add((BuyLimitOrder) order);
        if (order instanceof SellLimitOrder)
            sellLimitOrders.add((SellLimitOrder) order);
        Collections.sort(buyLimitOrders);
        Collections.sort(sellLimitOrders);
        resolve();
    }

    public boolean isParity() {
        return buyLimitOrders.size() == 0 ||
                sellLimitOrders.size() == 0 ||
                (sellLimitOrders.get(0).price.value > buyLimitOrders.get(0).price.value);
    }

    public void performTransaction(Order a, Order b) {
        Transaction transaction = new Transaction(a, b);
        transaction.perform();
        volume += transaction.volume;
        transactions.add(transaction);
        owner.notify(transaction);
    }

    public void resolve() {
        while (!isParity()) {
            BuyLimitOrder buy = buyLimitOrders.get(0);
            SellLimitOrder sell = sellLimitOrders.get(0);
            performTransaction(buy, sell);
            if (buy.amount == 0)
                buyLimitOrders.remove(0);
            if (sell.amount == 0)
                sellLimitOrders.remove(0);
        }
    }

    public List<Transaction> getTransactions(int max) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (result.size() == max)
                break;
            result.add(t);
        }
        return result;
    }

    private int getOrderId() {
        orderId++;
        return orderId;
    }
}
