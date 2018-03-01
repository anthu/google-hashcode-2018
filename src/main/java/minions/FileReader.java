package minions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    private List<String> fileContent;

    private int rows;
    private int columns;
    private int vehiclesCount;
    private int ridesCount;
    private int bonusCount;
    private int numberOfSteps;

    FileReader(String path) {
        fileContent = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {
                try {
                    String line = br.readLine();
                    String[] lineSplit = line.split(" ");
                    rows = Integer.valueOf(lineSplit[0]);
                    columns = Integer.valueOf(lineSplit[1]);
                    vehiclesCount = Integer.valueOf(lineSplit[2]);
                    ridesCount = Integer.valueOf(lineSplit[3]);
                    bonusCount = Integer.valueOf(lineSplit[4]);
                    numberOfSteps = Integer.valueOf(lineSplit[5]);


                    int id = 0;
                    while ((line = br.readLine()) != null) {
                        lineSplit = line.split(" ");

                        int rowStart = Integer.valueOf(lineSplit[0]);
                        int columnStart = Integer.valueOf(lineSplit[1]);
                        int rowFinish = Integer.valueOf(lineSplit[2]);
                        int columnFinish = Integer.valueOf(lineSplit[3]);
                        int earliestStart = Integer.valueOf(lineSplit[4]);
                        int latestFinish = Integer.valueOf(lineSplit[5]);

                        Ride ride = new Ride(rowStart, columnStart);

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
