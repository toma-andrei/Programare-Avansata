package AbstractFactoryPattern;

public abstract class AbstractFactory {
    public abstract Dao getDaoObject(String type);
}
