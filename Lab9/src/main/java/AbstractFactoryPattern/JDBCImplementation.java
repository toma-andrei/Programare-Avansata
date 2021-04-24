package AbstractFactoryPattern;

import entities.Movie;

public class JDBCImplementation extends AbstractFactory {

    @Override
    public Dao getDaoObject(String type) {
        System.out.println("Foloseste JDBCImplementation!!");
        if (type.equalsIgnoreCase("MOVIE")) {
            return new MovieDaoJDBC();
        }
        return null;
    }
}
