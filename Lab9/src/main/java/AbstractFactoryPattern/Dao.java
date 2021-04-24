package AbstractFactoryPattern;

import java.util.List;

// https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm
public interface Dao<T> {

    public void add(T obj);
    public List<T> getAll();
}
