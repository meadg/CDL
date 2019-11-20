package com.cdl.application;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.command.CheckOutApplicationCommand;
import com.cdl.command.CheckoutCommandReceiver;
import com.cdl.command.CommandFactory;
import com.cdl.command.StockItemChargingHandler;
import com.cdl.pricing.rules.StockItemPricingRule;
import com.cdl.pricing.rules.StockItemPricingRule.StockItemPricingRuleBuilder;
import com.cdl.pricing.rules.StockItemPricingRules;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;
import com.cdl.domain.price.UnitPrice;
import com.cdl.logging.ScanCommandLineLogger;
import com.cdl.logging.ScanLogger;
import com.cdl.pricing.rules.MultiBuyPriceRule;
import com.cdl.pricing.rules.PriceRule;
import com.cdl.pricing.rules.UnitPriceRule;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CheckOutApplication {



    private static Scanner scanner = new Scanner(new InputStreamReader(System.in));
    private CheckoutCommandReceiver commandReceiver;
    private CommandFactory commandFactory;

    public CheckOutApplication(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public static void main(String[] args) {

        CheckOutApplication checkOutApplication = createCheckOutApplication();
        CheckOutSession checkOutSession = initialiseSession();

        System.out.println("Please enter your command: ");

        String command = scanner.nextLine();
        CheckOutApplicationCommand appCommand = checkOutApplication.createCommand(command);

        while (!appCommand.isTerminating()) {
            checkOutSession.handleApplicationCommand(appCommand);
            command = scanner.nextLine();
            appCommand = checkOutApplication.createCommand(command);

        }
    }

    private CheckOutApplicationCommand createCommand(String command) {
        return commandFactory.createCommand(command);
    }


    private static CheckOutApplication createCheckOutApplication() {

        StockItemPricingRules stockItemPricingRules = new StockItemPricingRules();

        StockItemPricingRule stockItemPricingRule1 = new StockItemPricingRuleBuilder().withStockItem(new StockItem("A"))
                                                            .withUnitPrice(new UnitPrice(new Price(50)))
                                                            .withPriceRules(priceRules(new UnitPriceRule(),new MultiBuyPriceRule(3,new Price(130))))
                                                            .build();
        StockItemPricingRule stockItemPricingRule2 = new StockItemPricingRuleBuilder().withStockItem(new StockItem("B"))
                                                            .withUnitPrice(new UnitPrice(new Price(30)))
                                                            .withPriceRules(priceRules(new UnitPriceRule(),new MultiBuyPriceRule(2,new Price(45))))
                                                            .build();
        StockItemPricingRule stockItemPricingRule3 = new StockItemPricingRuleBuilder().withStockItem(new StockItem("C"))
                                                            .withUnitPrice(new UnitPrice(new Price(20)))
                                                            .withPriceRules(priceRules(new UnitPriceRule()))
                                                            .build();
        StockItemPricingRule stockItemPricingRule4 = new StockItemPricingRuleBuilder().withStockItem(new StockItem("D"))
                                                            .withUnitPrice(new UnitPrice(new Price(15)))
                                                            .withPriceRules(priceRules(new UnitPriceRule()))
                                                            .build();
        stockItemPricingRules.addStockItemPricingRule(new StockItem("A"),stockItemPricingRule1);
        stockItemPricingRules.addStockItemPricingRule(new StockItem("B"),stockItemPricingRule2);
        stockItemPricingRules.addStockItemPricingRule(new StockItem("C"),stockItemPricingRule3);
        stockItemPricingRules.addStockItemPricingRule(new StockItem("D"),stockItemPricingRule4);



        StockItemChargingHandler stockItemChargingHandler = new StockItemChargingHandler(stockItemPricingRules);
        CheckoutCommandReceiver checkoutCommandReceiver = new CheckoutCommandReceiver(stockItemChargingHandler);
        CommandFactory commandFactory = new CommandFactory(checkoutCommandReceiver);
        return new CheckOutApplication(commandFactory);
    }

    private static List<PriceRule> priceRules(PriceRule... priceRules){
        List<PriceRule> thePriceRules = new ArrayList<>();
        for (PriceRule theRule: priceRules
             ) {
            thePriceRules.add(theRule);
        }
        return thePriceRules;
    }

    private static CheckOutSession initialiseSession() {

        ScanLogger scanLogger = new ScanCommandLineLogger();
        CheckOutSession checkOutSession = new CheckOutSession(new ChargeItemAccumulator(),scanLogger);
        return checkOutSession;
    }
}
