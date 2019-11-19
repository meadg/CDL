package com.cdl.integration;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.charging.CheckOutItem;
import com.cdl.domain.ChargeItem;
import com.cdl.domain.DiscountChargeItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.UnitChargeItem;
import com.cdl.domain.price.Price;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class ChargeItemAccumulatorIntegrationTest {

    public static final int NINE = 9;
    public static final int TWELVE = 12;
    public static final int THIRTY_THREE = 33;
    public static final int MINUS_3 = -3;
    public static final int TWENTY_FOUR = 24;

    public static final Price PRICE_12  = new Price(TWELVE);
    public static final Price PRICE_MINUS_3  = new Price(MINUS_3);
    public static final Price PRICE_24  = new Price(TWENTY_FOUR);

    private static StockItem stockItem = new StockItem("ABC");

    private ChargeItemAccumulator accumulator;

    @Before
    public void setUp() {
        accumulator = new ChargeItemAccumulator();
    }

    @Test
    public void subTotalForNewItem() {

        final ChargeItem chargeItem = new UnitChargeItem(stockItem, "Apples",PRICE_12);

        accumulator.addChargeItem(chargeItem);

        final List<CheckOutItem> checkOutItems = accumulator.getCheckOutItems();

        assertThat(checkOutItems,hasSize(1));
        assertThat(checkOutItems.get(0), is(new CheckOutItem(chargeItem,TWELVE)));

    }
    @Test
    public void subTotalForMultipleItems() {

        final ChargeItem chargeItem1 = new UnitChargeItem(stockItem, "Apples",PRICE_12);
        final ChargeItem chargeItem2 = new DiscountChargeItem(stockItem, "Apples",PRICE_MINUS_3);
        final ChargeItem chargeItem3 = new UnitChargeItem(stockItem, "Apples",PRICE_24);

        accumulator.addChargeItem(chargeItem1);
        accumulator.addChargeItem(chargeItem2);
        accumulator.addChargeItem(chargeItem3);


        final List<CheckOutItem> checkOutItems = accumulator.getCheckOutItems();

        assertThat(checkOutItems,hasSize(3));
        assertThat(checkOutItems, contains(new CheckOutItem(chargeItem1,TWELVE),new CheckOutItem(chargeItem2,NINE),new CheckOutItem(chargeItem3,THIRTY_THREE)));

    }
}
