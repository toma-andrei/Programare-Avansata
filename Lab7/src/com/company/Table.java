package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    int dimension;
    private List<Integer> table = new ArrayList<>();

    public Table(int dimension) {

        this.dimension = dimension;

        for (int i = 0; i < dimension; i++) {
            table.forEach(e -> e = 0);
        }

        for (int i = 0; i < dimension; i++) {

            table.add((int) (Math.random() * 9 + 1));
        }
    }

    public List<Integer> getTable() {
        return table;
    }
}
