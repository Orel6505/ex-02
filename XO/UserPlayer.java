import java.util.List;
import java.util.Scanner;

public class UserPlayer extends Player {
    
    private Scanner scanner;

    public UserPlayer(PlayerType type, Game game) {
        super(type, game);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (!game.isGameOver) {
            synchronized (game) {
                if (!game.isGameOver) {
             
                    List<Cell> freeCells = game.getFreeCells();
                    if (freeCells.isEmpty()) {
                        System.out.println("Board is full");
                        game.setGameOver(true);
                    }
                    
                    else if (game.getCurrentTurn() == this.type) {
                        System.out.println("Enter row and column ");
                        
                        try {
                            int row = scanner.nextInt();
                            int col = scanner.nextInt();
                            Cell chosen = new Cell(row, col);
                            
                            boolean moveSuccess = game.makeMove(chosen, type);
                            if (!moveSuccess) {
                                System.out.println("Invalid move");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                            scanner.nextLine(); 
                        }
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
    }
}