package com.sapir.tictactoe;

import java.util.List;
import java.util.Random;

public class SelfPlayer extends Player{
    public SelfPlayer(PlayerType type, Game game) {
        super(type, game);
    }
    @Override
    public void run() {
        Random random = new Random();
        while(!game.isGameOver) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            synchronized (game){
                if (!game.isGameOver) {
                    
                    List<Cell> freeCells = game.getFreeCells();

                    if (freeCells.isEmpty()) {
                        System.err.println("Board is full");
                        game.setGameOver(true); 
                    }
                    
                    else if (game.getCurrentTurn() == this.type) {
                        int randomIndex = random.nextInt(freeCells.size());
                        Cell chosenCell = freeCells.get(randomIndex);
                        game.makeMove(chosenCell, type);
                    }
                     else {
                        try {
                            game.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
               
                
        }
    }
}}



