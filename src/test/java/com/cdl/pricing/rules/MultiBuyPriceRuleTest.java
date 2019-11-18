package com.cdl.pricing.rules;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.domain.ChargeItem;
import com.cdl.domain.DiscountChargeItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;
import com.cdl.domain.price.UnitPrice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MultiBuyPriceRuleTest {

    private static Integer FREQUENCY_0 =0;
    private static Integer FREQUENCY_2 =2;
    private static Integer FREQUENCY_3 =3;

    private static Price PRICE_130 = new Price(130);
    private static Price PRICE_50 = new Price(50);
    private static Price PRICE_20 = new Price(20);
    private static Price PRICE_MINUS20 = new Price(-20);

    private static StockItem STOCK_ITEM = new StockItem("ABC");

    @Mock
    private ChargeItemAccumulator chargeItemAccumulator;

    private MultiBuyPriceRule multiBuyPriceRule;
    private ChargeItem expectedChargeItem1;

    @Before
    public void setUp() {
        multiBuyPriceRule = new MultiBuyPriceRule(FREQUENCY_3, PRICE_130);
    }

    @Test
    public void addsDiscountItemIfFrequencyMet(){
        when(chargeItemAccumulator.hasNumberOfNonDiscountedItems(STOCK_ITEM,FREQUENCY_3)).thenReturn(true);

        expectedChargeItem1 = new DiscountChargeItem(STOCK_ITEM, MultiBuyPriceRule.MULTI_BUY_DISCOUNT,PRICE_MINUS20);

        multiBuyPriceRule.applyPriceRule(STOCK_ITEM,new UnitPrice(PRICE_50), chargeItemAccumulator);

        verify(chargeItemAccumulator).addChargeItem(expectedChargeItem1);
    }
    @Test
    public void flagsItemsAssDiscountedIfFrequencyMet(){
        when(chargeItemAccumulator.hasNumberOfNonDiscountedItems(STOCK_ITEM,FREQUENCY_3)).thenReturn(true);

        multiBuyPriceRule.applyPriceRule(STOCK_ITEM,new UnitPrice(PRICE_50), chargeItemAccumulator);

        verify(chargeItemAccumulator).flagItemsAsDiscounted(STOCK_ITEM,FREQUENCY_3);
    }
    @Test
    public void doesNotAddDiscountItemIfFrequencyNotMet(){
        when(chargeItemAccumulator.hasNumberOfNonDiscountedItems(STOCK_ITEM,FREQUENCY_3)).thenReturn(false);
        multiBuyPriceRule.applyPriceRule(STOCK_ITEM,new UnitPrice(PRICE_50), chargeItemAccumulator);

        verifyZeroInteractions(ignoreStubs(chargeItemAccumulator));
    }
    @Test
    public void doesNotApplyDiscountItemIfOriginalPriceLessThanMultiBuy(){
        when(chargeItemAccumulator.hasNumberOfNonDiscountedItems(STOCK_ITEM,FREQUENCY_3)).thenReturn(true);

        multiBuyPriceRule.applyPriceRule(STOCK_ITEM,new UnitPrice(PRICE_20), chargeItemAccumulator);

        verifyZeroInteractions(ignoreStubs(chargeItemAccumulator));
    }


}