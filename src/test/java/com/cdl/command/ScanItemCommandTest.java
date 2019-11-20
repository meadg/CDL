package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.command.scanning.ScanItemCommand;
import com.cdl.domain.StockItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ScanItemCommandTest {

    @Mock
    private CheckoutCommandReceiver checkoutCommandReceiver;
    @Mock
    private StockItem stockItem;
    @Mock
    private CheckOutSession checkoutSession;

    private ScanItemCommand scanItemCommand;

    @Before
    public void setUp() {
        scanItemCommand = new ScanItemCommand(checkoutCommandReceiver,stockItem);
    }

    @Test
    public void executeCommandCallsCorrectMethodOnCommandReceiver(){
        scanItemCommand.executeCommand(checkoutSession);
        verify(checkoutCommandReceiver).createChargeItemsForScannedItem(stockItem,checkoutSession);
    }

}