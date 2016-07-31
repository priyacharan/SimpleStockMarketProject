package com.stockmarket.jpmorgan.testcase;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.stockmarket.jpmorgan.constants.StockSymbol;
import com.stockmarket.jpmorgan.constants.TradeIndicator;
import com.stockmarket.jpmorgan.convertor.XmlConvertor;
import com.stockmarket.jpmorgan.main.GBCEStockOperations;
import com.stockmarket.jpmorgan.model.Stock;

/**
 * Implemented some unit test cases
 * 
 *
 */
public class SimpleStocksUnitTestCases {

    @Test
    public void testDividendYield() throws JAXBException {
	HashMap<StockSymbol, Stock> testEntries = XmlConvertor.DataGenerator();
	GBCEStockOperations stockCalculator = new GBCEStockOperations();
	
	Stock stock = testEntries.get(StockSymbol.POP);
	double dividendYield = stockCalculator.calculateDividendYield(stock, 35.0);
	assertEquals(0.229, dividendYield);
    }
    
    @Test
    public void testPERatio() throws JAXBException{
	HashMap<StockSymbol, Stock> testEntries = XmlConvertor.DataGenerator();
	GBCEStockOperations stockCalculator = new GBCEStockOperations();
	
	Stock stock = testEntries.get(StockSymbol.POP);
	double peRatio = stockCalculator.calculatePeRatio(stock, 12.35);
	assertEquals(1.544, peRatio);
    }
    
    @Test
    public void testVolumeWeightedStockPrice() throws JAXBException{

	HashMap<StockSymbol, Stock> testEntries = XmlConvertor.DataGenerator();
	GBCEStockOperations stockCalculator = new GBCEStockOperations();
	
	Stock stock = testEntries.get(StockSymbol.POP);
	stockCalculator.recordTrade(stock, 40, TradeIndicator.SELL, 14.0);
	
	double volumeWeightedStockPrice = stockCalculator.calculateVolumeWeightedStockPrice(stock, 14);
	assertEquals(14.0, volumeWeightedStockPrice);
	
	stockCalculator.recordTrade(stock, 40, TradeIndicator.BUY, 12.5);
	volumeWeightedStockPrice = stockCalculator.calculateVolumeWeightedStockPrice(stock, 3);
	assertEquals(14.0, volumeWeightedStockPrice);
    }    
    
    @Test
    public void testGBCEAllShareIndex() throws JAXBException {

	HashMap<StockSymbol, Stock> testEntries = XmlConvertor.DataGenerator();
	List<Stock> testEntriesList = XmlConvertor.allGBCEStocksList(testEntries);

	GBCEStockOperations stockCalculator = new GBCEStockOperations();
	double gbceAllShareIndex = stockCalculator.calculateGBCEAllShareIndex(testEntriesList);

	assertEquals(1.0, gbceAllShareIndex);
	
	for(Stock currStock : testEntriesList) {
	    currStock.setMarketPrice(15.35);
	}
	gbceAllShareIndex = stockCalculator.calculateGBCEAllShareIndex(testEntriesList);
	assertEquals(15.35, gbceAllShareIndex);
    }

    @Test
    public void testDateIsWithinSpan() {
    	GBCEStockOperations stockCalculator = new GBCEStockOperations();
	int timeSpan = 15; // in minutes
	
	Calendar currentDate = Calendar.getInstance();

	Calendar targetDate = Calendar.getInstance();
	targetDate.setTimeInMillis(currentDate.getTimeInMillis());
	targetDate.add(Calendar.MINUTE, timeSpan);
	
	assertEquals(stockCalculator.isTradeProcessedWithinTimeSpan(targetDate.getTime(), 10), true);
	
    }

    @Test
    public void testDateIsNotWithinSpan() {
    	GBCEStockOperations stockCalculator = new GBCEStockOperations();
	int timeSpan = 15; // in minutes
	
	Calendar currentDate = Calendar.getInstance();

	Calendar targetDate = Calendar.getInstance();
	targetDate.setTimeInMillis(currentDate.getTimeInMillis());
	targetDate.add(Calendar.MINUTE, -timeSpan); // n minutes into the future 
	
	assertEquals(stockCalculator.isTradeProcessedWithinTimeSpan(targetDate.getTime(), 10), false);
	
    }
}
