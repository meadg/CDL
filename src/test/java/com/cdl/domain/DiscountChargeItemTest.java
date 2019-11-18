package com.cdl.domain;

import com.cdl.domain.price.Price;
import com.cdl.exception.DiscountNotAllowedException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class DiscountChargeItemTest {

    private static Price PRICE11 = new Price(11);
    private static Price PRICE44 = new Price(44);
    private static Price SECOND_PRICE11 = new Price(11);

    private static String DISCOUNT_DESCRIPTION = "Multibuy Description";

    private static StockItem STOCKITEM_ABC = new StockItem("ABC");
    private static StockItem STOCKITEM_CBA = new StockItem("CBA");

    private DiscountChargeItem discountChargeItem1, discountChargeItem2, discountChargeItem3;

    @Before
    public void setUp() {
        discountChargeItem1 = new DiscountChargeItem(STOCKITEM_ABC, DISCOUNT_DESCRIPTION, PRICE11);
        discountChargeItem2 = new DiscountChargeItem(STOCKITEM_CBA, DISCOUNT_DESCRIPTION, PRICE44);
        discountChargeItem3 = new DiscountChargeItem(STOCKITEM_ABC, DISCOUNT_DESCRIPTION, PRICE11);

    }

    @Test
    public void creationAndEqualityTest() {
        assertThat(discountChargeItem1,is(discountChargeItem1));
        assertThat(discountChargeItem1,is(discountChargeItem3));
        assertThat(discountChargeItem1,is(not(discountChargeItem2)));

    }

    @Test
    public void isChargeItem() {
        assertThat(discountChargeItem1.isChargeItem(),is(false));

    }

    @Test
    public void isNotDiscountable() {
        assertThat(discountChargeItem1.isDiscountAble(),is(false));

    }

    @Test(expected = DiscountNotAllowedException.class)
    public void flaggingAsDiscountAppliedNotAllowed()
    {
        discountChargeItem1.flagAsDiscounted();

    }

}