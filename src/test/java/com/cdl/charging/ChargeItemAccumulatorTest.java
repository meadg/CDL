package com.cdl.charging;

import com.cdl.domain.CheckOutItem;
import com.cdl.logging.ScanLogger;
import com.cdl.domain.charge.ChargeItem;
import com.cdl.domain.charge.DiscountChargeItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.charge.UnitChargeItem;
import com.cdl.domain.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChargeItemAccumulatorTest {

    @Mock
    private StockItem stockItem1;
    @Mock
    private ChargeItem chargeItem1, chargeItem2,chargeItem3,chargeItem4;
    @Mock
    private ScanLogger scanLogger;

    private ChargeItemAccumulator chargeItemAccumulator;

    @Before
    public void setUp() {
        chargeItemAccumulator = new ChargeItemAccumulator();
    }

    @Test
    public void newChargeInformationHasEmptyListOfChargeItems() {
        assertThat(chargeItemAccumulator.getCheckOutItems(), emptyCollectionOf(CheckOutItem.class));
    }


    @Test
    public void addChargeItemAddsCorrectItem() {
        chargeItemAccumulator.addChargeItem(chargeItem1);
        assertThat(chargeItemAccumulator.getCheckOutItems(), hasSize(1));
        assertThat(chargeItemAccumulator.getCheckOutItems(), contains(new CheckOutItem(chargeItem1,0)));
    }

    @Test
    public void addMultipleChargeItemAddsCorrectItems() {
        addChargeItemsToAccumulator(chargeItem1,chargeItem2);

        assertThat(chargeItemAccumulator.getCheckOutItems(), hasSize(2));
        assertThat(chargeItemAccumulator.getCheckOutItems(), contains(new CheckOutItem(chargeItem1,0), new CheckOutItem(chargeItem2,0)));
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

        final List<CheckOutItem> checkOutItems = chargeItemAccumulator.getCheckOutItems();

        CheckOutItem expectedItem1 = new CheckOutItem(chargeItem1,0);
        CheckOutItem expectedItem2 = new CheckOutItem(chargeItem2,0);
        expectedItem2.flagAsDiscounted();
        CheckOutItem expectedItem3 = new CheckOutItem(chargeItem3,0);
        expectedItem3.flagAsDiscounted();
        CheckOutItem expectedItem4 = new CheckOutItem(chargeItem4,0);
        expectedItem4.flagAsDiscounted();

        assertThat(checkOutItems, contains(expectedItem1,expectedItem2,expectedItem3,expectedItem4));
    }


    @Test
    public void correctlyFlagsItemsIfMatchesStockItemAndDiscountableAndMoreThanNeeded(){

        setAsDiscountable(chargeItem1,chargeItem2,chargeItem3,chargeItem4);
        setAsMatchingStockItem(stockItem1,chargeItem1,chargeItem2,chargeItem3,chargeItem4);
        addChargeItemsToAccumulator(chargeItem1,chargeItem2,chargeItem3,chargeItem4);


        chargeItemAccumulator.flagItemsAsDiscounted(stockItem1,2);

        CheckOutItem expectedItem1 = new CheckOutItem(chargeItem1,0);
        expectedItem1.flagAsDiscounted();
        CheckOutItem expectedItem2 = new CheckOutItem(chargeItem2,0);
        expectedItem2.flagAsDiscounted();
        CheckOutItem expectedItem3 = new CheckOutItem(chargeItem3,0);
        CheckOutItem expectedItem4 = new CheckOutItem(chargeItem4,0);

    }

    @Test
    public void logsSingleUnprocessedItem(){
        StockItem stockItem = new StockItem("ABC");
        final Price price = new Price(12);
        ChargeItem chargeItem = new UnitChargeItem(stockItem,"Unit Price", price);

        addChargeItemsToAccumulator(chargeItem);

        chargeItemAccumulator.addNewItemsToReceiptOutputAndFlagAsProcessed(scanLogger);

        final CheckOutItem expectedCheckoutItem = new CheckOutItem(chargeItem, 12);
        expectedCheckoutItem.flagAsProcessed();
        verify(scanLogger).outputItemAndSubtotal(expectedCheckoutItem);

    }
    @Test
    public void logMultipleUnprocessedItem(){
        StockItem stockItem1 = new StockItem("ABC");
        final Price price1 = new Price(12);
        StockItem stockItem2 = new StockItem("CDE");
        final Price price2 = new Price(-3);


        ChargeItem chargeItem1 = new UnitChargeItem(stockItem1,"Unit Price", price1);
        ChargeItem chargeItem2 = new DiscountChargeItem(stockItem2,"Multibuy discount", price2);

        addChargeItemsToAccumulator(chargeItem1,chargeItem2);

        chargeItemAccumulator.addNewItemsToReceiptOutputAndFlagAsProcessed(scanLogger);

        final CheckOutItem expectedCheckoutItem1 = new CheckOutItem(chargeItem1, 12);
        expectedCheckoutItem1.flagAsProcessed();
        final CheckOutItem expectedCheckoutItem2 = new CheckOutItem(chargeItem2, 9);
        expectedCheckoutItem2.flagAsProcessed();

        verify(scanLogger).outputItemAndSubtotal(expectedCheckoutItem1);
        verify(scanLogger).outputItemAndSubtotal(expectedCheckoutItem2);

    }

    @Test
    public void logCheckOutSessionCompletion(){
        StockItem stockItem1 = new StockItem("ABC");
        final Price price1 = new Price(12);
        StockItem stockItem2 = new StockItem("ABC");
        final Price price2 = new Price(-3);

        ChargeItem chargeItem1 = new UnitChargeItem(stockItem1,"Unit Price", price1);
        ChargeItem chargeItem2 = new DiscountChargeItem(stockItem2,"Multibuy discount", price2);

        addChargeItemsToAccumulator(chargeItem1,chargeItem2);

        chargeItemAccumulator.addNewItemsToReceiptOutputAndFlagAsProcessed(scanLogger);

        chargeItemAccumulator.produceFinalCheckOutTotal(scanLogger);
        verify(scanLogger).outputFinalCheckOutTotals(1,9);

    }
    @Test
    public void logCheckOutSessionCompletionMultipleItems(){
        StockItem stockItem1 = new StockItem("ABC");
        final Price price1 = new Price(12);
        StockItem stockItem2 = new StockItem("CBA");
        final Price price2 = new Price(-3);


        ChargeItem chargeItem1 = new UnitChargeItem(stockItem1,"Unit Price", price1);
        ChargeItem chargeItem2 = new DiscountChargeItem(stockItem2,"Multibuy discount", price2);

        addChargeItemsToAccumulator(chargeItem1,chargeItem1,chargeItem1,chargeItem2,chargeItem1);

        chargeItemAccumulator.addNewItemsToReceiptOutputAndFlagAsProcessed(scanLogger);

        chargeItemAccumulator.produceFinalCheckOutTotal(scanLogger);
        verify(scanLogger).outputFinalCheckOutTotals(4,45);

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