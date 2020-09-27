package com.github.uquark0.magdaq.util;

public class TimerCache<T> extends Cache<T> {
    protected long ttl;
    protected long lastUpdate;

    public TimerCache(ValueSupplier<T> valueSupplier, long ttl) {
        super(valueSupplier);
        this.ttl = ttl;
    }

    @Override
    public T getValue() {
        if (System.currentTimeMillis() - lastUpdate >= ttl || value == null) {
            value = valueSupplier.getValue();
            lastUpdate = System.currentTimeMillis();
        }
        return value;
    }

    public T getValueWithoutUpdate() {
        return value;
    }
}
