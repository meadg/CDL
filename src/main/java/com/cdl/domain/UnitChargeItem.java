package com.cdl.domain;

import com.cdl.domain.price.Price;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UnitChargeItem extends ChargeItem {

    public UnitChargeItem(StockItem stockItem, String chargeDescription, Price chargeValue) {
        super(stockItem, chargeDescription, chargeValue);
    }

    @Override
    public boolean isChargeItem() {
        return true;
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
