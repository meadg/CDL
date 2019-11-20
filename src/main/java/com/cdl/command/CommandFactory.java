package com.cdl.command;

import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;
import com.cdl.domain.price.UnitPrice;

import static java.lang.Integer.parseInt;

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
        if (command.equals("StartUpdate")) {
            return new BeginProductInsertionCommand(commandReceiver);
        }

        if (command.startsWith("InsertProduct:")) {
            return createInsertProductCommand(command);
        }

        if (command.equals("EndUpdate")) {
            return new EndProductInsertionCommand(commandReceiver);
        }
        if (command.equals("InvalidState")) {
            return new InvalidStateCommand();
        }

        return new UnknownApplicationCommand();
    }

    private CheckOutApplicationCommand createInsertProductCommand(String command) {

        final String prodId = "ProductId:";
        final int prodIdIndex = command.indexOf(prodId);
        final int startOfProductCode = prodIdIndex + prodId.length();
        final int endOfProductCode = command.indexOf(":", startOfProductCode);

        final String unitPriceId = "UnitPrice:";
        final int  unitPriceIdIndex = command.indexOf(unitPriceId);
        final int startOfUnitPrice = unitPriceIdIndex + unitPriceId.length();

        String productCode = command.substring(startOfProductCode,endOfProductCode).trim();
        Integer unitPrice = parseInt(command.substring(startOfUnitPrice).trim());
        return new AddNewProductCommand(new StockItem(productCode),new UnitPrice(new Price(unitPrice)),commandReceiver);
    }



    private CheckOutApplicationCommand createScanItemCommand(String command) {
        int colonPosition = command.indexOf(":");
        String productCode = command.substring(colonPosition + 1).trim();
        return new ScanItemCommand(commandReceiver, new StockItem(productCode));
    }

}
