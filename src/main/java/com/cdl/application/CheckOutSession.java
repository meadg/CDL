package com.cdl.application;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.command.CheckOutApplicationCommand;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CheckOutSession {

    private SessionState currentState;
    private ChargeItemAccumulator chargeItemAccumulator;
    private ScanLogger scanLogger;

    public CheckOutSession(ChargeItemAccumulator chargeItemAccumulator,ScanLogger scanLogger) {
        this.chargeItemAccumulator = chargeItemAccumulator;
        this.scanLogger = scanLogger;
    }

    public void handleApplicationCommand(CheckOutApplicationCommand command) {
        //validate state here?
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
    }

    public void createScannedChargeItemOutput() {
        chargeItemAccumulator.addNewItemsToReceiptOutputAndFlagAsProcessed(scanLogger);

    }
    //testthis
    public void beginCheckOutSession() {
        scanLogger.startingCheckout();
    }


    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
