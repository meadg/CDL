package com.cdl.domain;

public class StockItem {

    private String stockItemId;

    public StockItem(String stockItemId) {

        this.stockItemId = stockItemId;
    }

    public String getStockItemId() {
        return stockItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockItem stockItem = (StockItem) o;

        return stockItemId != null ? stockItemId.equals(stockItem.stockItemId) : stockItem.stockItemId == null;
    }

    @Override
    public int hashCode() {
        return stockItemId != null ? stockItemId.hashCode() : 0;
    }
}
