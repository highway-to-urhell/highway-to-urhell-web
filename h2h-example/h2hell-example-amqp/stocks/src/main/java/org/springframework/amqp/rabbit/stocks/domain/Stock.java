/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.amqp.rabbit.stocks.domain;

/**
 * @author Mark Fisher
 * @author Dave Syer
 */
public class Stock {

	private String ticker;

	private StockExchange stockExchange;

	// For de-seialization:
	public Stock() {
	}
	
	public Stock(StockExchange stockExchange, String ticker) {
		this.stockExchange = stockExchange;
		this.ticker = ticker;
	}

	public String getTicker() {
		return ticker;
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}

	@Override
	public String toString() {
		return "Stock [ticker=" + ticker + ", stockExchange=" + stockExchange + "]";
	}

}
