package minions;

import java.util.Arrays;

public enum Cell {
    MUSHROOM("M"),
    TOMATO("T"),
    OCCUPIED("X"),
    ;
    private final String name;
    Cell(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Cell byName(String name) {
        return Arrays.stream(Cell.values())
                .filter((cell) -> name.equals(cell.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("name unknown: " + name));
    }
}
