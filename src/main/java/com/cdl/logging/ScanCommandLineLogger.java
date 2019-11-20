package com.cdl.logging;

import com.cdl.application.SessionState;
import com.cdl.domain.CheckOutItem;
import com.cdl.domain.StockItem;

import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.String.format;


public class ScanCommandLineLogger implements ScanLogger {

    public static final String SUBITEM_OUTPUT = "Product Code:%s Description:%s Value:%s Subtotal:%s";
    public static final String TOTAL_AMOUNT = "Number of Items:%s Total Amount:%s";
    private static final String STARTINGITEMCHECKOUT = "Starting Check Out";
    private static final String UNKNOWN_COMMAND = "Unknown command Please try again";
    private static final String INVALID_STATE_COMMAND = "Command is not valid for current session state which is: '%s' Please try again";
    private static final String PRODUCT_NOT_FOUND = "Product: %s not found Please try again";
    ;
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

    @Override
    public void unknownCommand() {
        System.out.println(UNKNOWN_COMMAND);
    }

    @Override
    public void invalidCommandForState(SessionState currentState) {
        System.out.println(format(INVALID_STATE_COMMAND, currentState.name()));
    }

    @Override
    public void registerInvalidProduct(StockItem stockItem) {
        System.out.println(format(PRODUCT_NOT_FOUND, stockItem.getStockItemId()));
    }


    private String asPounds(int value) {
        return GBP.format(value / 100.0);
    }
}
