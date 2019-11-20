package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;
import com.cdl.command.scanning.StockItemChargingHandler;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;

public class CheckoutCommandReceiver {

    private StockItemChargingHandler stockItemChargingHandler;

    public CheckoutCommandReceiver(StockItemChargingHandler stockItemChargingHandler) {

        this.stockItemChargingHandler = stockItemChargingHandler;
    }

    public void beginCheckoutSession(CheckOutSession checkoutSession) {

        checkoutSession.setState(SessionState.NEW);
        checkoutSession.beginCheckOutSession();
    }

    public void completeCheckoutSession(CheckOutSession checkoutSession) {
        checkoutSession.setState(SessionState.COMPLETE);
        checkoutSession.completeCheckoutAndPrintTotals();
    }

    public void createChargeItemsForScannedItem(StockItem stockItem, CheckOutSession checkOutSession) {
        checkOutSession.setState(SessionState.SCANNING);
        if (stockItemChargingHandler.isValidProduct(stockItem)) {
            stockItemChargingHandler.applyStockItemPricingRules(stockItem, checkOutSession);
            checkOutSession.createScannedChargeItemOutput();

        } else {
            checkOutSession.registerInvalidProduct(stockItem);
        }
    }

    public void insertNewProduct(StockItem stockItem, UnitPrice price,CheckOutSession checkoutSession) {
        stockItemChargingHandler.addNewProduct(stockItem,price);
        checkoutSession.registerNewProduct(stockItem,price);
    }

    public void beginProductInsertion(CheckOutSession checkoutSession) {
        checkoutSession.setState(SessionState.UPDATING);
        checkoutSession.startProductInsertion();
    }

    public void endProductInsertion(CheckOutSession checkoutSession) {
        checkoutSession.setState(SessionState.AVAILABLE);
        checkoutSession.endProductUpdate();
    }
}
