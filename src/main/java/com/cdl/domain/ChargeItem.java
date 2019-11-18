package com.cdl.domain;

import com.cdl.domain.price.Price;

public abstract class ChargeItem {

    private StockItem stockItem;
    private String chargeDescription;
    private Price chargeValue;

    public ChargeItem(StockItem stockItem, String chargeDescription, Price chargeValue) {

        this.stockItem = stockItem;
        this.chargeDescription = chargeDescription;
        this.chargeValue = chargeValue;
    }


    public int addPriceToValue(int value){
        return chargeValue.addTo(value);
    }

    public  boolean matchesStockItem(StockItem theItem){
        return this.stockItem.equals(theItem);
    }

    public abstract boolean isChargeItem();
    public abstract boolean isDiscountAble();

}
