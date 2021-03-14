package com.company;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Book extends MyFile {
    private int releaseYear;

    public Book(String name, int releaseYear, String path) throws InvalidYearException, InvalidPathException {
        if (releaseYear < 0 || releaseYear > 2021) {
            throw new InvalidYearException("invalid releaseYear!");
        }
        this.releaseYear = releaseYear;
        this.name = name;

        Path testPath = Paths.get(path);

        if (!(Files.exists(testPath))) {
            throw new InvalidPathException("Invalid Path!");
        }
        this.path = path;
    }


    @Override
    public String toString() {
        return "Book : " + name + " " + releaseYear + "  " + path + "\n";
    }
}
