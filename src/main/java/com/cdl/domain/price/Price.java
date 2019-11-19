package com.cdl.domain.price;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
