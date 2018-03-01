package minions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private List<Ride> rides = new ArrayList<>();

    private int rows;
    private int columns;
    private int vehiclesCount;
    private int ridesCount;
    private int bonusCount;
    private int numberOfSteps;

    FileReader(String path) {
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
                    int skipped = 0;
                    Car car = new Car();
                    while ((line = br.readLine()) != null) {
                        lineSplit = line.split(" ");

                        int rowStart = Integer.valueOf(lineSplit[0]);
                        int columnStart = Integer.valueOf(lineSplit[1]);
                        int rowFinish = Integer.valueOf(lineSplit[2]);
                        int columnFinish = Integer.valueOf(lineSplit[3]);
                        int earliestStart = Integer.valueOf(lineSplit[4]);
                        int latestFinish = Integer.valueOf(lineSplit[5]);

                        Ride ride = new Ride(id++, rowStart, columnStart, rowFinish, columnFinish, earliestStart, latestFinish);

                        int diff = ride.getLatestFinish()-ride.getEarliestStart();
                        if(ride.getDuration() <= diff) {
                            rides.add(ride);
                        } else {
                            skipped++;
                        }

                        car.addRide(ride);
                    }
                    System.out.println("loaded rides: " + rides.size() + " Skipped: " + skipped);
                    System.out.println(car.toString());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ride> getRides() {
        return rides;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getVehiclesCount() {
        return vehiclesCount;
    }

    public int getRidesCount() {
        return ridesCount;
    }

    public int getBonusCount() {
        return bonusCount;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }
}
