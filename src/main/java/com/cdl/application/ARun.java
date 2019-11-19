package com.cdl.application;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.NumberFormat;
import java.util.Locale;

public class ARun {

    public static void main(String[] args) {
        NumberFormat GBP = NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println(GBP.format(-30/100.0));
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
