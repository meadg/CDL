package com.cdl.domain.price;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class UnitPriceTest {

    private static Price PRICE11 = new Price(11);
    private static Price PRICE44 = new Price(44);
    private static Price SECOND_PRICE11 = new Price(11);

    private static UnitPrice UNITPRICE_11  = new UnitPrice(PRICE11);
    private static UnitPrice UNITPRICE_44  = new UnitPrice(PRICE44);
    private static UnitPrice SECOND_UNITPRICE_11  = new UnitPrice(PRICE11);

    @Test
    public void creationAndEqualityTest(){

        assertThat(UNITPRICE_11,is(UNITPRICE_11));
        assertThat(UNITPRICE_11,is(SECOND_UNITPRICE_11));
        assertThat(UNITPRICE_11,is(not(UNITPRICE_44)));

    }

}