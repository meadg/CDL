package com.cdl.logging;

import com.cdl.application.SessionState;
import com.cdl.domain.CheckOutItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;

public interface ScanLogger {

    void outputItemAndSubtotal(CheckOutItem checkOutItem);

    void outputFinalCheckOutTotals(int numberOfItems, int total);

    void startingCheckout();

    void unknownCommand();

    void invalidCommandForState(SessionState currentState);

    void registerInvalidProduct(StockItem stockItem);

    void beginningProductUpdate();

    void registerNewProduct(StockItem stockItem, UnitPrice price);

    void endProductUpdateSession();
}
