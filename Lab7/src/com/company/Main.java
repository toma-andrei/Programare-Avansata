package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Name of player one: ");
        System.out.flush();
        String playerOne = scan.nextLine();

        System.out.print("Name of player two: ");
        System.out.flush();
        String playerTwo = scan.nextLine();

        Table table = new Table(5);
        Game game = new Game(table);

        Thread player1 = new Player(playerOne, game);
        Thread player2 = new Player(playerTwo, game);

        player1.start();
        Thread.sleep(400);
        player2.start();

        player1.join();
        player2.join();
    }
}
