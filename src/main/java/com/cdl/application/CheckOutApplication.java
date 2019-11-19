package com.cdl.application;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.command.BeginCheckoutCommand;
import com.cdl.command.CheckOutApplicationCommand;
import com.cdl.command.CheckoutCommandReceiver;
import com.cdl.command.CompleteCheckoutCommand;
import com.cdl.command.ScanItemCommand;
import com.cdl.command.StockItemChargingHandler;
import com.cdl.command.StockItemPricingRule;
import com.cdl.command.StockItemPricingRule.StockItemPricingRuleBuilder;
import com.cdl.command.StockItemPricingRules;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;
import com.cdl.domain.price.UnitPrice;
import com.cdl.pricing.rules.MultiBuyPriceRule;
import com.cdl.pricing.rules.UnitPriceRule;

import java.io.InputStreamReader;
import java.util.Scanner;

import static com.google.common.collect.Lists.newArrayList;

public class CheckOutApplication {



    private static Scanner scanner = new Scanner(new InputStreamReader(System.in));
    private CheckoutCommandReceiver checkoutCommandReceiver;

    public CheckOutApplication(CheckoutCommandReceiver checkoutCommandReceiver) {
        this.checkoutCommandReceiver = checkoutCommandReceiver;
    }

    public static void main(String[] args) {

        CheckOutApplication checkOutApplication = createCheckOutApplication();
        CheckOutSession checkOutSession = initialiseSession();

        System.out.println("Please enter your command: ");

        String command = scanner.nextLine();

        do {
            CheckOutApplicationCommand appCommand = checkOutApplication.createCommand(command);
            checkOutSession.handleApplicationCommand(appCommand);
            command = scanner.nextLine();

        } while (!command.equals("Exit"));



    }

    private CheckOutApplicationCommand createCommand(String command) {

        if(command.startsWith("BeginCheckOut")){
            return new BeginCheckoutCommand(checkoutCommandReceiver);
        }else if(command.startsWith("FinishCheckOut")){
            return new CompleteCheckoutCommand(checkoutCommandReceiver);
        }else if (command.startsWith("ScanItem:")){
            int colonPosition = command.indexOf(":");
            String productCode = command.substring(colonPosition+1).trim();
            return new ScanItemCommand(checkoutCommandReceiver,new StockItem(productCode));
        }
        return null;
    }

    private static CheckOutApplication createCheckOutApplication() {

        StockItemPricingRules stockItemPricingRules = new StockItemPricingRules();

        StockItemPricingRule stockItemPricingRule1 = new StockItemPricingRuleBuilder().withStockItem(new StockItem("A"))
                                                            .withUnitPrice(new UnitPrice(new Price(50)))
                                                            .withPriceRules(newArrayList(new UnitPriceRule(),new MultiBuyPriceRule(3,new Price(130))))
                                                            .build();
        StockItemPricingRule stockItemPricingRule2 = new StockItemPricingRuleBuilder().withStockItem(new StockItem("B"))
                                                            .withUnitPrice(new UnitPrice(new Price(30)))
                                                            .withPriceRules(newArrayList(new UnitPriceRule(),new MultiBuyPriceRule(2,new Price(45))))
                                                            .build();
        StockItemPricingRule stockItemPricingRule3 = new StockItemPricingRuleBuilder().withStockItem(new StockItem("C"))
                                                            .withUnitPrice(new UnitPrice(new Price(20)))
                                                            .withPriceRules(newArrayList(new UnitPriceRule()))
                                                            .build();
        StockItemPricingRule stockItemPricingRule4 = new StockItemPricingRuleBuilder().withStockItem(new StockItem("D"))
                                                            .withUnitPrice(new UnitPrice(new Price(15)))
                                                            .withPriceRules(newArrayList(new UnitPriceRule()))
                                                            .build();
        stockItemPricingRules.addStockItemPricingRule(new StockItem("A"),stockItemPricingRule1);
        stockItemPricingRules.addStockItemPricingRule(new StockItem("B"),stockItemPricingRule2);
        stockItemPricingRules.addStockItemPricingRule(new StockItem("C"),stockItemPricingRule3);
        stockItemPricingRules.addStockItemPricingRule(new StockItem("D"),stockItemPricingRule4);



        StockItemChargingHandler stockItemChargingHandler = new StockItemChargingHandler(stockItemPricingRules);
        CheckoutCommandReceiver checkoutCommandReceiver = new CheckoutCommandReceiver(stockItemChargingHandler);
        return new CheckOutApplication(checkoutCommandReceiver);
    }

    private static CheckOutSession initialiseSession() {

        ScanLogger scanLogger = new ScanCommandLineLogger();
        CheckOutSession checkOutSession = new CheckOutSession(new ChargeItemAccumulator(),scanLogger);
        return checkOutSession;
    }
}
