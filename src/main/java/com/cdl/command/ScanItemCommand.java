package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.domain.StockItem;

public class ScanItemCommand implements CheckOutApplicationCommand {

    private CheckoutCommandReceiver checkoutCommandReceiver;
    private StockItem stockItem;

    public ScanItemCommand(CheckoutCommandReceiver checkoutCommandReceiver, StockItem stockItem) {
        this.checkoutCommandReceiver = checkoutCommandReceiver;
        this.stockItem = stockItem;
    }

    @Override
    public void executeCommand(CheckOutSession checkOutSession) {
        checkoutCommandReceiver.createChargeItemsForScannedItem(stockItem,checkOutSession);

    }
}
