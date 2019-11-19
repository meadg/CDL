package com.cdl.domain;

import com.cdl.domain.price.Price;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class UnitChargeItemTest {

    private static Price PRICE11 = new Price(11);
    private static Price PRICE44 = new Price(44);
    private static Price SECOND_PRICE11 = new Price(11);

    private static String UNITCHARGE_DESCRIPTION = "Multibuy Description";

    private static StockItem STOCKITEM_ABC = new StockItem("ABC");
    private static StockItem STOCKITEM_CBA = new StockItem("CBA");

    private UnitChargeItem unitChargeItem1, unitChargeItem2, unitChargeItem3;

    @Before
    public void setUp() {
        unitChargeItem1 = new UnitChargeItem(STOCKITEM_ABC, UNITCHARGE_DESCRIPTION, PRICE11);
        unitChargeItem2 = new UnitChargeItem(STOCKITEM_CBA, UNITCHARGE_DESCRIPTION, PRICE44);
        unitChargeItem3 = new UnitChargeItem(STOCKITEM_ABC, UNITCHARGE_DESCRIPTION, PRICE11);

    }

    @Test
    public void creationAndEqualityTest() {
        assertThat(unitChargeItem1,is(unitChargeItem1));
        assertThat(unitChargeItem1,is(unitChargeItem3));
        assertThat(unitChargeItem1,is(not(unitChargeItem2)));

    }

    @Test
    public void isChargeItem() {
        assertThat(unitChargeItem1.isChargeItem(),is(true));
    }
    @Test
    public void isDiscountableIfnotAlreadyApplied() {

        assertThat(unitChargeItem1.isDiscountAble(),is(true));
    }

    @Test
    public void addChargeToItem_returnsCorrectValue(){

        assertThat(unitChargeItem1.addPriceToValue(12),is(23));
        assertThat(unitChargeItem1.addPriceToValue(-30),is(-19));

    }

}