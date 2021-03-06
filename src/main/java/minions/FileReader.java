package minions;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FileReader {
    private List<Ride> rides = new ArrayList<>();
    public static int overallScore = 0;
    private int rows;
    private int columns;
    private int vehiclesCount;
    private int ridesCount;
    private int bonusCount;
    private int numberOfSteps;

    FileReader(String path) {
        File f = new File(path);
        try (
            BufferedReader br = new BufferedReader(new java.io.FileReader(f))
        ) {
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

            while ((line = br.readLine()) != null) {
                lineSplit = line.split(" ");

                int rowStart = Integer.parseInt(lineSplit[0]);
                int columnStart = Integer.parseInt(lineSplit[1]);
                int rowFinish = Integer.parseInt(lineSplit[2]);
                int columnFinish = Integer.parseInt(lineSplit[3]);
                int earliestStart = Integer.parseInt(lineSplit[4]);
                int latestFinish = Integer.parseInt(lineSplit[5]);

                Ride ride = new Ride(id++, rowStart, columnStart, rowFinish, columnFinish, earliestStart, latestFinish);

                int diff = ride.getLatestFinish()-ride.getEarliestStart();
                if(ride.getDuration() <= diff) {
                    rides.add(ride);
                } else {
                    skipped++;
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Car> bestCars = getNextSortedSkipLate(rides);

        try(
                PrintWriter br = new PrintWriter(new FileWriter("output/" + f.getName().replace(".in", ".out")))
        ) {
            int totalScore = 0;
            for(Car car: bestCars) {
                br.write(car.toString() + "\n");
                totalScore += car.getCurrentScore();
            }
            System.out.println(f.getName() + ": " + totalScore);
            overallScore += totalScore;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Car> getSorted(List<Ride> rides) {
        List<Car> bestCars = new ArrayList<>();
        rides.sort(Comparator.comparing(Ride::getEarliestStart));
        for (int j = 0; j < vehiclesCount ; j++) {
            bestCars.add(new Car(bonusCount));
        }
        for(Ride ride : rides) {
            Car avcar = bestCars.get(0);
            for(Car car : bestCars) {
                if(car.getTimeAfterLastRide() < avcar.getTimeAfterLastRide()) {
                    avcar = car;
                }
            }
            avcar.addRide(ride);
        }
        return bestCars;
    }

    private List<Car> getNextSorted(List<Ride> rides) {
        List<Car> bestCars = new ArrayList<>();
        rides.sort(Comparator.comparing(Ride::getEarliestStart));
        for (int j = 0; j < vehiclesCount ; j++) {
            bestCars.add(new Car(bonusCount));
        }
        for(Ride ride : rides) {
            Car avcar = bestCars.get(0);
            for(Car car : bestCars) {
                if(getTimeTocome(avcar, ride) > getTimeTocome(car, ride)) {
                    avcar = car;
                }
            }
            avcar.addRide(ride);
        }
        return bestCars;
    }

    private List<Car> getNextSortedSkipLate(List<Ride> rides) {
        List<Car> bestCars = new ArrayList<>();
        rides.sort(Comparator.comparing(Ride::getLatestFinish));
        rides.sort(Comparator.comparing(Ride::getEarliestStart));
        for (int j = 0; j < vehiclesCount ; j++) {
            bestCars.add(new Car(bonusCount));
        }

        for(Ride ride : rides) {
            Car avcar = bestCars.get(0);
            for(Car car : bestCars) {
                if(getTimeTocome(avcar, ride) > getTimeTocome(car, ride)) {
                    avcar = car;
                }
            }
            if ((getTimeTocome(avcar,ride) + ride.getDuration()) < ride.getLatestFinish())
            {
                avcar.addRide(ride);
            }
        }
        return bestCars;
    }

    private List<Car> getNextSortedSkipLateWithMax(List<Ride> rides, int skipMax) {
        List<Car> bestCars = new ArrayList<>();
        rides.sort(Comparator.comparing(Ride::getEarliestStart));
        List<Ride> ridesCopy = new ArrayList<>(rides);

        boolean notFinished = true;
        while (notFinished) {
            int index = ThreadLocalRandom.current().nextInt(0, ridesCopy.size());
            if(ridesCopy.get(index).getDuration() < skipMax) {
                skipMax -= ridesCopy.get(index).getDuration();
                ridesCopy.remove(index);
            } else {
                notFinished = false;
            }
        }

        for (int j = 0; j < vehiclesCount ; j++) {
            bestCars.add(new Car(bonusCount));
        }
        int removed = 0;
        for(Ride ride : ridesCopy) {
            Car avcar = bestCars.get(0);
            for(Car car : bestCars) {
                if(getTimeTocome(avcar, ride) > getTimeTocome(car, ride)) {
                    avcar = car;
                }
            }
            if ((getTimeTocome(avcar,ride) + ride.getDuration()) < ride.getLatestFinish())
            {
                avcar.addRide(ride);
            } else {
                removed++;
            }
        }
        return bestCars;
    }


    private int getTimeTocome(Car car , Ride start) {
        return Math.abs(car.getColumnPosition() - start.getColumnStart()) + Math.abs(car.getRowPosition() - start.getRowStart()) + car.getTimeAfterLastRide();
    }

    private List<Car> getRandom() {
        int bestScore = 0;
        List<Car> bestCars = new ArrayList<>();
        for(int i = 0; i < 100000; i++) {
            List<Car> cars = new ArrayList<>();
            for (int j = 0; j < vehiclesCount ; j++) {
                cars.add(new Car(bonusCount));
            }

            int score = 0;
            for(Ride ride : rides) {
                int radnd = ThreadLocalRandom.current().nextInt(0, vehiclesCount +1);
                if (radnd < vehiclesCount) {
                    cars.get(radnd).addRide(ride);
                }
            }
            for(Car car : cars) {
                score += car.getCurrentScore();
            }
            if(score > bestScore) {
                System.out.println(score);
                bestScore = score;
                bestCars = cars;
            }
        }
        System.out.println(bestScore);
        return bestCars;
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
