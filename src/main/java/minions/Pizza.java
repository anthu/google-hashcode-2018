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

    public boolean isSliceValid(Slice slice, int configuration) {
        int numOfT = 0;
        int numOfM = 0;
        for(int i = slice.getTopLeft().getX(); i < slice.getBottomRight().getX(); i++) {
            for (int j = slice.getTopLeft().getY(); j < slice.getBottomRight().getY(); j++) {
                if(getCell(i,j) == Cell.MUSHROOM) {
                    numOfM++;
                } else {
                    numOfT++;
                }
            }
        }
        return numOfM > configuration && numOfT > configuration;
    }

    public Cell getCell(int x, int y) {
        return rowsWholePizza.get(y).get(x);
    }
}
