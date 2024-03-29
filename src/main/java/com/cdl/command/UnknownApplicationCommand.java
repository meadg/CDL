package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;

import java.util.Arrays;
import java.util.List;

public class UnknownApplicationCommand implements CheckOutApplicationCommand {

    @Override
    public void executeCommand(CheckOutSession checkOutSession) {
        checkOutSession.registerUnknownCommand();
    }

    @Override
    public boolean isTerminating() {
        return false;
    }


    @Override
    public List<SessionState> validForStates() {
        return Arrays.asList(SessionState.values());
    }
}
