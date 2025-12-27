package com.sapir.stock;

import java.security.InvalidParameterException;
import java.util.Random;

public class StockServer {
	
	private int microsoftValue = 220;
	private int appleValue = 110;
	private int googleValue = 1512;
	private Random random = new Random();
	
	public enum Stock {
		MICROSOFT,
		APPLE,
		GOOGLE
	}	
	
	public synchronized int GetStock(Stock stock) {
		switch(stock) {
		case MICROSOFT:
			return microsoftValue;
		case APPLE:
			return appleValue;
		case GOOGLE:
			return googleValue;
		default:
			throw new InvalidParameterException("no such stock type");
		}
	}

	public synchronized void UpdateStock(Stock stock, int value) {
		int randomValue = 100 + random.nextInt(401); // Random value between 100-500
		switch(stock) {
		case MICROSOFT:
			microsoftValue = randomValue;
			break;
		case APPLE:
			appleValue = randomValue;
			break;
		case GOOGLE:
			googleValue = randomValue;
			break;
		default:
			throw new InvalidParameterException("no such stock type");
		}
	}
	
}

