package com.cdl.domain;

import com.cdl.domain.StockItem;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class StockItemTest {

    private static final String STOCK_ITEM_ID_1 = "ABC";
    private static final String STOCK_ITEM_ID_2 = "CBA";

    @Test
    public void creationAndEqualityTest(){
        StockItem stockItem1 = new StockItem(STOCK_ITEM_ID_1);
        StockItem stockItem2 = new StockItem(STOCK_ITEM_ID_2);
        StockItem stockItem3 = new StockItem(STOCK_ITEM_ID_1);

        assertThat(stockItem1,is(stockItem1));
        assertThat(stockItem1,is(stockItem3));
        assertThat(stockItem1,is(not(stockItem2)));
    }

}