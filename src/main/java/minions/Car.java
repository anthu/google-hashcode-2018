package minions;


import java.util.List;

import java.util.ArrayList;

public class Car {
    private List<Ride> rides = new ArrayList<>();
    private int timeAfterLastRide = 0;
    private int columnPosition = 0;
    private int rowPosition = 0;
    private int currentTime = 0;
    private int currentScore = 0;
    public void addRide(Ride ride){
        int timeToStart = Math.abs(columnPosition - ride.getColumnStart()) + Math.abs(rowPosition - ride.getRowStart());
        int timeAtStartPositionArrived = timeAfterLastRide + timeToStart;
        int startTime = timeAtStartPositionArrived >= ride.getEarliestStart() ? timeAtStartPositionArrived : ride.getEarliestStart();
        timeAfterLastRide = startTime + ride.getDuration();
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

    public int score(int b) {
        currentTime = 0;
        currentScore = 0;
        Ride previousRide = new Ride(0,0,0,0,-1,-1, -1);
        for (Ride ride : this.rides) {
            if (second(ride)) {
                currentScore += b;
            }
            if(firstTrue(previousRide,ride)) {
                currentScore += ride.getDuration();
            }
        }
        return currentScore;
    }

    private boolean firstTrue(Ride previous, Ride current) {
        int arTime = getArrival(previous, current);
        int start = (arTime < current.getEarliestStart()) ? arTime : this.currentTime;
        this.currentTime = (start + current.getDuration());
        return this.currentTime < current.getLatestFinish();
    }

    private static int getArrival(Ride prev, Ride cur) {
        return Math.abs(prev.getRowFinish() - cur.getRowStart()) + Math.abs(prev.getColumnFinish() - cur.getRowStart());
    }

    private boolean second(Ride current) {
        return (current.getEarliestStart() == this.currentTime);
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
