package Day12;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String[] moveListArray = {"N", "S", "E", "W"};
    static List<String> moveList = Arrays.asList(moveListArray);
    static String[] turnListArray = {"R", "L"};
    static List<String> turnList = Arrays.asList(turnListArray);
    static String[] forwardListArray = {"F"};
    static List<String> forwardList = Arrays.asList(forwardListArray);

    public static void main(String[] args) {

        if (makeTest(getTestData(), 25)) {
            System.out.println("Test 1 passed");
        }
        if (makeTest(getTestDataForPartTwo(), 286)) {
            System.out.println("Test 2 passed");
        }

        System.out.println("Manhatian Distance for Part 1: " + getData().getManthatianDistance());
        System.out.println("Manhatian Distance for Part 2: " + getDataForPartTwo().getManthatianDistance());

    }

    private static Turtle getData() {

        Turtle turtle = new Turtle();
        try {
            File myObj = new File("AdventOfCode2020/inputDay12.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                Pair<String, Integer> dataPair = readLineEntry(str);
                if (moveList.contains(dataPair.getKey())) {
                    turtle.makeMove(dataPair.getKey(), dataPair.getValue());
                } else if (turnList.contains(dataPair.getKey())) {
                    turtle.makeTurn(dataPair.getKey(), dataPair.getValue());
                } else if (forwardList.contains(dataPair.getKey())) {
                    turtle.moveForward(dataPair.getValue());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return turtle;
    }

    private static Turtle getDataForPartTwo() {

        Turtle turtle = new Turtle();
        try {
            File myObj = new File("AdventOfCode2020/inputDay12.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                Pair<String, Integer> dataPair = readLineEntry(str);
                if (moveList.contains(dataPair.getKey())) {
                    turtle.makeMoveOnWaypoint(dataPair.getKey(), dataPair.getValue());
                } else if (turnList.contains(dataPair.getKey())) {
                    turtle.makeTurnOnWaypoint(dataPair.getKey(), dataPair.getValue());
                } else if (forwardList.contains(dataPair.getKey())) {
                    turtle.moveForwardToWaypoint(dataPair.getValue());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return turtle;
    }

    private static Turtle getTestDataForPartTwo() {

        Turtle turtle = new Turtle();
        try {
            File myObj = new File("AdventOfCode2020/inputDay12test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                Pair<String, Integer> dataPair = readLineEntry(str);
                if (moveList.contains(dataPair.getKey())) {
                    turtle.makeMoveOnWaypoint(dataPair.getKey(), dataPair.getValue());
                } else if (turnList.contains(dataPair.getKey())) {
                    turtle.makeTurnOnWaypoint(dataPair.getKey(), dataPair.getValue());
                } else if (forwardList.contains(dataPair.getKey())) {
                    turtle.moveForwardToWaypoint(dataPair.getValue());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return turtle;
    }

    private static Turtle getTestData() {

        Turtle turtle = new Turtle();
        try {
            File myObj = new File("AdventOfCode2020/inputDay12test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                Pair<String, Integer> dataPair = readLineEntry(str);
                if (moveList.contains(dataPair.getKey())) {
                    turtle.makeMove(dataPair.getKey(), dataPair.getValue());
                } else if (turnList.contains(dataPair.getKey())) {
                    turtle.makeTurn(dataPair.getKey(), dataPair.getValue());
                } else if (forwardList.contains(dataPair.getKey())) {
                    turtle.moveForward(dataPair.getValue());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return turtle;
    }

    private static Pair<String, Integer> readLineEntry(String inputLine) {
        String direction = inputLine.substring(0, 1);
        int value = Integer.parseInt(inputLine.substring(1));
        return new Pair<>(direction, value);
    }

    private static boolean makeTest(Turtle turtle, int testValue) {
        return turtle.getManthatianDistance() == testValue;
    }

}
