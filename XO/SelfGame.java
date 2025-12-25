public class SelfGame extends Game {

    public SelfGame() {
        super(); 
        SelfPlayer playerX = new SelfPlayer(PlayerType.X, this);
        SelfPlayer playerO = new SelfPlayer(PlayerType.O, this);

        playerX.start();
        playerO.start();
    }
}