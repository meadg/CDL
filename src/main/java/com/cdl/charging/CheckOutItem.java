package com.cdl.charging;

import com.cdl.domain.ChargeItem;
import com.cdl.domain.StockItem;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CheckOutItem {

        private ChargeItem chargeItem;
        private int  subTotal;
        private boolean discounted =false;
        private boolean printed =false;

        public CheckOutItem(ChargeItem chargeItem,int subTotal) {
            this.chargeItem = chargeItem;
            this.subTotal = subTotal;
        }

        public int getSubTotal(){
            return subTotal;
        }

        public boolean matchesStockItem(StockItem stockItem) {
            return chargeItem.matchesStockItem(stockItem);
        }

        public boolean isDiscountAble() {
            return chargeItem.isDiscountAble() && discounted==false;
        }

        public void flagAsDiscounted() {
            this.discounted = true;
        }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
