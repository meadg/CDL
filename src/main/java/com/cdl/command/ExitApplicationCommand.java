package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;

import java.util.Arrays;
import java.util.List;

public class ExitApplicationCommand implements CheckOutApplicationCommand {

    @Override
    public void executeCommand(CheckOutSession checkOutSession) {
        //donothing
    }

    @Override
    public boolean isTerminating() {
        return true;
    }


    @Override
    public List<SessionState> validForStates() {
        return Arrays.asList(SessionState.values());
    }
}
