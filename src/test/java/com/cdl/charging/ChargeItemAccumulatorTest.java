package com.cdl.charging;

import com.cdl.domain.ChargeItem;
import com.cdl.domain.StockItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChargeItemAccumulatorTest {

    @Mock
    private StockItem stockItem1;

    @Mock
    private ChargeItem chargeItem1, chargeItem2,chargeItem3,chargeItem4;

    private ChargeItemAccumulator chargeItemAccumulator;

    @Before
    public void setUp() {
        chargeItemAccumulator = new ChargeItemAccumulator();
    }

    @Test
    public void newChargeInformationHasEmptyListOfChargeItems() {
        assertThat(chargeItemAccumulator.getChargeItems(), emptyCollectionOf(ChargeItem.class));
    }


    @Test
    public void addChargeItemAddsCorrectItem() {
        chargeItemAccumulator.addChargeItem(chargeItem1);
        assertThat(chargeItemAccumulator.getChargeItems(), hasSize(1));
        assertThat(chargeItemAccumulator.getChargeItems(), contains(chargeItem1));
    }

    @Test
    public void addMultipleChargeItemAddsCorrectItems() {
        addChargeItemsToAccumulator(chargeItem1,chargeItem2);

        assertThat(chargeItemAccumulator.getChargeItems(), hasSize(2));
        assertThat(chargeItemAccumulator.getChargeItems(), contains(chargeItem1, chargeItem2));
    }

    @Test
    public void hasNumberOfNonDiscountedItemsReturnsTrueIfNumberOfNonDiscountedEqual(){

        setAsDiscountable(chargeItem1,chargeItem2,chargeItem3);
        setAsMatchingStockItem(stockItem1,chargeItem1,chargeItem2,chargeItem3);
        addChargeItemsToAccumulator(chargeItem1,chargeItem2,chargeItem3);

        assertThat(chargeItemAccumulator.hasNumberOfNonDiscountedItems(stockItem1, 3),is(true));
    }
    @Test
    public void hasNumberOfNonDiscountedItemsReturnsFalseIfNumberOfNonDiscountedTooLow(){

        setAsDiscountable(chargeItem1,chargeItem2);
        setAsMatchingStockItem(stockItem1,chargeItem1,chargeItem2,chargeItem3);
        addChargeItemsToAccumulator(chargeItem1,chargeItem2,chargeItem3);

        assertThat(chargeItemAccumulator.hasNumberOfNonDiscountedItems(stockItem1, 3),is(false));
    }
    @Test
    public void hasNumberOfNonDiscountedItemsReturnsFalseIfNumberOfMatchingTooLow(){

        setAsDiscountable(chargeItem1,chargeItem2,chargeItem3);
        setAsMatchingStockItem(stockItem1,chargeItem1,chargeItem2);
        addChargeItemsToAccumulator(chargeItem1,chargeItem2,chargeItem3);

        assertThat(chargeItemAccumulator.hasNumberOfNonDiscountedItems(stockItem1, 3),is(false));
    }
    @Test
    public void hasNumberOfNonDiscountedItemsReturnsFalseIfNoEntries(){

        setAsDiscountable(chargeItem1,chargeItem2,chargeItem3);
        setAsMatchingStockItem(stockItem1,chargeItem1,chargeItem2);

        assertThat(chargeItemAccumulator.hasNumberOfNonDiscountedItems(stockItem1, 3),is(false));
    }

    @Test
    public void correctlyFlagsItemsIfMatchesStockItemAndDiscountable(){

        setAsDiscountable(chargeItem2,chargeItem3,chargeItem4);
        setAsMatchingStockItem(stockItem1,chargeItem2,chargeItem3,chargeItem4);
        addChargeItemsToAccumulator(chargeItem1,chargeItem2,chargeItem3,chargeItem4);

        chargeItemAccumulator.flagItemsAsDiscounted(stockItem1,3);

        verify(chargeItem1,never()).flagAsDiscounted();
        verify(chargeItem2).flagAsDiscounted();
        verify(chargeItem3).flagAsDiscounted();
        verify(chargeItem4).flagAsDiscounted();

    }


    @Test
    public void correctlyFlagsItemsIfMatchesStockItemAndDiscountableAndMoreThanNeeded(){

        setAsDiscountable(chargeItem1,chargeItem2,chargeItem3,chargeItem4);
        setAsMatchingStockItem(stockItem1,chargeItem1,chargeItem2,chargeItem3,chargeItem4);
        addChargeItemsToAccumulator(chargeItem1,chargeItem2,chargeItem3,chargeItem4);


        chargeItemAccumulator.flagItemsAsDiscounted(stockItem1,2);

        verify(chargeItem1).flagAsDiscounted();
        verify(chargeItem2).flagAsDiscounted();
        verify(chargeItem3,never()).flagAsDiscounted();
        verify(chargeItem4,never()).flagAsDiscounted();

    }


    private void addChargeItemsToAccumulator(ChargeItem... chargeItems){
        for (ChargeItem chargeItem: chargeItems
        ) {
            chargeItemAccumulator.addChargeItem(chargeItem);

        }

    }
    private void setAsMatchingStockItem(StockItem stockItem1, ChargeItem... chargeItems) {
        for (ChargeItem chargeItem: chargeItems
             ) {
            when(chargeItem.matchesStockItem(stockItem1)).thenReturn(true);

        }
    }

    private void setAsDiscountable(ChargeItem... chargeItems){
        for (ChargeItem chargeItem: chargeItems
             ) {
            when(chargeItem.isDiscountAble()).thenReturn(true);

        }
    }


}