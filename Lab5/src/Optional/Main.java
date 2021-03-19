package Optional;

import java.util.*;

public class Main {

    public static void main(String[] args) throws TooFewArgumentsException {

        /*citeste cate o comanda de la tastatura si o rezolva sau afiseaza exceptii daca e necesar (comenzi gresite, fisiere care nu exista etc.).

    comenzi:
        createCatalog name;
        addImage catalogName imageName path
        addBook catalogName bookName releaseYear path
        play catalogName elemName
        save catalogName
        */

        List<Catalog> catalogList = new ArrayList<>();

        Scanner comandaScanner = new Scanner(System.in);

        String comanda = "";

        /*clasa ce "executa" comenzile*/
        CatalogOperationExecutor myExecutor = new CatalogOperationExecutor();


        /*Foloseste designul Command pattern (Comenzile sunt vazute ca si obiecte).
         * AddCommand, ListCommand, LoadCommand, PlayCommand, SaveCommand sunt clasele pentru comenzi
         *
         * Catalog este clasa ce va efectua comenzile (numita ReceiverClass in Command Pattern)
         *
         * CatalogOperationExecutor este clasa ce va apela metoda execute() pentru o anumita comanda (numita InvokerClass)
         *
         * Main este clasa ce va spune cand se executa o comanda (Clasa Client din Command Pattern)*/

        while (!comanda.equals("quit")) {
            comanda = comandaScanner.nextLine();

            // System.out.println(comanda);

            String[] parts = comanda.split(" ");

            try {

                if (parts[0].equals("createCatalog")) {
                    if (parts.length > 2)
                        throw new TooManyArgumentsException("too many arguments for createCatalog call!");
                    else if (parts.length < 2)
                        throw new TooFewArgumentsException("too few arguments for createCatalog call!");

                    for (Catalog catalog : catalogList) {
                        if (catalog.getName().equals(parts[1])) {
                            throw new GeneralException("catalog with name " + parts[1] + " already exists!");

                        }
                    }

                    catalogList.add(new Catalog(parts[1]));

                } else if (parts[0].equals("addImage")) {
                    if (parts.length > 4)
                        throw new TooManyArgumentsException("too many arguments for addImage call!");
                    else if (parts.length < 4)
                        throw new TooFewArgumentsException("too few arguments for addImage call!");

                    Catalog myCatalog = null;
                    for (Catalog catalog : catalogList) {
                        if (catalog.getName().equals(parts[1])) {
                            myCatalog = catalog;
                            break;
                        }
                    }

                    if (myCatalog == null)
                        throw new GeneralException("catalog does not exist!");

                    Image imageFile = new Image(parts[2], parts[3]);
                    myExecutor.executeOperation(new AddCommand(myCatalog, imageFile));

                } else if (parts[0].equals("addBook")) {
                    if (parts.length > 4)
                        throw new TooManyArgumentsException("too many arguments for addBook call!");
                    else if (parts.length < 4)
                        throw new TooFewArgumentsException("too few arguments for addBook call!");

                    Catalog myCatalog = null;

                    for (Catalog catalog : catalogList) {
                        if (catalog.getName().equals(parts[1])) {
                            myCatalog = catalog;
                            break;
                        }
                    }

                    if (myCatalog == null)
                        throw new GeneralException("catalog does not exist!");

                    Book bookFile = new Book(parts[2], Integer.parseInt(parts[3]), parts[4]);
                    myExecutor.executeOperation(new AddCommand(myCatalog, bookFile));

                } else if (parts[0].equals("play")) {
                    if (parts.length > 3)
                        throw new TooManyArgumentsException("too many arguments for play call!");
                    else if (parts.length < 3)
                        throw new TooFewArgumentsException("too few arguments for play call!");

                    Catalog myCatalog = null;

                    for (Catalog catalog : catalogList) {
                        if (catalog.getName().equals(parts[1])) {
                            myCatalog = catalog;
                            break;
                        }
                    }

                    if (myCatalog == null)
                        throw new GeneralException("catalog does not exist!");

                    myExecutor.executeOperation(new PlayCommand(myCatalog, parts[2]));

                } else if (parts[0].equals("list")) {
                    if (parts.length > 2)
                        throw new TooManyArgumentsException("too many arguments for list call!");
                    else if (parts.length < 2)
                        throw new TooFewArgumentsException("too few arguments for list call!");

                    Catalog myCatalog = null;

                    for (Catalog catalog : catalogList) {
                        if (catalog.getName().equals(parts[1])) {
                            myCatalog = catalog;
                            break;
                        }
                    }

                    if (myCatalog == null)
                        throw new GeneralException("catalog does not exist!");

                    myExecutor.executeOperation(new ListCommand(myCatalog));

                } else if (parts[0].equals("save")) {
                    if (parts.length > 2)
                        throw new TooManyArgumentsException("too many arguments for play call!");
                    else if (parts.length < 2)
                        throw new TooFewArgumentsException("too few arguments for play call!");

                    Catalog myCatalog = null;

                    for (Catalog catalog : catalogList) {
                        if (catalog.getName().equals(parts[1])) {
                            myCatalog = catalog;
                            break;
                        }
                    }

                    if (myCatalog == null)
                        throw new GeneralException("catalog does not exist!");

                    myExecutor.executeOperation(new SaveCommand(myCatalog, parts[1]));

                } else if (parts[0].equals("load")) {
                    if (parts.length > 2)
                        throw new TooManyArgumentsException("too many arguments for play call!");
                    else if (parts.length < 2)
                        throw new TooFewArgumentsException("too few arguments for play call!");

                    catalogList.add(new Catalog("tempName"));
                    Catalog myCatalog = catalogList.get(catalogList.size() - 1);

                    myExecutor.executeOperation(new LoadCommand(myCatalog, (parts[1] + ".ser")));
                } else if (parts[0].equals("quit")) {
                    break;
                } else {
                    throw new GeneralException("command unknown!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
