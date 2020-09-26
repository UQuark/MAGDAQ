package com.github.uquark0.magdaq.html;

import java.io.IOException;

public class QuotationRowHTML extends HTMLPattern {
    private static final String PATTERN = "/assets/magdaq/html/quotation_row.html";

    public QuotationRowHTML() throws IOException {
        super(PATTERN);
    }
}
