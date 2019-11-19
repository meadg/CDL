package com.cdl.charging;

import com.cdl.domain.ChargeItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CheckOutItem {

    private ChargeItem chargeItem;
    private int subTotal;
    private boolean discounted = false;
    private boolean processed = false;

    public CheckOutItem(ChargeItem chargeItem, int subTotal) {
        this.chargeItem = chargeItem;
        this.subTotal = subTotal;
    }

    //re
    public StockItem getStockItem() {
        return chargeItem.getStockItem();
    }

    public String getChargeDescription() {
        return chargeItem.getChargeDescription();
    }

    public Price getChargeValue() {
        return chargeItem.getChargeValue();
    }


    public int getSubTotal() {
        return subTotal;
    }

    public boolean matchesStockItem(StockItem stockItem) {
        return chargeItem.matchesStockItem(stockItem);
    }

    public boolean isDiscountAble() {
        return chargeItem.isDiscountAble() && discounted == false;
    }

    public void flagAsDiscounted() {
        this.discounted = true;
    }

    public boolean isChargeItem(){
        return chargeItem.isChargeItem();
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

    //testthis
    public boolean isUnprocessed() {
        return !processed;
    }

    public void flagAsProcessed() {
        this.processed = true;
    }

    public String getProductCode() {
        return chargeItem.getStockItem().getStockItemId();
    }
}
