package com.stockmarket.jpmorgan.constants;

/**
 * 
 * Constants for the trade indicator, as java enum
 * 
 * 
 */
public enum TradeIndicator {

    BUY("Buy"), SELL("Sell");

    private final String indicator;

    private TradeIndicator(final String indicator) {
	this.indicator = indicator;
    }

    public String getIndicator() {
	return indicator;
    }
    
    @Override
    public String toString() {
        return indicator;
    }
}
