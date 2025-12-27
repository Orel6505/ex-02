package com.sapir.stock;

import java.util.Random;

public class StockReaderThread extends Thread {
    private StockServer stockServer;
    private String personName;
    private StockServer.Stock stockType;
    private Random random;
    
    public StockReaderThread(StockServer stockServer, String personName, StockServer.Stock stockType) {
        this.stockServer = stockServer;
        this.personName = personName;
        this.stockType = stockType;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int stockValue = stockServer.GetStock(stockType);
            
            System.out.println("Name: " + personName + ", " + stockType + " Stock: " + stockValue + " USD");
            
            try {
                int sleepTime = 1000 + random.nextInt(2001); // Random between 1000-3000
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(personName + " finished reading stocks");
    }
}
