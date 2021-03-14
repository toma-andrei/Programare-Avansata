package com.company;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Image extends MyFile {

    public Image(String name, String path) throws InvalidPathException {
        this.name = name;
        Path testPath = Paths.get(path);
        if(!(Files.exists(testPath))){
            throw new InvalidPathException("Invalid Path!");
        }
        this.path = path;
    }

    @Override
    public String toString() {
        return "Image : " + name + " " + path + "\n";
    }
}
