package com.cdl.command;

import com.cdl.application.CheckOutSession;

public class UnknownApplicationCommand implements CheckOutApplicationCommand {

    @Override
    public void executeCommand(CheckOutSession checkOutSession) {
        checkOutSession.registerUnknownCommand();
    }

    @Override
    public boolean isTerminating() {
        return false;
    }
}
