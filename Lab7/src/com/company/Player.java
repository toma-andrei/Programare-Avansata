package com.company;

import java.util.Scanner;

public class Player extends Thread {
    private String name;
    private int score;
    final Game currentGame;

    public Player(String name, Game game) {
        currentGame = game;
        this.name = name;
    }

    @Override
    public void run() {
        while (currentGame.existsMove()) {
            synchronized (currentGame) {

                if (!currentGame.existsMove())
                    break;

                System.out.println(name + " is moving!");
                System.out.flush();

                currentGame.move(this);
            }
            System.out.println();
        }

        synchronized (this) {
            System.out.println("Game Over!");
            System.out.println(name + " " + score);
        }

    }

    public synchronized void addScore(int value) {
        score += value;
    }
}
