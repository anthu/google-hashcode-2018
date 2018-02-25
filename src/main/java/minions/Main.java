package minions;

import java.util.List;

public class Main {
    public static void main(String ... args) {
        FileReader fileReader = new FileReader("medium.in");

        Pizza pizza = fileReader.getPizza();
        int columnCount = fileReader.getColumnCount();
        int rowCount = fileReader.getRowCount();
        int minimumIngredients = fileReader.getMinimumIngredientsCount();
        int maxSize = fileReader.getMaxSize();

        System.out.println("Read pizza config was:");
        System.out.println("rows: " + rowCount + ", columns: " + columnCount + ", minimum ingredients:" +
                minimumIngredients + ", max size: " + maxSize);
    }
}
