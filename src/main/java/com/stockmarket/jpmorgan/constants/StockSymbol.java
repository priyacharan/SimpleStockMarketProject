package com.stockmarket.jpmorgan.constants;


/**
 * 
 * Constants defined for the GBCE Stock Symbol
 * 
 *
 */
public enum StockSymbol {

    TEA("TEA"), POP("POP"), ALE("ALE"), GIN("GIN"), JOE("JOE");
    
    private final String symbol;

    StockSymbol(final String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() {
	return symbol;
    }
    
    @Override
    public String toString() {
        return symbol;
    }
}
