package com.stockmarket.jpmorgan.main;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import com.stockmarket.jpmorgan.constants.StockSymbol;
import com.stockmarket.jpmorgan.constants.TradeIndicator;
import com.stockmarket.jpmorgan.convertor.XmlConvertor;
import com.stockmarket.jpmorgan.model.Stock;
import com.stockmarket.jpmorgan.model.StockTrade;

/**
 * 
 * Main class which is the entry point where the application starts
 *
 */
public class SimpleStockMarket {

    public static void main(String[] args) throws JAXBException {

	HashMap<StockSymbol, Stock> sampleData = XmlConvertor.DataGenerator();
	GBCEStockOperations stockOperations = new GBCEStockOperations();

	/* Simulated input */
	String inputStock = "pop";
	double marketPrice = 12.25;
	int nShares = 40;
	String tradeIndicator = "sell";
	double tradePrice = 18.30;

	Stock stock = sampleData.get(StockSymbol.valueOf(inputStock.toUpperCase()));

	System.out.println("Calculating Dividend Yield");
	System.out.println(stockOperations.calculateDividendYield(stock, marketPrice));

	System.out.println("Calculating Pe Ratio");
	System.out.println(stockOperations.calculatePeRatio(stock, marketPrice));

	System.out.println("Calculating Record Trade");
	
	StockTrade trade = stockOperations.recordTrade(stock, nShares, TradeIndicator.valueOf(tradeIndicator.toUpperCase()), tradePrice);

	if (trade.getQuantity() > 0) {
	    System.out.println("Recorded trade for stock " + stock.getstocksymbol() + ":\n" + trade.toString());
	} else {
	    System.out.println("No stocks were traded.");
	}

	System.out.println("Claculating volume weighted stock price for time span in 15 minutes");
	int timeSpanInMinutes = 15;
	System.out.println(stockOperations.calculateVolumeWeightedStockPrice(stock, timeSpanInMinutes));

//	System.out.println("Calculating GBCE All share index");
	System.out.println(stockOperations.calculateGBCEAllShareIndex(XmlConvertor.allGBCEStocksList(sampleData)));
    }
}
