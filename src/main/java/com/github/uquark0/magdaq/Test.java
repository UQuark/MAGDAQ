package com.github.uquark0.magdaq;

import com.github.uquark0.magdaq.economy.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Market market = new Market();
        market.addStock(0);

        Broker printBroker = new Broker() {
            @Override
            public void performTransaction(Transaction transaction, Order order) {
                System.out.println(transaction.toString());
            }
        };

        Broker voidBroker = new Broker() {
            @Override
            public void performTransaction(Transaction transaction, Order order) {

            }
        };

        BuyLimitOrder b1 = new BuyLimitOrder(0, new MoneyAmount(10, 0), 15, printBroker);
        BuyLimitOrder b2 = new BuyLimitOrder(0, new MoneyAmount(10, 5), 10, printBroker);
        BuyLimitOrder b3 = new BuyLimitOrder(0, new MoneyAmount(11, 7), 5, printBroker);
        BuyLimitOrder b4 = new BuyLimitOrder(0, new MoneyAmount(12, 0), 8, printBroker);
        BuyLimitOrder b5 = new BuyLimitOrder(0, new MoneyAmount(13, 0), 10, printBroker);

        SellLimitOrder s1 = new SellLimitOrder(0, new MoneyAmount(8, 0), 15, voidBroker);
        SellLimitOrder s2 = new SellLimitOrder(0, new MoneyAmount(9, 0), 5, voidBroker);
        SellLimitOrder s3 = new SellLimitOrder(0, new MoneyAmount(10, 0), 7, voidBroker);
        SellLimitOrder s4 = new SellLimitOrder(0, new MoneyAmount(15, 0), 6, voidBroker);
        SellLimitOrder s5 = new SellLimitOrder(0, new MoneyAmount(16, 0), 10, voidBroker);

        market.sendOrder(b1);
        market.sendOrder(b2);
        market.sendOrder(b3);
        market.sendOrder(b4);
        market.sendOrder(b5);
        market.sendOrder(s1);
        market.sendOrder(s2);
        market.sendOrder(s3);
        market.sendOrder(s4);
        market.sendOrder(s5);
    }
}
