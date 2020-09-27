package com.github.uquark0.magdaq.util;

public class AsynchronousTimerCache<T> extends TimerCache<T> {
    public AsynchronousTimerCache(ValueSupplier<T> valueSupplier, long ttl) {
        super(valueSupplier, ttl);
    }

    @Override
    public T getValue() {
        if (System.currentTimeMillis() - lastUpdate >= ttl || value == null) {
            new Thread(() -> {
                value = valueSupplier.getValue();
                lastUpdate = System.currentTimeMillis();
            }).start();
        }
        return value;
    }
}
