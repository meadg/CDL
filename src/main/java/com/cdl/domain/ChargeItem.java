package com.cdl.domain;

import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class ChargeItem {

    //make this implements checkout item and specify a type, charge, discount, information
    private StockItem stockItem;
    private String chargeDescription;
    private Price chargeValue;
    private boolean discountApplied = false;
    private boolean processed = false;

    public ChargeItem(StockItem stockItem, String chargeDescription, Price chargeValue) {

        this.stockItem = stockItem;
        this.chargeDescription = chargeDescription;
        this.chargeValue = chargeValue;
    }

    public boolean isProcessed(){
        return processed;
    }

    public abstract boolean isChargeItem();

}
