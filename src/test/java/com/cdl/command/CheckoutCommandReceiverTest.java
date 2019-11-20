package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;
import com.cdl.domain.StockItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutCommandReceiverTest {
    @Mock
    private CheckOutSession checkoutSession;
    @Mock
    private StockItem stockItem;
    @Mock
    private StockItemChargingHandler stockItemChargingHandler;

    private CheckoutCommandReceiver checkoutCommandReceiver;

    @Before
    public void setUp(){
        checkoutCommandReceiver = new CheckoutCommandReceiver(stockItemChargingHandler);
    }

    @Test
    public void beginCheckoutUpdatesSessionStateCorrectly(){
        checkoutCommandReceiver.beginCheckoutSession(checkoutSession);
        verify(checkoutSession).setState(SessionState.NEW);
    }

    @Test
    public void completeCheckoutSessionUpdatesSessionStateCorrectly(){
        checkoutCommandReceiver.completeCheckoutSession(checkoutSession);
        verify(checkoutSession).setState(SessionState.COMPLETE);
    }
    @Test
    public void completeCheckoutSessionCallsBackOnSessionToOutputTotals(){
        checkoutCommandReceiver.completeCheckoutSession(checkoutSession);
        verify(checkoutSession).completeCheckoutAndPrintTotals();
    }

    @Test
    public void createChargeItemsForScannedItemInvokesCorrectMethodOnHandler(){
        when(stockItemChargingHandler.isValidProduct(stockItem)).thenReturn(true);
        checkoutCommandReceiver.createChargeItemsForScannedItem(stockItem,checkoutSession);
        verify(stockItemChargingHandler).applyStockItemPricingRules(stockItem,checkoutSession);
    }
    @Test
    public void createChargeItemsForScannedItemCallsBackOnSessionToPrintOutput(){
        when(stockItemChargingHandler.isValidProduct(stockItem)).thenReturn(true);
        checkoutCommandReceiver.createChargeItemsForScannedItem(stockItem,checkoutSession);
        verify(checkoutSession).createScannedChargeItemOutput();
    }


}