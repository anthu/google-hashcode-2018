package minions;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private final List<List<Cell>> rowsWholePizza;
    private final List<Slice> slices;

    public Pizza(List<List<Cell>> rowsWholePizza) {
        this.rowsWholePizza = rowsWholePizza;
        slices = new ArrayList<>();
    }


}
