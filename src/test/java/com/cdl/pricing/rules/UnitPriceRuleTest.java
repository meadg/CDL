package com.cdl.pricing.rules;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.domain.charge.ChargeItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.charge.UnitChargeItem;
import com.cdl.domain.price.Price;
import com.cdl.domain.price.UnitPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UnitPriceRuleTest {


    private static Price PRICE_50 = new Price(50);
    private static UnitPrice UNIT_PRICE_50 = new UnitPrice(PRICE_50);
    private static StockItem STOCK_ITEM = new StockItem("ABC");

    @Mock
    private ChargeItemAccumulator chargeItemAccumulator;

    private MultiBuyPriceRule multiBuyPriceRule;
    private ChargeItem expectedChargeItem1;

    @Test
    public void appliesUnitPrice(){

        ChargeItem expectedChargeItem = new UnitChargeItem(STOCK_ITEM, UnitPriceRule.ITEM_PRICE, PRICE_50);
        UnitPriceRule unitPriceRule = new UnitPriceRule();
        unitPriceRule.applyPriceRule(STOCK_ITEM,UNIT_PRICE_50,chargeItemAccumulator);

        verify(chargeItemAccumulator).addChargeItem(expectedChargeItem);
    }


}