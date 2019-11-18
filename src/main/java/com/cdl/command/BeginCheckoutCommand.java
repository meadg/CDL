package com.cdl.command;

import com.cdl.application.CheckOutSession;

public class BeginCheckoutCommand implements CheckOutApplicationCommand {
    //Could construct this here or get from factory or singleton??
    private CheckoutCommandReceiver checkoutCommandReceiver;

    public BeginCheckoutCommand(CheckoutCommandReceiver checkoutCommandReceiver) {
        this.checkoutCommandReceiver = checkoutCommandReceiver;
    }

    @Override
    public void executeCommand(CheckOutSession checkoutSession) {
        checkoutCommandReceiver.beginCheckoutSession(checkoutSession);

    }
}
