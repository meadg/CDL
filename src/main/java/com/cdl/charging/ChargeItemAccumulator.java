package com.cdl.charging;

import com.cdl.domain.CheckOutItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.charge.ChargeItem;
import com.cdl.logging.ScanLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class ChargeItemAccumulator {

    private List<CheckOutItem> checkOutItems = new ArrayList<>();

    public void addChargeItem(ChargeItem chargeItem) {

        int newSubTotal = chargeItem.addPriceToValue(retrieveLastSubTotalValue());

        checkOutItems.add(new CheckOutItem(chargeItem, newSubTotal));
    }


    public List<CheckOutItem> getCheckOutItems() {
        return this.checkOutItems;
    }

    public boolean hasNumberOfNonDiscountedItems(StockItem stockItem, Integer numberRequired) {

        long available = checkOutItems.stream().filter(item -> item.matchesStockItem(stockItem) && item.isDiscountAble()).count();
        return available >= numberRequired;
    }

    public void flagItemsAsDiscounted(StockItem stockItem, Integer numberOfItems) {
        checkOutItems.stream().filter(item -> item.matchesStockItem(stockItem) && item.isDiscountAble()).limit(numberOfItems).forEach(match -> match.flagAsDiscounted());
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
        scanLogger.outputFinalCheckOutTotals(numberOfItems, finalSubTotal);
    }

    public void clearAccumulator() {
        checkOutItems = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargeItemAccumulator that = (ChargeItemAccumulator) o;
        return Objects.equals(checkOutItems, that.checkOutItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkOutItems);
    }


    private int retrieveLastSubTotalValue() {
        return checkOutItems.isEmpty() ? 0 : checkOutItems.get(checkOutItems.size() - 1).getSubTotal();
    }

}
