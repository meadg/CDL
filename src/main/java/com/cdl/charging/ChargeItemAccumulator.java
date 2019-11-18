package com.cdl.charging;

import com.cdl.domain.ChargeItem;
import com.cdl.domain.StockItem;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class ChargeItemAccumulator {

    private List<CheckOutItem> checkOutItems = new ArrayList<>();

    public void addChargeItem(ChargeItem chargeItem) {

        int subTotal = checkOutItems.isEmpty()? 0 : checkOutItems.get(checkOutItems.size()-1).getSubTotal();
        int newSubTotal = chargeItem.addPriceToValue(subTotal);

        checkOutItems.add(new CheckOutItem(chargeItem,newSubTotal));
    }

    public List<CheckOutItem> getCheckOutItems() {
        return this.checkOutItems;
    }

    public boolean hasNumberOfNonDiscountedItems(StockItem stockItem, Integer numberRequired) {

        long available = checkOutItems.stream().filter(item -> item.matchesStockItem(stockItem) && item.isDiscountAble()).count();
        return available >= numberRequired;
    }

    public void flagItemsAsDiscounted(StockItem stockItem, Integer numberOfItems) {
        checkOutItems.stream().filter(item -> item.matchesStockItem(stockItem) && item.isDiscountAble()).limit(numberOfItems).forEach(match-> match.flagAsDiscounted());
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
