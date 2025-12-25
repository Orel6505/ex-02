import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    PlayerType [][] gameBoard;
    protected PlayerType currentTurn;
    protected boolean isGameOver;
    protected PlayerType winner;
    protected final int WIN_COUNT = 3;
    protected final int SIZE = 5;

    public Game(){
        gameBoard = new PlayerType[SIZE][SIZE];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = PlayerType.NONE;
            }
        }
        currentTurn = PlayerType.X;
        isGameOver = false;
        winner = PlayerType.NONE;
        printBoard();
    }

    private synchronized void printBoard() {
       System.out.println();
        System.out.print("    "); 
        for (int k = 0; k < gameBoard.length; k++) {
            System.out.print(" " + k + "  ");
        }
        System.out.println(); 
        
        System.out.println("   ---------------------");

        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print(i + " |"); 
            for (int j = 0; j < gameBoard[i].length; j++) {
                String symbol = " ";
                if (gameBoard[i][j] != PlayerType.NONE) {
                    symbol = gameBoard[i][j].toString();
                }
                System.out.print(" " + symbol + " |");
            }
            System.out.println();
            System.out.println("   ---------------------");
        }
        System.out.println(); 
    }

    public PlayerType getTurn() {
        return currentTurn;
    }

    public List<Cell> getFreeCells() {
        List<Cell> freeCellsList = new ArrayList<>();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == PlayerType.NONE) {
                    freeCellsList.add(new Cell(i, j));
                }

            }
        
        }
        return freeCellsList;
    }

    public PlayerType[][] getGameBoard() {
        return gameBoard;
    }

    public PlayerType getCurrentTurn() {
        return currentTurn;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public PlayerType getWinner() {
        return winner;
    }

    public void setGameBoard(PlayerType[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setCurrentTurn(PlayerType currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void setWinner(PlayerType winner) {
        this.winner = winner;
    }
    public boolean checkWin(PlayerType playerType, Cell cell) {
        return (checkRow(playerType, cell.row, cell.col) ||
            checkColumn(playerType, cell.row, cell.col) ||
            checkMainDiagonal(playerType, cell.row, cell.col) ||
            checkAntiDiagonal(playerType, cell.row, cell.col));
    }

    public boolean checkRow(PlayerType playerType, int row, int col) {
        int count = 0, j=col+1;
        while(j<gameBoard.length && gameBoard[row][j]==playerType){
                j++;
                count++;
                if(count==WIN_COUNT){
                    return true;
                }
                    
        }  
        j=col-1;
        while(j>=0 && gameBoard[row][j]==playerType){
                j--;
                count++;
                if(count==WIN_COUNT){
                    return true;
                }
            }
        return false;
    }

    public boolean checkColumn(PlayerType playerType, int row, int col) {
        int count = 0, i=row+1;
        while(i<gameBoard.length && gameBoard[i][col]==playerType){
                i++;
                count++;
                if(count==WIN_COUNT){
                    return true;
                }
        }
        i=row-1;
        while(i>=0 && gameBoard[i][col]==playerType){
                i--;
                count++;
                if(count==WIN_COUNT){
                    return true;
                }
            }
        return false;
    }

    public boolean checkMainDiagonal(PlayerType playerType, int row, int col) {
        int count = 0, i=row+1, j=col+1;
        while(i<gameBoard.length && j<gameBoard[0].length && gameBoard[i][j]==playerType){
                i++;
                j++;
                count++;
                if(count==WIN_COUNT){
                    return true;
                }
        }
        i=row-1;
        j=col-1;
        while(i>=0 && j>=0 && gameBoard[i][j]==playerType){
                i--;
                j--;
                count++;
                if(count==WIN_COUNT){
                    return true;
                }
            }
        return false;
    }

    public boolean checkAntiDiagonal(PlayerType playerType, int row, int col) {
        int count = 0, i=row+1, j=col-1;
        while(i<gameBoard.length && j>=0 && gameBoard[i][j]==playerType){
                i++;
                j--;
                count++;
                if(count==WIN_COUNT){
                    return true;
                }
        }
        i=row-1;
        j=col+1;
        while(i>=0 && j<gameBoard[0].length && gameBoard[i][j]==playerType){
                i--;
                j++;
                count++;
                if(count==WIN_COUNT){
                    return true;
                }
            }
        return false;
    }

        public void checkGameOver(Cell cell, PlayerType playerType){
            if(checkWin(playerType, cell)){
                isGameOver = true;
                winner = playerType;
                System.out.println("GAME OVER. The Winner is " + winner);
            }
            else if(getFreeCells().isEmpty()){
                isGameOver = true;
                winner = PlayerType.NONE; 
                System.out.println("GAME OVER. It's a Draw");
            }
        }

    public synchronized boolean makeMove(Cell cell, PlayerType playerType) {
        if (gameBoard[cell.row][cell.col] == PlayerType.NONE && currentTurn == playerType && !isGameOver) {
            gameBoard[cell.row][cell.col] = playerType;
            printBoard();
            checkGameOver(cell, playerType);
            currentTurn = (currentTurn == PlayerType.X) ? PlayerType.O : PlayerType.X;
            notifyAll();
            return true;
        }
        return false;
    }



}
