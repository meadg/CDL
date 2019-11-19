package com.cdl.charging;

import com.cdl.domain.ChargeItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;

import java.util.Objects;

public class CheckOutItem {

    private ChargeItem chargeItem;
    private int subTotal;
    private boolean discounted = false;
    private boolean processed = false;

    public CheckOutItem(ChargeItem chargeItem, int subTotal) {
        this.chargeItem = chargeItem;
        this.subTotal = subTotal;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckOutItem that = (CheckOutItem) o;
        return subTotal == that.subTotal &&
                discounted == that.discounted &&
                processed == that.processed &&
                Objects.equals(chargeItem, that.chargeItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chargeItem, subTotal, discounted, processed);
    }

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
