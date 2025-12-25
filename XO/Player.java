public abstract class Player extends Thread {
    protected PlayerType type;
    protected Game game;

    public Player(PlayerType type, Game game) {
        this.type = type;
        this.game = game;
    }

    @Override
    public abstract void run();

}
