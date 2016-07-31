package com.stockmarket.jpmorgan.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Object that represents a main global exchange stock .
 * 
 * 
 */
@XmlRootElement
public class Stocks {

	    private List<Stock> stock;
	    @XmlElement(name="stock-data")
		public List<Stock> getStock() {
			return stock;
		}

		public void setStock(List<Stock> stock) {
			this.stock = stock;
		}
	    
		

	  

	}
