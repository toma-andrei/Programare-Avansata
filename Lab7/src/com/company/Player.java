package com.company;

import java.util.Scanner;

public class Player extends Thread {
    private String name;

    Game currentGame;

    public Player(String name, Game game) {
        currentGame = game;
        this.name = name;
    }

    @Override
    public void run() {
        while (currentGame.existsMove()) {

            synchronized (currentGame) {
                System.out.println(name + " is moving!");
                System.out.flush();
                currentGame.move();
            }
            System.out.println();
        }
    }
}
