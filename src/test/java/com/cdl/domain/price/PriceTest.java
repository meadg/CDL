package com.cdl.domain.price;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class PriceTest {

    private static final Price PRICE_ZERO = new Price(0);
    private static Price PRICE11 = new Price(11);
    private static Price PRICE33 = new Price(33);
    private static Price PRICE44 = new Price(44);
    private static Price MINUS_PRICE44 = new Price(-44);
    private static Price PRICE88 = new Price(88);
    private static Price SECOND_PRICE11 = new Price(11);

    @Test
    public void creationAndEqualityTest() {
        assertThat(PRICE11, is(PRICE11));
        assertThat(PRICE11, is(SECOND_PRICE11));
        assertThat(PRICE11, is(not(PRICE44)));
    }

    @Test
    public void minusOperatesCorrectly(){
        assertThat(PRICE44.minus(PRICE11),is(PRICE33));
    }

    @Test
    public void lessThanOperatesCorrectly(){
        assertThat(PRICE44.lessThan(PRICE11),is(false));
        assertThat(PRICE44.lessThan(PRICE44),is(false));
        assertThat(PRICE11.lessThan(PRICE44),is(true));
    }
    @Test
    public void multiplyOperatesCorrectly(){
        assertThat(PRICE44.multiply(2),is(PRICE88));
        assertThat(PRICE44.multiply(-1),is(MINUS_PRICE44));
        assertThat(PRICE11.multiply(0),is(PRICE_ZERO));
    }
}