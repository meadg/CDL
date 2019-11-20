package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.application.SessionState;

import java.util.List;

public interface CheckOutApplicationCommand {

    void executeCommand(CheckOutSession checkOutSession);

    boolean isTerminating();

    List<SessionState> validForStates();

}
