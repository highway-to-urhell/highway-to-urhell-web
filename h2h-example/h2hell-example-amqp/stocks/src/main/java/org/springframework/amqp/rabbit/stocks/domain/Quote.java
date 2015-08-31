/*
 * Copyright 2002-2010 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.springframework.amqp.rabbit.stocks.domain;

import java.text.DateFormat;
import java.util.Date;

/**
 * Domain object representing a stock quote.
 * 
 * @author Mark Fisher
 */
public class Quote {

	private Stock stock;
	private String price;
	private long timestamp;

	private DateFormat format = DateFormat.getTimeInstance();

	public Quote() {
		this(null, null);
	}

	public Quote(Stock stock, String price) {
		this(stock, price, new Date().getTime());
	}

	public Quote(Stock stock, String price, long timestamp) {
		this.stock = stock;
		this.price = price;
		this.timestamp = timestamp;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getPrice() {
		return price;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimeString() {
		return format.format(new Date(timestamp));
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Quote [time=" + getTimeString() + ", stock=" + stock + ", price=" + price + "]";
	}

}
