package com.sapir.stock;

public class StockUpdateThread extends Thread {
    private StockServer stockServer;
    
    public StockUpdateThread(StockServer stockServer) {
        this.stockServer = stockServer;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            // Update all stock types
            stockServer.UpdateStock(StockServer.Stock.MICROSOFT, 0);
            stockServer.UpdateStock(StockServer.Stock.APPLE, 0);
            stockServer.UpdateStock(StockServer.Stock.GOOGLE, 0);
            
            System.out.println("Stock prices updated (iteration " + (i + 1) + "/10)");
            
            try {
                // Sleep for 1 second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("StockUpdateThread finished updating");
    }
}
