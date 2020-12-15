package Day13;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        BusSchedule partOne = getData();
        if (makeTest(getTestData(), 295)) {
            System.out.println("Part, Part 1 Success");
        }
        if (makeTestForPart2(getTestData(), 1068781)) {
            System.out.println("Part, Part 2 Success");
        }

        System.out.println("Bus ID multiplied by time to wait: " + partOne.getBusIdMulByTimeToWait());
        System.out.println("The earliest timestamp that matches the list is: " + getValueForPart2(partOne));
    }

    private static BusSchedule getData() {

        BusSchedule schedule = new BusSchedule();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay13.txt");
            Scanner myReader = new Scanner(myObj);
            int lineNumber = 1;
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (lineNumber == 1) {
                    schedule.setStartTimestamp(Integer.parseInt(str));
                } else {
                    schedule.setAvaiableBusses(getListOfAvaiableBusses(str));
                    schedule.setRawIds(Arrays.asList(str.split(",")));
                }
                lineNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return schedule;
    }

    private static BusSchedule getTestData() {

        BusSchedule schedule = new BusSchedule();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay13test.txt");
            Scanner myReader = new Scanner(myObj);
            int lineNumber = 1;
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (lineNumber == 1) {
                    schedule.setStartTimestamp(Integer.parseInt(str));
                } else {
                    schedule.setAvaiableBusses(getListOfAvaiableBusses(str));
                    schedule.setRawIds(Arrays.asList(str.split(",")));
                }
                lineNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return schedule;
    }

    public static long getValueForPart2(BusSchedule busSchedule) {
        List<Integer> busLines = busSchedule.getAvaiableBusses();

        Map<Integer, Integer> idOffsetMap = createOffsetMap(busSchedule.getRawIds());
        List<Long> lcmNumberList = new ArrayList<>();
        lcmNumberList.add((long) busLines.get(0));


        return findTimestamp(busLines, idOffsetMap, busLines.get(0), busLines.get(0), 1, lcmNumberList, idOffsetMap.get(busLines.get(1)));
    }

    private static long findTimestamp(List<Integer> busLines, Map<Integer, Integer> idOffsetMap, long startNum, long jumpValue,
                                      int index, List<Long> lcmNumberList, int totalOffset) {
        if (index >= busLines.size()) {
            return startNum;
        }

        long cmpNum = busLines.get(index);

        while ((startNum + totalOffset) % cmpNum != 0) {
            startNum += jumpValue;
        }

        lcmNumberList.add(cmpNum);
        jumpValue = leastCommonMultiple(lcmNumberList);

        if (index + 1 >= busLines.size())
            return startNum;

        return findTimestamp(busLines, idOffsetMap, startNum, jumpValue, index + 1, lcmNumberList,
                totalOffset + idOffsetMap.get(busLines.get(index + 1)));
    }

    private static Map<Integer, Integer> createOffsetMap(List<String> rawIds) {
        Map<Integer, Integer> idOffset = new HashMap<>();

        int offset = 1;
        for (int i = 1; i < rawIds.size(); i++) {
            if (rawIds.get(i).equals("x")) {
                offset++;
            } else {
                idOffset.put(Integer.parseInt(rawIds.get(i)), offset);
                offset = 1;
            }
        }

        return idOffset;
    }

    public static long leastCommonMultiple(List<Long> n) {
        if (n.size() < 2) {
            return -1;
        }

        long res = leastCommonMultiple(n.get(0), n.get(1));
        for (Long temp : n) {
            res = leastCommonMultiple(res, temp);
        }
        return res;
    }

    public static long leastCommonMultiple(long a, long b) {

        return (a / greatestCommonDivisor(a, b)) * b;
    }

    public static long greatestCommonDivisor(long a, long b) {
        if (b == 0) {
            return a;
        }
        return greatestCommonDivisor(b, a % b);
    }

    private static List<Integer> getListOfAvaiableBusses(String inpupt) {
        List<Integer> resultList = new ArrayList<>();
        String[] dataDividied = inpupt.split(",");
        for (String temp : dataDividied) {
            if (!"x".equals(temp)) {
                resultList.add(Integer.parseInt(temp));
            }
        }
        return resultList;
    }

    private static boolean makeTest(BusSchedule schedule, int testValue) {
        Pair<Integer, Integer> resultDataSet = schedule.getEarliestLeavingBus(schedule.startTimestamp);
        long result = resultDataSet.getKey() * resultDataSet.getValue();
        return result == testValue;
    }

    private static boolean makeTestForPart2(BusSchedule schedule, int testValue) {
        return getValueForPart2(schedule) == testValue;
    }

}
