package com.sapir.stock;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Stock Trading System Started ===\n");
        
        StockServer stockServer = new StockServer();
        
        StockReaderThread tamiTan = new StockReaderThread(
            stockServer, 
            "Tami Tan", 
            StockServer.Stock.MICROSOFT
        );
        
        StockReaderThread timSroli = new StockReaderThread(
            stockServer, 
            "Tim Sroli", 
            StockServer.Stock.APPLE
        );
        
        StockReaderThread simaDids = new StockReaderThread(
            stockServer, 
            "Sima Dids", 
            StockServer.Stock.GOOGLE
        );
        
        StockUpdateThread updater = new StockUpdateThread(stockServer);
        
        // Start all threads
        System.out.println("Starting all threads...\n");
        tamiTan.start();
        timSroli.start();
        simaDids.start();
        updater.start();
        
        // Wait for all threads to complete
        try {
            tamiTan.join();
            timSroli.join();
            simaDids.join();
            updater.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== All threads completed! System shutting down ===");
    }
}
