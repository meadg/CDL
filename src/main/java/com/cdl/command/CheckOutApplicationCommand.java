package com.cdl.command;

import com.cdl.application.CheckOutSession;

public interface CheckOutApplicationCommand {

    void executeCommand(CheckOutSession checkOutSession);

}
