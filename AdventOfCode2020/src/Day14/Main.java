package Day14;

import Day12.Turtle;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Main {
    final static String MASK = "mask";

    public static void main(String[] args) {

        if (makeTest(getTestData(), 165)) {
            System.out.println("Test 1 passed");
        }
        if (makeTest(getTestDataForPart2(), 208)) {
            System.out.println("Test 2 passed");
        }
        //getTestDataForPart2();

        System.out.println("Sum of values in memory entries: " + getSumOfValuesInMemory(getData()));
        System.out.println("Sum of values in memory entries for part 2: " + getSumOfValuesInMemory(getDataForPart2()));

    }

    private static List<DataEntry> getData() {

        List<DataEntry> dataEntryList = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay14.txt");
            Scanner myReader = new Scanner(myObj);
            DataEntry dataEntry = new DataEntry();
            Map<Integer, Integer> mapMemoryValue = new HashMap<>();
            Map<Long, Long> mapMemoryValueAfterMask = new HashMap<>();
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (isNewEntryStart(str)) {
                    if (!dataEntryList.isEmpty()) {
                        dataEntry.setMemoryValueMap(mapMemoryValueAfterMask);
                        dataEntry.setMemoryOriginalValue(mapMemoryValue);
                        mapMemoryValue = new HashMap<>();
                        mapMemoryValueAfterMask = new HashMap<>();
                    }
                    dataEntryList.add(dataEntry);
                    dataEntry = new DataEntry();
                    dataEntry.setMask(readMask(str));
                } else {
                    Pair<Integer, Integer> pairOfValues = readDataEntryFromInput(str);
                    mapMemoryValue.put(pairOfValues.getKey(), pairOfValues.getValue());
                    String binary = StaticUtils.changeDecimalToBinary(pairOfValues.getValue());
                    String appliedMask = StaticUtils.applyMaskToBinary(dataEntry.getMask(), binary);
                    mapMemoryValueAfterMask.put(pairOfValues.getKey().longValue(), StaticUtils.getValueFromBinary(appliedMask));
                }
            }
            myReader.close();
            dataEntry.setMemoryValueMap(mapMemoryValueAfterMask);
            dataEntry.setMemoryOriginalValue(mapMemoryValue);
            dataEntryList.add(dataEntry);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        dataEntryList = dataEntryList.stream().filter(entry -> entry.getMask() != null).collect(Collectors.toList());

        return dataEntryList;
    }

    private static List<DataEntry> getDataForPart2() {

        List<DataEntry> dataEntryList = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay14.txt");
            Scanner myReader = new Scanner(myObj);
            DataEntry dataEntry = new DataEntry();
            Map<Integer, Integer> mapMemoryValue = new HashMap<>();
            Map<Long, Long> mapMemoryValueAfterMask = new HashMap<>();
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (isNewEntryStart(str)) {
                    if (!dataEntryList.isEmpty()) {
                        dataEntry.setMemoryValueMap(mapMemoryValueAfterMask);
                        dataEntry.setMemoryOriginalValue(mapMemoryValue);
                        mapMemoryValue = new HashMap<>();
                        mapMemoryValueAfterMask = new HashMap<>();
                    }
                    dataEntryList.add(dataEntry);
                    dataEntry = new DataEntry();
                    dataEntry.setMask(readMask(str));
                } else {
                    Pair<Integer, Integer> pairOfValues = readDataEntryFromInput(str);
                    mapMemoryValue.put(pairOfValues.getKey(), pairOfValues.getValue());
                    String binary = StaticUtils.changeDecimalToBinary(pairOfValues.getKey());
                    String appliedMask = StaticUtils.applyMaskToBinaryAdress(dataEntry.getMask(), binary);
                    List<Long> keysList = StaticUtils.getKeyList(appliedMask);
                    for (Long temp : keysList) {
                        mapMemoryValueAfterMask.put(temp, pairOfValues.getValue().longValue());
                    }
                }
            }
            myReader.close();
            dataEntry.setMemoryValueMap(mapMemoryValueAfterMask);
            dataEntry.setMemoryOriginalValue(mapMemoryValue);
            dataEntryList.add(dataEntry);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        dataEntryList = dataEntryList.stream().filter(entry -> entry.getMask() != null).collect(Collectors.toList());

        return dataEntryList;
    }


    private static List<DataEntry> getTestData() {

        List<DataEntry> dataEntryList = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay14test.txt");
            Scanner myReader = new Scanner(myObj);
            DataEntry dataEntry = new DataEntry();
            Map<Integer, Integer> mapMemoryValue = new HashMap<>();
            Map<Long, Long> mapMemoryValueAfterMask = new HashMap<>();
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (isNewEntryStart(str)) {
                    if (!dataEntryList.isEmpty()) {
                        dataEntry.setMemoryValueMap(mapMemoryValueAfterMask);
                        dataEntry.setMemoryOriginalValue(mapMemoryValue);
                        mapMemoryValue = new HashMap<>();
                        mapMemoryValueAfterMask = new HashMap<>();
                    }
                    dataEntryList.add(dataEntry);
                    dataEntry = new DataEntry();
                    dataEntry.setMask(readMask(str));
                } else {
                    Pair<Integer, Integer> pairOfValues = readDataEntryFromInput(str);
                    mapMemoryValue.put(pairOfValues.getKey(), pairOfValues.getValue());
                    String binary = StaticUtils.changeDecimalToBinary(pairOfValues.getValue());
                    String appliedMask = StaticUtils.applyMaskToBinary(dataEntry.getMask(), binary);
                    mapMemoryValueAfterMask.put(pairOfValues.getKey().longValue(), StaticUtils.getValueFromBinary(appliedMask));
                }
            }
            myReader.close();
            dataEntry.setMemoryValueMap(mapMemoryValueAfterMask);
            dataEntry.setMemoryOriginalValue(mapMemoryValue);
            dataEntryList.add(dataEntry);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        dataEntryList = dataEntryList.stream().filter(entry -> entry.getMask() != null).collect(Collectors.toList());

        return dataEntryList;
    }

    private static List<DataEntry> getTestDataForPart2() {

        List<DataEntry> dataEntryList = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay14testPart2.txt");
            Scanner myReader = new Scanner(myObj);
            DataEntry dataEntry = new DataEntry();
            Map<Integer, Integer> mapMemoryValue = new HashMap<>();
            Map<Long, Long> mapMemoryValueAfterMask = new HashMap<>();
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (isNewEntryStart(str)) {
                    if (!dataEntryList.isEmpty()) {
                        dataEntry.setMemoryValueMap(mapMemoryValueAfterMask);
                        dataEntry.setMemoryOriginalValue(mapMemoryValue);
                        mapMemoryValue = new HashMap<>();
                        mapMemoryValueAfterMask = new HashMap<>();
                    }
                    dataEntryList.add(dataEntry);
                    dataEntry = new DataEntry();
                    dataEntry.setMask(readMask(str));
                } else {
                    Pair<Integer, Integer> pairOfValues = readDataEntryFromInput(str);
                    mapMemoryValue.put(pairOfValues.getKey(), pairOfValues.getValue());
                    String binary = StaticUtils.changeDecimalToBinary(pairOfValues.getKey());
                    String appliedMask = StaticUtils.applyMaskToBinaryAdress(dataEntry.getMask(), binary);
                    List<Long> keysList = StaticUtils.getKeyList(appliedMask);
                    for (Long temp : keysList) {
                        mapMemoryValueAfterMask.put(temp, pairOfValues.getValue().longValue());
                    }
                }
            }
            myReader.close();
            dataEntry.setMemoryValueMap(mapMemoryValueAfterMask);
            dataEntry.setMemoryOriginalValue(mapMemoryValue);
            dataEntryList.add(dataEntry);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        dataEntryList = dataEntryList.stream().filter(entry -> entry.getMask() != null).collect(Collectors.toList());

        return dataEntryList;
    }

    private static String readMask(String inputLine) {
        String[] inputData = inputLine.split("=");
        return inputData[1].trim();
    }

    private static Pair<Integer, Integer> readDataEntryFromInput(String inputLine) {
        String[] dataInputArray = inputLine.split("=");
        Integer memoryBlock = Integer.parseInt(StringUtils.substringBetween(dataInputArray[0].trim(), "[", "]"));
        Integer rawValue = Integer.parseInt(dataInputArray[1].trim());
        return new Pair<>(memoryBlock, rawValue);
    }

    private static boolean isNewEntryStart(String inputLine) {
        return MASK.equals(inputLine.substring(0, 4));
    }

    private static long getSumOfValuesInMemory(List<DataEntry> dataEntries) {
        Map<Long, Long> resultMap = new HashMap<>();
        for (DataEntry temp : dataEntries) {
            for (Map.Entry<Long, Long> entry : temp.getMemoryValueMap().entrySet()) {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resultMap.values().stream().mapToLong(k -> k).sum();
    }

    private static boolean makeTest(List<DataEntry> dataEntries, int testValue) {
        return getSumOfValuesInMemory(dataEntries) == testValue;
    }

}
