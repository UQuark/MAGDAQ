package com.github.uquark0.magdaq.util;

public class Cache<T> {
    public interface ValueSupplier<T> {
        T getValue();
    }
    protected T value;
    protected final ValueSupplier<T> valueSupplier;

    public Cache(ValueSupplier<T> valueSupplier) {
        this.valueSupplier = valueSupplier;
    }

    public T getValue() {
        if (value == null)
            value = valueSupplier.getValue();
        return value;
    }

    public void invalidate() {
        value = null;
    }
}
