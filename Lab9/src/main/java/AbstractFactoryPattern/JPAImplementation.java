package AbstractFactoryPattern;

public class JPAImplementation extends AbstractFactory{

    @Override
    public Dao getDaoObject(String type) {
        System.out.println("Foloseste JPAImplementation!!");
        if (type.equalsIgnoreCase("MOVIE")) {
            return new MovieDaoJPA();
        }
        return null;
    }
}
