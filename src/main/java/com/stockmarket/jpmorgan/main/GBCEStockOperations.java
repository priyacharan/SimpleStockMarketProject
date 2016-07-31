package com.stockmarket.jpmorgan.main;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.stockmarket.jpmorgan.constants.TradeIndicator;
import com.stockmarket.jpmorgan.model.Stock;
import com.stockmarket.jpmorgan.model.StockTrade;

/**
 * Class which is responsible for doing all required GBCE stock calculation operations
 * 
 * 
 */
public class GBCEStockOperations implements IStockOperations<Stock> {

    private static final double defaultValue = 0.0;
    

    /**
     * Method for calculating dividend yield for given stock data 
     */
    public double calculateDividendYield(Stock stock, double marketPrice) {

	stock.setMarketPrice(marketPrice);

	if (marketPrice > 0) {
	    if (stock.getStocktype().equalsIgnoreCase("COMMON")) {
		return new BigDecimal((stock.getLastDividend() / marketPrice)).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	    }

	    if (stock.getStocktype().equalsIgnoreCase("PREFFERED")) {
		return new BigDecimal(((stock.getFixedDividend() * stock.getParValue()) / marketPrice)).setScale(3).doubleValue();
	    }
	}

	return defaultValue;
    }
    /**
     * Method for calculating PE Ratio for given stock data 
     */
    public double calculatePeRatio(Stock stock, double marketPrice) {

	stock.setMarketPrice(marketPrice);

	if (stock.getLastDividend() > 0) {
	    return new BigDecimal((marketPrice / stock.getLastDividend())).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	return defaultValue;
    }

    /**
     * Method for calculating record trade
     */
    public StockTrade recordTrade(Stock stock, int nShares, TradeIndicator tradeIndicator, double tradePrice) {

	StockTrade stockTrade = new StockTrade();

	if (nShares * tradePrice > 0) {

	    stockTrade.setQuantity(nShares);
	    stockTrade.setTimeStamp(new Date());
	    stockTrade.setTradeIndicator(tradeIndicator);

	    tradePrice = new BigDecimal(tradePrice).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();

	    stockTrade.setTradePrice(tradePrice);

	    stock.getTradeRecords().add(stockTrade);
	}
	return stockTrade;
    }

    /**
     * Method for calculating Voulumeweighted stock price 
     */
    public double calculateVolumeWeightedStockPrice(Stock Stock,
	    int timeSpanInMinutes) {

	double divisor = 0.0;
	double divider = 0.0;
	for (StockTrade currentTrade : Stock.getTradeRecords()) {

	    if (isTradeProcessedWithinTimeSpan(currentTrade.getTimeStamp(), timeSpanInMinutes)) {
		divisor += currentTrade.getQuantity();
		divider += currentTrade.getTradePrice() * currentTrade.getQuantity();
	    }
	if (divisor > 0) {
	}
	    return new BigDecimal(divider / divisor).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	return defaultValue;
    }
    /**
     * 
     * Method that verifies if a given date is within the last n minutes
     * 
     */
    public static boolean isTradeProcessedWithinTimeSpan(Date timeStamp, int timeSpanInMinutes) {

    	Calendar currentDate = Calendar.getInstance();

    	Calendar targetDate = Calendar.getInstance();
    	targetDate.setTimeInMillis(currentDate.getTimeInMillis());
    	targetDate.add(Calendar.MINUTE, -timeSpanInMinutes);
    	
    	return timeStamp.compareTo(targetDate.getTime()) >= 0;
        }
    /**
     * Method for calculating GBCE All Share Index data
     */
    public double calculateGBCEAllShareIndex(List<Stock> allStocks) {

	double stockPriceMultiplied = 1.0;

	if (allStocks.size() > 0) {
	    for (Stock currStock : allStocks) {
		if (currStock.getMarketPrice() > 0) {
		    stockPriceMultiplied *= currStock.getMarketPrice();
		}
	    }
	    return new BigDecimal(Math.pow(stockPriceMultiplied, 1.0 / allStocks.size())).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	return defaultValue;
    }



}
