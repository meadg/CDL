package com.cdl.domain.charge;

import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;

import java.util.Objects;

public abstract class ChargeItem {

    private StockItem stockItem;
    private String chargeDescription;
    private Price chargeValue;

    public ChargeItem(StockItem stockItem, String chargeDescription, Price chargeValue) {

        this.stockItem = stockItem;
        this.chargeDescription = chargeDescription;
        this.chargeValue = chargeValue;
    }


    public int addPriceToValue(int value) {
        return chargeValue.addTo(value);
    }

    public boolean matchesStockItem(StockItem theItem) {
        return this.stockItem.equals(theItem);
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public String getChargeDescription() {
        return chargeDescription;
    }

    public Price getChargeValue() {
        return chargeValue;
    }

    public abstract boolean isChargeItem();

    public abstract boolean isDiscountAble();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargeItem that = (ChargeItem) o;
        return Objects.equals(stockItem, that.stockItem) &&
                Objects.equals(chargeDescription, that.chargeDescription) &&
                Objects.equals(chargeValue, that.chargeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockItem, chargeDescription, chargeValue);
    }

}
