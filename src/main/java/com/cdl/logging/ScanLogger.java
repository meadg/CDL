package com.cdl.logging;

import com.cdl.domain.CheckOutItem;

public interface ScanLogger {

    void outputItemAndSubtotal(CheckOutItem checkOutItem);
    void outputFinalCheckOutTotals(int numberOfItems, int total);
    void startingCheckout();

    void unknownCommand();
}
