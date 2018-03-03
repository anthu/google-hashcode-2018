package minions;


import java.util.List;

import java.util.ArrayList;

public class Car {
    private List<Ride> rides = new ArrayList<>();
    private int timeAfterLastRide = 0;
    private int columnPosition = 0;
    private int rowPosition = 0;
    private int currentScore = 0;
    private int bonus;

    public Car(int bonus) {
        this.bonus = bonus;
    }

    public void addRide(Ride ride){
        int timeToStart = Math.abs(columnPosition - ride.getColumnStart()) + Math.abs(rowPosition - ride.getRowStart());
        int startTime = timeAfterLastRide + timeToStart;

        // wait for start and receive bonus
        if (startTime <= ride.getEarliestStart()) {
            startTime = ride.getEarliestStart();
            this.currentScore += this.bonus;
        }

        timeAfterLastRide = startTime + ride.getDuration();

        // receive points for in-time delivery
        if(timeAfterLastRide < ride.getLatestFinish()) {
            this.currentScore += ride.getDuration();
        }

        columnPosition = ride.getColumnFinish();
        rowPosition = ride.getRowFinish();

        rides.add(ride);
    }

    public List<Ride> getRides() {
        return rides;
    }

    public int getTimeAfterLastRide() {
        return timeAfterLastRide;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rides.size());
        sb.append(" ");

        for(Ride ride : rides) {
            sb.append(ride.getId());
            sb.append(" ");
        }
        return sb.toString().trim();
    }

}
