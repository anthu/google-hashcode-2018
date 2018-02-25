package minions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    private static final int CONFIG_ROW_INDEX = 0;
    private static final int CONFIG_ROW_ROW_COUNT_INDEX = 0;
    private static final int CONFIG_ROW_COLUMN_COUNT_INDEX = 1;
    private static final int CONFIG_ROW_MINIMUM_INDEX = 2;
    private static final int CONFIG_ROW_MAX_SIZE_INDEX = 3;
    private List<String> fileContent;

    FileReader(String path) {
        fileContent = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {
                List<List<Cell>> rows = new ArrayList();
                String line;
                try {
                    while ((line = br.readLine()) != null) {
                        fileContent.add(line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Pizza getPizza() {
        List<List<Cell>> pizza = new ArrayList<>();
        boolean isFirstLine = true;
        for (String lineInFile : fileContent) {
            if (!isFirstLine) {
                String[] cells = lineInFile.split("");
                List<Cell> row = Arrays.stream(cells)
                        .map(Cell::byName)
                        .collect(Collectors.toList());
                pizza.add(row);
            } else {
                isFirstLine = false;
            }
        }
        return new Pizza(pizza);
    }

    public int getRowCount() {
        return getConfigByIndex(CONFIG_ROW_ROW_COUNT_INDEX);
    }


    public int getColumnCount() {
        return getConfigByIndex(CONFIG_ROW_COLUMN_COUNT_INDEX);
    }

    public int getMinimumIngredientsCount() {
        return getConfigByIndex(CONFIG_ROW_MINIMUM_INDEX);
    }

    public int getMaxSize() {
        return getConfigByIndex(CONFIG_ROW_MAX_SIZE_INDEX);
    }

    private Integer getConfigByIndex(int index) {
        return Integer.valueOf(fileContent.get(CONFIG_ROW_INDEX).split(" ")[index]);
    }
}
