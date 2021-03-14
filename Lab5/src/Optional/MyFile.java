package Optional;

import java.io.Serializable;

abstract class MyFile implements Serializable {
    String name;
    String path;

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
