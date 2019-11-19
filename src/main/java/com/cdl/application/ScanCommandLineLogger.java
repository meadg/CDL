package com.cdl.application;

import com.cdl.charging.CheckOutItem;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static java.lang.String.format;

public class ScanCommandLineLogger implements ScanLogger {

    public static final String SUBITEM_OUTPUT = "Product Code:%s Description:%s Value:%s Subtotal:%s";
    public static final String TOTAL_AMOUNT = "Number of Items:%s Total Amount:%s";
    private static final String STARTINGITEMCHECKOUT = "Starting item Check Out";

    @Override
    public void outputItemAndSubtotal(CheckOutItem item) {

        final String productCode = item.getProductCode();
        final String chargeDescription = item.getChargeDescription();
        final int value = item.getChargeValue().getValue();
        final int subTotal = item.getSubTotal();

        System.out.println(format(SUBITEM_OUTPUT, productCode, chargeDescription,value,subTotal));

    }

    @Override
    public void outputFinalCheckOutTotals(int numberOfItems, int total) {

        System.out.println(format(TOTAL_AMOUNT,numberOfItems,total));

    }

    @Override
    public void startingCheckout() {
        System.out.println(STARTINGITEMCHECKOUT);
    }
}
