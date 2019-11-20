package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;

import java.util.Arrays;
import java.util.List;

public class BeginProductInsertionCommand implements CheckOutApplicationCommand {
    private CheckoutCommandReceiver checkoutCommandReceiver;

    public BeginProductInsertionCommand(CheckoutCommandReceiver checkoutCommandReceiver) {
        this.checkoutCommandReceiver = checkoutCommandReceiver;
    }

    @Override
    public void executeCommand(CheckOutSession checkoutSession) {
        checkoutCommandReceiver.beginProductInsertion(checkoutSession);

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
