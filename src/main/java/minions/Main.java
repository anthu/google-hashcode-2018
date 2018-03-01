package minions;

import java.util.List;

public class Main {
    public static void main(String ... args) {
        FileReader fileReader = new FileReader("medium.in");

        int columnCount = fileReader.getColumnCount();
        int rowCount = fileReader.getRowCount();
        int minimumIngredients = fileReader.getMinimumIngredientsCount();
        int maxSize = fileReader.getMaxSize();

    }
}
