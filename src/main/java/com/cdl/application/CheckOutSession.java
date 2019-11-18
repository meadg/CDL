package com.cdl.application;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.command.CheckOutApplicationCommand;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CheckOutSession {

    private ChargeItemAccumulator chargeItemAccumulator = new ChargeItemAccumulator();
    private SessionState currentState;


    public void handleApplicationCommand(CheckOutApplicationCommand command) {
        //validate state here?
        command.executeCommand(this);
    }

//    public void updateReceipt(ReceiptPrinter receiptPrinter){
//        chargeItemAccumulator.outputNewCheckoutItems(receiptPrinter);
//    }

    public ChargeItemAccumulator getItemAccumulator() {
        return chargeItemAccumulator;
    }

    public void setState(SessionState theState) {
        this.currentState = theState;
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
        return ToStringBuilder.reflectionToString(this);
    }

}
