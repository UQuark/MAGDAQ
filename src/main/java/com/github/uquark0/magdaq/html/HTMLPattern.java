package com.github.uquark0.magdaq.html;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class HTMLPattern {
    private final String pattern;
    private String[] values;

    public HTMLPattern(String patternPath) throws IOException {
        InputStream inputStream = QuotationRowHTML.class.getResourceAsStream(patternPath);
        pattern = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }

    public void linkValuesArray(String... values) {
        this.values = values;
    }

    public String getHTML() {
        return String.format(pattern, values);
    }

}
