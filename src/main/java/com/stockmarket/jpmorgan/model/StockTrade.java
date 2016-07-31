package com.stockmarket.jpmorgan.model;

import java.util.Date;

import com.stockmarket.jpmorgan.constants.TradeIndicator;

/**
 * Object that represents a stock trade operation.
 * 
 * 
 */
public class StockTrade {

    private Date	   timeStamp;
    private int	    quantity;
    private TradeIndicator tradeIndicator; 
    private double     tradePrice;

    /* Class constructor */
    public StockTrade() {
	timeStamp = new Date();
	quantity = 0;
	tradePrice = 0.0;
    }

    /* Getters and setters */

    public Date getTimeStamp() {
	return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
	this.timeStamp = timeStamp;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public TradeIndicator getTradeIndicator() {
	return tradeIndicator;
    }

    public void setTradeIndicator(TradeIndicator tradeIndicator) {
	this.tradeIndicator = tradeIndicator;
    }

    public double getTradePrice() {
	return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
	this.tradePrice = tradePrice;
    }

    @Override
    public String toString() {
	return "Time Stamp: " + timeStamp.toString() + "; Quantity: " + quantity + "; Operation: "
		+ tradeIndicator.getIndicator() + "; Trade Price: " + tradePrice + "; Trade total cost: " + (tradePrice*quantity);
    }
}
