package com.cdl.application;

import com.cdl.charging.CheckOutItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;

public interface ScanLogger {

    void outputItemAndSubtotal(CheckOutItem checkOutItem);
    void outputFinalCheckOutTotals(int numberOfItems, int total);
    void startingCheckout();

    void unknownCommand();
}
