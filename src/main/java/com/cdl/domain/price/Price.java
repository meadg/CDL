package com.cdl.domain.price;

import java.util.Objects;

public class Price {

    private Integer price;

    public Price(Integer price) {
        this.price = price;
    }

    public Price multiply(Integer numberRequired) {
        return new Price(this.price * numberRequired);
    }

    public Price minus(Price number) {
        return new Price(this.price - number.price);
    }

    public boolean lessThan(Price totalCharge) {

        return this.price < totalCharge.price;
    }

    public int getValue() {

        return price;
    }

    public Integer addTo(int value){
        return value+this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(price, price1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

}
