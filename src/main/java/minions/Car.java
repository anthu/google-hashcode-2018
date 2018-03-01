package minions;


import java.util.List;

import java.util.ArrayList;

public class Car {
    private List<Ride> rides = new ArrayList<>();
    private int timeAfterLastRide = 0;
    private int columnPosition = 0;
    private int rowPosition = 0;

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
}
