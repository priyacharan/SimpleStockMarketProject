package com.stockmarket.jpmorgan.main;

import java.util.List;

import com.stockmarket.jpmorgan.constants.TradeIndicator;
import com.stockmarket.jpmorgan.model.Stock;
import com.stockmarket.jpmorgan.model.StockTrade;

/**
 * Interface for the stock calculator. 
 * 
 */
public interface IStockOperations<T extends Stock> {

    /**
     * @param stock  the stock being evaluated
     * @param marketPrice the established market price
     * 
     * @return the calculated dividend yield
     */
    public double calculateDividendYield(T stock, double marketPrice);

    /**
     * @param stock  the stock being evaluated
     * @param marketPrice the established market price
     * @return the calculated pe ratio
     */
    public double calculatePeRatio(T stock, double marketPrice);

    /**
     * 
     * @param stock the stock being transacted
     * @param nShares number of shares traded
     * @param tradeIndicator indication if it's a sell or buy trade
     * @param tradePrice the trade price
     * 
     * @return a stock trade record object
     */
    public StockTrade recordTrade(T stock, int nShares, TradeIndicator tradeIndicator, double tradePrice);

    /**
     * 
     * @param gbceStock
     * @param timeSpanInMinutes
     *            calculate trades for the past n minutes
     * 
     * @return the stocks volume weighted stock price
     */
    public double calculateVolumeWeightedStockPrice(T stock, int timeSpanInMinutes);

    /**
     * 
     * @param allStocks
     *            The list of all available stocks
     * @return GBCE Price share index
     */
    public double calculateGBCEAllShareIndex(List<T> allStocks);
}
