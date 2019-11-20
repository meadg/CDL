package com.cdl.command;

import com.cdl.application.CheckOutSession;

public class ExitApplicationCommand implements CheckOutApplicationCommand {

    @Override
    public void executeCommand(CheckOutSession checkOutSession) {
        //donothing
    }

    @Override
    public boolean isTerminating() {
        return true;
    }
}
