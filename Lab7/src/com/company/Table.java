package com.company;

import java.util.Arrays;

public class Table {
    int dimension = 5;
    private int table[][];


    public Table(int dimension) {

        this.dimension = dimension;

        table = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            Arrays.fill(table[i], 0);
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = i + 1; j < dimension; j++) {
                if (j > i) {
                    table[i][j] = (int) (Math.random() * 9 + 1);
                    table[j][i] = table[i][j];
                }
            }
        }
    }

    public int[][] getTable() {
        return table;
    }
}
