package com.cdl.command;

import com.cdl.application.CheckOutSession;

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
}
