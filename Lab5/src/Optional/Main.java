package Optional;

import java.io.IOException;
import java.util.*;

public class Main {

    //createCatalog name;
    //addImage catalogName imageName path
    //addBook catalogName bookName releaseYear path

    public static void main(String[] args) throws IOException, InvalidYearException, InvalidPathException {


       /* List<Catalog> catalogList = new ArrayList<>();

        Scanner comandaScanner = new Scanner(System.in);

        String comanda = "";

        while (!comanda.equals("quit")) {
            comanda = comandaScanner.nextLine();

            System.out.println(comanda);

            String[] parts = comanda.split(" ");

            if (parts[0].equals("createCatalog")) {
                catalogList.add(new Catalog(parts[1]));

            } else if (parts[0].equals("addImage")) {
                catalogList.indexOf(parts[0]);
            }

        }*/

        Catalog myCatalog = new Catalog("catalogName");
        Image copacImg = new Image("copac", "C:\\Users\\toma1\\IdeaProjects\\Lab5\\InputSources\\copac.jpg");
        Image caineImg = new Image("caine", "C:\\Users\\toma1\\IdeaProjects\\Lab5\\InputSources\\caine.jpg");
        Book cursJava = new Book("javaBook", 2014, "C:\\Users\\toma1\\IdeaProjects\\Lab5\\InputSources\\Cristian_Frasinaru-Curs_practic_de_Java.pdf");

        CatalogOperationExecutor myExecutor = new CatalogOperationExecutor();

        myExecutor.executeOperation(new AddCommand(myCatalog, copacImg));
        myExecutor.executeOperation(new AddCommand(myCatalog, caineImg));
        myExecutor.executeOperation(new AddCommand(myCatalog, cursJava));

        myExecutor.executeOperation(new ListCommand(myCatalog));

        myExecutor.executeOperation(new PlayCommand(myCatalog, "caine"));
    }
}
