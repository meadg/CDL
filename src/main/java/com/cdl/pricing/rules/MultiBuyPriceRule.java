package com.cdl.pricing.rules;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.domain.DiscountChargeItem;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;
import com.cdl.domain.price.UnitPrice;

public class MultiBuyPriceRule implements PriceRule {

    public static final String MULTI_BUY_DISCOUNT = "multi buy discount";
    private Integer numberRequired;
    private Price multiplePrice;

    public MultiBuyPriceRule(Integer numberRequired, Price multiplePrice) {

        this.numberRequired = numberRequired;
        this.multiplePrice = multiplePrice;
    }

    @Override
    public void applyPriceRule(StockItem stockItem, UnitPrice unitPrice, ChargeItemAccumulator chargeItemAccumulator) {

        if (chargeItemAccumulator.hasNumberOfNonDiscountedItems(stockItem, numberRequired)) {

            Price totalCharge = unitPrice.getPrice().multiply(numberRequired);
            if (multiplePrice.lessThan(totalCharge)) {
                createDiscountItem(stockItem, chargeItemAccumulator, totalCharge);

            }

        }
    }

    private void createDiscountItem(StockItem stockItem, ChargeItemAccumulator chargeItemAccumulator, Price totalCharge) {
        Price discountPrice = multiplePrice.minus(totalCharge);
        chargeItemAccumulator.addChargeItem(new DiscountChargeItem(stockItem, MULTI_BUY_DISCOUNT, discountPrice));
        //pass in discount type?
        chargeItemAccumulator.flagItemsAsDiscounted(stockItem,numberRequired);
    }
}
