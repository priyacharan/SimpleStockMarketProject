package com.stockmarket.jpmorgan.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.stockmarket.jpmorgan.constants.StockSymbol;

/**
 * Object that represents a stock .
 * 
 * 
 */

@XmlType(propOrder = { "stocksymbol", "stocktype", "lastDividend",
		"fixedDividend", "parValue", "stock", "marketPrice", "tradeRecords" })
public class Stock {
	private StockSymbol stocksymbol;
	private String stocktype;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;
	private List<Stock> stock;
	private double marketPrice;
	private List<StockTrade> tradeRecords;

	public List<StockTrade> getTradeRecords() {
		return tradeRecords;
	}

	public void setTradeRecords(List<StockTrade> tradeRecords) {
		this.tradeRecords = tradeRecords;
	}

	/* Class constructor */
	public Stock() {
		lastDividend = 0;
		fixedDividend = 0.0;
		tradeRecords = new ArrayList<StockTrade>();
		marketPrice = 0.0;
	}

	public Stock(StockSymbol stockSymbol2, String stockType2,
			double lastDividend2, double fixedDividend2, double parValue2) {
		this.stocksymbol = stockSymbol2;
		this.stocktype = stockType2;
		this.lastDividend = lastDividend2;
		this.fixedDividend = fixedDividend2;
		this.parValue = parValue2;
		marketPrice = 0.0;
		tradeRecords = new ArrayList<StockTrade>();
		
	}

	/* Getters and setters */

	public StockSymbol getstocksymbol() {
		return stocksymbol;
	}

	public void setstocksymbol(StockSymbol stockSymbol) {
		this.stocksymbol = stockSymbol;
	}


	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	@XmlElement(name = "stock-data")
	public List<Stock> getStock() {
		return stock;
	}

	public void setSymbol(List<Stock> Stock) {
		this.stock = Stock;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getStocktype() {
		return stocktype;
	}

	public void setStocktype(String stocktype) {
		this.stocktype = stocktype;
	}
}
