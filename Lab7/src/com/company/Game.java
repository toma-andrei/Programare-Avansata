package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private final Table table;
    private static final List<Integer> tokens = new ArrayList<>();

    public Game(Table table) {
        this.table = table;
    }

    public void move(Player player) {
        boolean playerMoved = false;
        int token;

        while (!playerMoved) {

            Scanner scan = new Scanner(System.in);

            System.out.print("Take token: ");
            System.out.flush();

            token = scan.nextInt();
            scan.nextLine();

            if(tokens.contains(token)){
                System.out.println("Move can't be done, try another!");
                continue;
            }

            playerMoved = true;
            tokens.add(token);
            player.addScore(table.getTable().get(token));
        }
    }

    public boolean existsMove() {
        return tokens.size() < table.dimension;
    }
}
