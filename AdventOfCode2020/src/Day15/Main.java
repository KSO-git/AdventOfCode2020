package Day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        int position = 2020;
        int positionPart2 = 30000000;
        List<Turn> dataFromFile = getData();

        if (makeTest(getAllTurnsTillTurn(getTestData("1"), 2020), 2020, 436)) {
            System.out.println("Test 1 passed");
        }
        if (makeTest(getAllTurnsTillTurn(getTestData("2"), 2020), 2020, 1)) {
            System.out.println("Test 2 passed");
        }
        if (makeTest(getAllTurnsTillTurn(getTestData("3"), 2020), 2020, 10)) {
            System.out.println("Test 3 passed");
        }
        if (makeTestPart2(valueOnPositionForPart2(getTestData("1"), 30000000), 175594)) {
            System.out.println("Test 1 Part 2 passed");
        }
        if (makeTestPart2(valueOnPositionForPart2(getTestData("2"), 30000000), 2578)) {
            System.out.println("Test 2 Part 2 passed");
        }
        if (makeTestPart2(valueOnPositionForPart2(getTestData("3"), 30000000), 3544142)) {
            System.out.println("Test 3 Part 2 passed");
        }
        int valueOnPosition = valueOnPosition(dataFromFile, position);
        long valueOnPositionPart2 = valueOnPositionForPart2(dataFromFile, positionPart2);

        System.out.println("Value on turn " + position + " : " + valueOnPosition);
        System.out.println("Value on turn " + positionPart2 + " : " + valueOnPositionPart2);

    }

    private static int valueOnPosition(List<Turn> dataFromFile, int position) {
        List<Turn> followinTurns = getAllTurnsTillTurn(dataFromFile, position);
        return followinTurns.get(position - 1).getValue();
    }

    private static long valueOnPositionForPart2(List<Turn> dataFromFile, int position) {
        List<Long> listOfValues = dataFromFile.stream()
                .mapToLong(Turn::getValue)
                .boxed()
                .collect(Collectors.toList());
        return getAllTurnsTillTurnForPart2(listOfValues, position);
    }

    private static List<Turn> getData() {

        List<Turn> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay15.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String[] startingValues = str.split(",");
                for (int i = 0; i < startingValues.length; i++) {
                    Turn turn = new Turn(i, Integer.parseInt(startingValues[i]));
                    result.add(turn);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    private static List<Turn> getTestData(String testSetNumber) {

        List<Turn> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay15test" + testSetNumber + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String[] startingValues = str.split(",");
                for (int i = 0; i < startingValues.length; i++) {
                    Turn turn = new Turn(i, Integer.parseInt(startingValues[i]));
                    result.add(turn);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

    private static List<Turn> getAllTurnsTillTurn(List<Turn> statringTurns, int turnNumber) {
        if (statringTurns.size() >= turnNumber) {
            return statringTurns;
        }
        List<Turn> listOfTurns = statringTurns;
        int lastTurnNumber = statringTurns.get(statringTurns.size() - 1).getValue();
        long numberOfOccurences = statringTurns.stream().mapToInt(Turn::getValue).filter(turnValue -> turnValue == lastTurnNumber).count();
        if (numberOfOccurences > 1) {
            List<Turn> allTurnsThathadTheSameValue = statringTurns.stream().filter(turnValue -> turnValue.getValue() == lastTurnNumber).collect(Collectors.toList());
            int lastValueIndex = allTurnsThathadTheSameValue.get(allTurnsThathadTheSameValue.size() - 1).getTurnNumber();
            int secondLastValueIndex = allTurnsThathadTheSameValue.get(allTurnsThathadTheSameValue.size() - 2).getTurnNumber();

            listOfTurns.add(new Turn(listOfTurns.size(), lastValueIndex - secondLastValueIndex));
        } else {
            listOfTurns.add(new Turn(listOfTurns.size(), 0));
        }
        return getAllTurnsTillTurn(listOfTurns, turnNumber);
    }

    private static long getAllTurnsTillTurnForPart2(List<Long> statringTurns, int turnNumber) {
        final HashMap<Long, Long> map = new HashMap<>();

        for (long i = 0; i < statringTurns.size() - 1; i++) {
            map.put(statringTurns.get((int) i), i);
        }

        long value = statringTurns.get(statringTurns.size()-1);
        for (long i = statringTurns.size()-1; i < turnNumber - 1; i++) {
            final Long prev = map.get(value);
            map.put(value, i);
            if (prev == null) {
                value = 0;
            } else {
                value = i - prev;
            }
        }

        return value;
    }

    private static boolean makeTest(List<Turn> listOfTurns, int turnNumber, int testValue) {
        return listOfTurns.get(turnNumber - 1).getValue() == testValue;
    }

    private static boolean makeTestPart2(long value, int testValue) {
        return value == testValue;
    }

}
