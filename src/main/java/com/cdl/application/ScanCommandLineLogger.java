package com.cdl.application;

import com.cdl.charging.CheckOutItem;

import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.String.format;


public class ScanCommandLineLogger implements ScanLogger {

    public static final String SUBITEM_OUTPUT = "Product Code:%s Description:%s Value:%s Subtotal:%s";
    public static final String TOTAL_AMOUNT = "Number of Items:%s Total Amount:%s";
    private static final String STARTINGITEMCHECKOUT = "Starting item Check Out";
    private static NumberFormat GBP = NumberFormat.getCurrencyInstance(Locale.UK);

    @Override
    public void outputItemAndSubtotal(CheckOutItem item) {

        final String productCode = item.getProductCode();
        final String chargeDescription = item.getChargeDescription();
        final int value = item.getChargeValue().getValue();
        final int subTotal = item.getSubTotal();

        System.out.println(format(SUBITEM_OUTPUT, productCode, chargeDescription, asPounds(value), asPounds(subTotal)));
    }


    @Override
    public void outputFinalCheckOutTotals(int numberOfItems, int total) {

        System.out.println(format(TOTAL_AMOUNT, numberOfItems, asPounds(total)));

    }

    @Override
    public void startingCheckout() {
        System.out.println(STARTINGITEMCHECKOUT);
    }


    private String asPounds(int value){
        return GBP.format(value / 100.0);
    }
}
