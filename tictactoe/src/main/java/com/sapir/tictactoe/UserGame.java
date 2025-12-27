package com.sapir.tictactoe;

public class UserGame extends Game {

    public UserGame() {
        super();
        UserPlayer playerX = new UserPlayer(PlayerType.X, this);
        SelfPlayer playerO = new SelfPlayer(PlayerType.O, this);

        playerX.start();
        playerO.start();
    }
}