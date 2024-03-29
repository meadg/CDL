package com.cdl.application;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.command.CheckOutApplicationCommand;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;
import com.cdl.logging.ScanLogger;

public class CheckOutSession {

    private SessionState currentState;
    private ChargeItemAccumulator chargeItemAccumulator;
    private ScanLogger scanLogger;

    public CheckOutSession(ChargeItemAccumulator chargeItemAccumulator, ScanLogger scanLogger) {
        this.chargeItemAccumulator = chargeItemAccumulator;
        this.scanLogger = scanLogger;
    }

    public void handleApplicationCommand(CheckOutApplicationCommand command) {
        command.executeCommand(this);
    }

    public ChargeItemAccumulator getItemAccumulator() {
        return chargeItemAccumulator;
    }

    public void setState(SessionState theState) {
        this.currentState = theState;
    }


    public void completeCheckoutAndPrintTotals() {
        chargeItemAccumulator.produceFinalCheckOutTotal(scanLogger);
        chargeItemAccumulator.clearAccumulator();
        this.setState(SessionState.AVAILABLE);
    }

    public void createScannedChargeItemOutput() {
        chargeItemAccumulator.addNewItemsToReceiptOutputAndFlagAsProcessed(scanLogger);
    }

    public void beginCheckOutSession() {
        scanLogger.startingCheckout();
    }

    public void registerUnknownCommand() {
        scanLogger.unknownCommand();
    }

    public void registerInvalidCommandForState() {
        scanLogger.invalidCommandForState(this.currentState);
    }

    public void registerInvalidProduct(StockItem stockItem) {
        scanLogger.registerInvalidProduct(stockItem);
    }


    public void startProductInsertion() {
        scanLogger.beginningProductUpdate();
    }
    public void registerNewProduct(StockItem stockItem, UnitPrice price) {
        scanLogger.registerNewProduct(stockItem,price);
    }

    public void endProductUpdate() {
        scanLogger.endProductUpdateSession();
    }


    public SessionState currentState() {
        return this.currentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckOutSession that = (CheckOutSession) o;

        if (currentState != that.currentState) return false;
        if (chargeItemAccumulator != null ? !chargeItemAccumulator.equals(that.chargeItemAccumulator) : that.chargeItemAccumulator != null)
            return false;
        return scanLogger != null ? scanLogger.equals(that.scanLogger) : that.scanLogger == null;
    }

    @Override
    public int hashCode() {
        int result = currentState != null ? currentState.hashCode() : 0;
        result = 31 * result + (chargeItemAccumulator != null ? chargeItemAccumulator.hashCode() : 0);
        result = 31 * result + (scanLogger != null ? scanLogger.hashCode() : 0);
        return result;
    }

}
