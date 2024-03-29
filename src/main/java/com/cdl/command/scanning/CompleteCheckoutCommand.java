package com.cdl.command.scanning;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;
import com.cdl.command.CheckOutApplicationCommand;
import com.cdl.command.CheckoutCommandReceiver;

import java.util.Arrays;
import java.util.List;

public class CompleteCheckoutCommand implements CheckOutApplicationCommand {

    private CheckoutCommandReceiver checkoutCommandReceiver;

    public CompleteCheckoutCommand(CheckoutCommandReceiver checkoutCommandReceiver) {
        this.checkoutCommandReceiver = checkoutCommandReceiver;
    }

    @Override
    public void executeCommand(CheckOutSession session) {
        checkoutCommandReceiver.completeCheckoutSession(session);

    }

    @Override
    public boolean isTerminating() {
        return false;
    }


    @Override
    public List<SessionState> validForStates() {
        return Arrays.asList(SessionState.SCANNING, SessionState.NEW);
    }
}
