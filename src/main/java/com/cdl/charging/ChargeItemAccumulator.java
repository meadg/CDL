package com.cdl.charging;

import com.cdl.application.ScanLogger;
import com.cdl.domain.ChargeItem;
import com.cdl.domain.StockItem;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ChargeItemAccumulator {

    private List<CheckOutItem> checkOutItems = new ArrayList<>();

    public void addChargeItem(ChargeItem chargeItem) {

        int newSubTotal = chargeItem.addPriceToValue(retrieveLastSubTotalValue());

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


    public void addNewItemsToReceiptOutputAndFlagAsProcessed(ScanLogger scanLogger) {

        final List<CheckOutItem> itemsToOutput = checkOutItems.stream().filter(item -> item.isUnprocessed()).map(match -> {
            match.flagAsProcessed();
            return match;
        }).collect(toList());

        itemsToOutput.stream().forEach(item -> scanLogger.outputItemAndSubtotal(item));

    }

    public void produceFinalCheckOutTotal(ScanLogger scanLogger) {
        int finalSubTotal = retrieveLastSubTotalValue();
        int numberOfItems = checkOutItems.stream().filter(item -> item.isChargeItem()).collect(toList()).size();
        scanLogger.outputFinalCheckOutTotals(numberOfItems,finalSubTotal);
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


    private int retrieveLastSubTotalValue() {
        return checkOutItems.isEmpty()? 0 : checkOutItems.get(checkOutItems.size()-1).getSubTotal();
    }

}
