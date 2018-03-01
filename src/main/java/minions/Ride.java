package minions;

public class Ride {
    private final int id;
    private final int rowStart;
    private final int columnStart;
    private final int rowFinish;
    private final int columnFinish ;
    private final int earliestStart;
    private final int latestFinish;

    public Ride(int id, int rowStart, int columnStart, int rowFinish, int columnFinish, int earliestStart, int latestFinish) {
        this.id = id;
        this.rowStart = rowStart;
        this.columnStart = columnStart;
        this.rowFinish = rowFinish;
        this.columnFinish = columnFinish;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
    }

    public int getRowStart() {
        return rowStart;
    }

    public int getColumnStart() {
        return columnStart;
    }

    public int getRowFinish() {
        return rowFinish;
    }

    public int getColumnFinish() {
        return columnFinish;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public int getLatestFinish() {
        return latestFinish;
    }

    public int getId() {
        return id;
    }
}
