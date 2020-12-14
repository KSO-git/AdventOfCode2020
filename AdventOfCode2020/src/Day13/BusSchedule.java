package Day13;

import javafx.util.Pair;

import java.util.List;

public class BusSchedule {
    int startTimestamp;
    List<Integer> avaiableBusses;
    List<String> rawIds;

    public BusSchedule() {
    }


    public BusSchedule(int startTimestamp, List<Integer> avaiableBusses) {
        this.startTimestamp = startTimestamp;
        this.avaiableBusses = avaiableBusses;
    }

    public int getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(int startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public List<Integer> getAvaiableBusses() {
        return avaiableBusses;
    }

    public void setAvaiableBusses(List<Integer> avaiableBusses) {
        this.avaiableBusses = avaiableBusses;
    }

    public List<String> getRawIds() {
        return rawIds;
    }

    public void setRawIds(List<String> rawIds) {
        this.rawIds = rawIds;
    }

    public Pair<Integer, Integer> getEarliestLeavingBus(int timestamp) {
        Pair<Integer, Integer> result = new Pair<>(0, 0);
        boolean indicatorForLoop = true;
        int currentTimestamp = timestamp;
        while (indicatorForLoop) {
            for (Integer temp : this.avaiableBusses) {
                if (currentTimestamp % temp == 0) {
                    indicatorForLoop = false;
                    result = new Pair<>(currentTimestamp - this.startTimestamp, temp);
                    break;
                }
            }
            currentTimestamp++;
        }
        return result;
    }

    public long getBusIdMulByTimeToWait() {
        Pair<Integer, Integer> resultDataSet = getEarliestLeavingBus(this.startTimestamp);
        return resultDataSet.getKey() * resultDataSet.getValue();
    }
}
