package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private Table table;
    private List<Integer> from = new ArrayList<>();
    private List<Integer> to = new ArrayList<>();

    public Game(Table table) {
        this.table = table;
    }

    public void move() {
        boolean playerMoved = false;
        int from;
        int to;

        while (!playerMoved) {

            Scanner scan = new Scanner(System.in);
            System.out.println(this.from.size());
            System.out.print("Move from: ");
            System.out.flush();
            from = scan.nextInt();
            scan.nextLine();

            System.out.print("Move to: ");
            System.out.flush();
            to = scan.nextInt();
            scan.nextLine();

            for (int i = 0; i < this.from.size(); i++) {
                if (this.from.get(i).equals(from) && this.to.get(i).equals(to)) {
                    System.out.println("Move can't be done! Try another!");
                    System.out.flush();
                    break;
                } else {
                    this.from.add(from);
                    this.to.add(to);
                }
            }
            playerMoved = true;
        }
    }

    public boolean existsMove() {
        return true;
    }
}
