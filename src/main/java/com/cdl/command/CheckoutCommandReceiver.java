package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;
import com.cdl.domain.StockItem;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        stockItemChargingHandler.applyStockItemPricingRules(stockItem,checkOutSession);
        checkOutSession.createScannedChargeItemOutput();
    }
}
