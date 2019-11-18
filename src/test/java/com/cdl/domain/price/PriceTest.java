package com.cdl.domain.price;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class PriceTest {

    private static Price PRICE11 = new Price(11);
    private static Price PRICE44 = new Price(44);
    private static Price SECOND_PRICE11 = new Price(11);

    @Test
    public void creationAndEqualityTest() {
        assertThat(PRICE11, is(PRICE11));
        assertThat(PRICE11, is(SECOND_PRICE11));
        assertThat(PRICE11, is(not(PRICE44)));
    }
}