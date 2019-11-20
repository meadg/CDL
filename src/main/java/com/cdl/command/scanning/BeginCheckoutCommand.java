package com.cdl.command.scanning;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;
import com.cdl.command.CheckOutApplicationCommand;
import com.cdl.command.CheckoutCommandReceiver;

import java.util.Arrays;
import java.util.List;

public class BeginCheckoutCommand implements CheckOutApplicationCommand {
    private CheckoutCommandReceiver checkoutCommandReceiver;

    public BeginCheckoutCommand(CheckoutCommandReceiver checkoutCommandReceiver) {
        this.checkoutCommandReceiver = checkoutCommandReceiver;
    }

    @Override
    public void executeCommand(CheckOutSession checkoutSession) {
        checkoutCommandReceiver.beginCheckoutSession(checkoutSession);

    }

    @Override
    public boolean isTerminating() {
        return false;
    }

    @Override
    public List<SessionState> validForStates() {
        return Arrays.asList(SessionState.AVAILABLE);
    }
}
