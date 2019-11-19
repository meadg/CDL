package com.cdl.domain.price;

import java.util.Objects;

public class UnitPrice {


    private Price unitPrice;

    public UnitPrice(Price price) {

        unitPrice = price;
    }

    public Price getPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitPrice unitPrice1 = (UnitPrice) o;
        return Objects.equals(unitPrice, unitPrice1.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitPrice);
    }
}
