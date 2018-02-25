package minions;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private final List<List<Cell>> rowsWholePizza;

    private final List<List<Cell>> rowsRemainingPizza;

    private final List<Slice> slices;

    public Pizza(List<List<Cell>> rowsWholePizza) {
        this.rowsWholePizza = rowsWholePizza;

        rowsRemainingPizza = new ArrayList<>();
        for(List<Cell> row : rowsWholePizza) {
            List<Cell> rowCopy = new ArrayList<>();
            rowCopy.addAll(row);
            rowsRemainingPizza.add(rowCopy);
        }

        slices = new ArrayList<>();
    }

    public void addSlice(Slice slice) {
        slices.add(slice);

    }

}
