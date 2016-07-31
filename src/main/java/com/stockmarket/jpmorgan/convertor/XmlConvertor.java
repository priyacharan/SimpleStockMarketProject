package com.stockmarket.jpmorgan.convertor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.stockmarket.jpmorgan.constants.StockSymbol;
import com.stockmarket.jpmorgan.model.Stock;
import com.stockmarket.jpmorgan.model.Stocks;

public class XmlConvertor {

	public static HashMap<StockSymbol, Stock> DataGenerator()
			throws JAXBException {
		List<Stock> values = xmltoJavaConvertor();
		HashMap<StockSymbol, Stock> stockData = dataMap(values);
		return stockData;
	}

	/*
	 * Putting all sample data into a hashmap with the purpose of simplifying
	 * the stock search process
	 */
	private static HashMap<StockSymbol, Stock> dataMap(List<Stock> stockData) {
		HashMap<StockSymbol, Stock> sampleDataMap = new HashMap<StockSymbol, Stock>();
		Stock sampleStockData = null;

		for (Stock currStock : stockData) {
			sampleStockData = new Stock(currStock.getstocksymbol(),
					currStock.getStocktype(), currStock.getLastDividend(),
					currStock.getFixedDividend(), currStock.getParValue());
			sampleDataMap.put(currStock.getstocksymbol(), sampleStockData);
		}
		return sampleDataMap;
	}

	private static List<Stock> xmltoJavaConvertor() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Stocks.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		File xml = new File("src/main/resources/SampleData.xml");
		Stocks data = (Stocks) unmarshaller.unmarshal(xml);
		return data.getStock();
	}

	public static List<Stock> allGBCEStocksList(
			HashMap<StockSymbol, Stock> sampleData) {

		List<Stock> allStocks = new ArrayList<Stock>();

		for (Map.Entry<StockSymbol, Stock> entry : sampleData.entrySet()) {
			allStocks.add(entry.getValue());
		}
		return allStocks;
	}

}
