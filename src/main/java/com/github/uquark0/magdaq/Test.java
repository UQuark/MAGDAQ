package com.github.uquark0.magdaq;

import com.github.uquark0.magdaq.html.PrintRowHTML;
import com.github.uquark0.magdaq.html.QuotationRowHTML;
import com.github.uquark0.magdaq.html.Stacker;
import com.github.uquark0.magdaq.html.TerminalScreenHTML;
import gui.ava.html.Html2Image;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Stacker quotationStacker = new Stacker(new QuotationRowHTML(), 36);
        Stacker printStacker = new Stacker(new PrintRowHTML(), 11);
        TerminalScreenHTML terminalScreen = new TerminalScreenHTML();

        for (int i = 0; i < 100; i++)
            quotationStacker.addValues("123", "123", "123", "123");

        for (int i = 0; i < 100; i++)
            printStacker.addValues("000", "000");
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

        terminalScreen.linkValuesArray(quotationStacker.getStack(), printStacker.getStack());
        Html2Image.fromHtml(terminalScreen.getHTML()).getImageRenderer().saveImage("test.bmp");
    }
}
