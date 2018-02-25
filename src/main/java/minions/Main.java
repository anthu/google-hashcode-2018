package minions;

import java.util.List;

public class Main {
    public static void main(String ... args) {
        FileReader fileReader = new FileReader("medium.in");

        List<List<Cell>> pizza = fileReader.getPizza();
        int columnCount = fileReader.getColumnCount();
        int rowCount = fileReader.getRowCount();
        int minimumIngredients = fileReader.getMinimumIngredientsCount();
        int maxSize = fileReader.getMaxSize();

        System.out.println("Read pizza config was:");
        System.out.println("rows: " + pizza.size() + ", columns: " + pizza.get(0).size() + ", minimum ingredients:" +
                minimumIngredients + ", max size: " + maxSize);
    }
}
