package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;

import java.util.Arrays;
import java.util.List;

public class AddNewProductCommand implements CheckOutApplicationCommand {

    private StockItem stockItem;
    private UnitPrice price;
    private CheckoutCommandReceiver checkoutCommandReceiver;

    public AddNewProductCommand(StockItem stockItem, UnitPrice price, CheckoutCommandReceiver checkoutCommandReceiver) {
        this.stockItem = stockItem;
        this.price = price;
        this.checkoutCommandReceiver = checkoutCommandReceiver;
    }

    @Override
    public void executeCommand(CheckOutSession checkOutSession) {
        checkoutCommandReceiver.insertNewProduct(stockItem,price,checkOutSession);
    }

    @Override
    public boolean isTerminating() {
        return false;
    }


    @Override
    public List<SessionState> validForStates() {
        return Arrays.asList(SessionState.UPDATING);
    }
}
