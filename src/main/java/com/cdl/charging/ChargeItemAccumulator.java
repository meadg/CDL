package com.cdl.charging;

import com.cdl.domain.ChargeItem;
import com.cdl.domain.StockItem;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ChargeItemAccumulator {
    // may be list of objects holding charge item and subtotal
    private List<ChargeItem> chargeItems = new ArrayList<>();

    //this would take a checkout item which could be a charge discount or information
    public void addChargeItem(ChargeItem chargeItem) {
        chargeItems.add(chargeItem);
    }

    public List<ChargeItem> getChargeItems() {
        return this.chargeItems;
    }

    public boolean hasNumberOfNonDiscountedItems(StockItem stockItem, Integer numberRequired) {

        long available = chargeItems.stream().filter(item -> item.matchesStockItem(stockItem) && item.isDiscountAble()).count();
        return available >= numberRequired;
    }

    public void flagItemsAsDiscounted(StockItem stockItem, Integer numberOfItems) {
        chargeItems.stream().filter(item -> item.matchesStockItem(stockItem) && item.isDiscountAble()).limit(numberOfItems).forEach(match-> match.flagAsDiscounted());
    }

}
