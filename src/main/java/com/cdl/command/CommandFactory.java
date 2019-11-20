package com.cdl.command;

import com.cdl.domain.StockItem;

public class CommandFactory {

    private CheckoutCommandReceiver commandReceiver;

    public CommandFactory(CheckoutCommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public CheckOutApplicationCommand createCommand(String command) {

        if (command.startsWith("BeginCheckOut")) {
            return new BeginCheckoutCommand(commandReceiver);
        }

        if (command.startsWith("FinishCheckOut")) {
            return new CompleteCheckoutCommand(commandReceiver);
        }

        if (command.startsWith("ExitApp")) {
            return new ExitApplicationCommand();
        }

        if (command.startsWith("ScanItem:")) {
            return createScanItemCommand(command);
        }
        if (command.equals("InvalidState")) {
            return new InvalidStateCommand();
        }

        return new UnknownApplicationCommand();
    }

    private CheckOutApplicationCommand createScanItemCommand(String command) {
        int colonPosition = command.indexOf(":");
        String productCode = command.substring(colonPosition + 1).trim();
        return new ScanItemCommand(commandReceiver, new StockItem(productCode));
    }

}
