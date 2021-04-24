package AbstractFactoryPattern;

public class FactoryProducer {
    public static AbstractFactory getFactory(String implementation) {
        if (implementation.equalsIgnoreCase("JPA"))
            return new JPAImplementation();
        else if (implementation.equalsIgnoreCase("JDBC"))
            return new JDBCImplementation();
        return null;
    }
}
