package com.cdl.command.scanning;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;
import com.cdl.command.CheckOutApplicationCommand;
import com.cdl.command.CheckoutCommandReceiver;
import com.cdl.domain.StockItem;

import java.util.Arrays;
import java.util.List;

public class ScanItemCommand implements CheckOutApplicationCommand {

    private CheckoutCommandReceiver checkoutCommandReceiver;
    private StockItem stockItem;

    public ScanItemCommand(CheckoutCommandReceiver checkoutCommandReceiver, StockItem stockItem) {
        this.checkoutCommandReceiver = checkoutCommandReceiver;
        this.stockItem = stockItem;
    }

    @Override
    public void executeCommand(CheckOutSession checkOutSession) {
        checkoutCommandReceiver.createChargeItemsForScannedItem(stockItem, checkOutSession);

    }

    @Override
    public boolean isTerminating() {
        return false;
    }

    @Override
    public List<SessionState> validForStates() {
        return Arrays.asList(SessionState.NEW, SessionState.SCANNING);
    }


}
