package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.command.scanning.CompleteCheckoutCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
@RunWith(MockitoJUnitRunner.class)
public class CompleteCheckoutCommandTest {

    @Mock
    private  CheckoutCommandReceiver checkoutCommandReceiver;
    @Mock
    private CheckOutSession checkoutSession;

    private CompleteCheckoutCommand completeCheckoutCommand;

    @Before
    public void setUp(){
        completeCheckoutCommand = new CompleteCheckoutCommand(checkoutCommandReceiver);
    }

    @Test
    public void commandExecutesAgainstReceiver(){
        completeCheckoutCommand.executeCommand(checkoutSession);
        verify(checkoutCommandReceiver).completeCheckoutSession(checkoutSession);
    }


}