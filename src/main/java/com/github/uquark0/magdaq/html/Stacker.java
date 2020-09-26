package com.github.uquark0.magdaq.html;

public class Stacker {
    private String result = "";
    private final HTMLPattern pattern;
    private final int limit;
    private int count = 0;

    public Stacker(HTMLPattern pattern, int limit) {
        this.pattern = pattern;
        this.limit = limit;
    }

    public Stacker(HTMLPattern pattern) {
        this(pattern, -1);
    }

    public void addValues(String... values) {
        if (count >= limit)
            return;
        count++;
        pattern.linkValuesArray(values);
        result += pattern.getHTML();
    }

    public String getStack() {
        return result;
    }
}
