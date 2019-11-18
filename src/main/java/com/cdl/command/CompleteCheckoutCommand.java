package com.cdl.command;

import com.cdl.application.CheckOutSession;

public class CompleteCheckoutCommand implements CheckOutApplicationCommand {
    //Could construct this here or get from factory or singleton??
    private CheckoutCommandReceiver checkoutCommandReceiver;

    public CompleteCheckoutCommand(CheckoutCommandReceiver checkoutCommandReceiver) {
        this.checkoutCommandReceiver = checkoutCommandReceiver;
    }

    @Override
    public void executeCommand(CheckOutSession session) {
        checkoutCommandReceiver.completeCheckoutSession(session);

    }
}
