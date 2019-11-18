package com.cdl.command;

import com.cdl.application.CheckOutSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BeginCheckoutCommandTest {

    @Mock
    private  CheckoutCommandReceiver checkoutCommandReceiver;
    @Mock
    private CheckOutSession checkoutSession;

    private BeginCheckoutCommand beginCheckoutCommand;

    @Before
    public void setUp(){
        beginCheckoutCommand = new BeginCheckoutCommand(checkoutCommandReceiver);
    }

    @Test
    public void commandExecutesAgainstReceiver(){
        beginCheckoutCommand.executeCommand(checkoutSession);
        verify(checkoutCommandReceiver).beginCheckoutSession(checkoutSession);
    }

}