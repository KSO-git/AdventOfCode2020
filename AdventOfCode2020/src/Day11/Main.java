package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        if (makeTest(getTestData(), 37)) {
            System.out.println("Test Part 1: Success");
        }

        if (makeTestPartTwo(getTestData(), 26)) {
            System.out.println("Test Part 2: Success");
        }

        System.out.println("Number of seats taken after all movement is: " + getNumberOfTakenAfterAllMovents(getData()));
        System.out.println("Number of seats taken after all movement with rules from part 2 is: " + getNumberOfTakenAfterAllMoventsPartTwo(getData()));

    }

    private static PlaneSeats getData() {

        List<OneRow> singleRow = new ArrayList<>();
        int lengthOfPlane = 0;
        try {
            File myObj = new File("AdventOfCode2020/inputDay11.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                List<Character> charListOfRow = new ArrayList<>();
                for (char temp : str.toCharArray()) {
                    charListOfRow.add(temp);
                }
                OneRow single = new OneRow(mapCharacterToChar(charListOfRow));
                singleRow.add(single);
                lengthOfPlane++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int widthOfPlane = singleRow.get(0).getRowSituation().length;


        return getPlaneSeatsMap(singleRow, widthOfPlane, lengthOfPlane);
    }

    private static PlaneSeats getTestData() {

        List<OneRow> singleRow = new ArrayList<>();
        int lengthOfPlane = 0;
        try {
            File myObj = new File("AdventOfCode2020/inputDay11test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                List<Character> charListOfRow = new ArrayList<>();
                for (char temp : str.toCharArray()) {
                    charListOfRow.add(temp);
                }
                OneRow single = new OneRow(mapCharacterToChar(charListOfRow));
                singleRow.add(single);
                lengthOfPlane++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int widthOfPlane = singleRow.get(0).getRowSituation().length;


        return getPlaneSeatsMap(singleRow, widthOfPlane, lengthOfPlane);
    }

    private static char[] mapCharacterToChar(List<Character> characters) {
        char[] chars = new char[characters.size()];
        int i = 0;
        for (Character c : characters) {
            chars[i++] = c;
        }
        return chars;
    }

    private static PlaneSeats getPlaneSeatsMap(List<OneRow> singleRow, int width, int length) {
        char[][] seatMap = new char[length][width];
        int currentRowNumber = 0;
        for (OneRow temp : singleRow) {
            for (int i = 0; i < temp.getRowSituation().length; i++) {
                seatMap[currentRowNumber][i] = temp.getRowSituation()[i];
            }
            currentRowNumber++;
        }
        return new PlaneSeats(seatMap, width, length);
    }

    public static void printSeats(PlaneSeats seats) {
        char[][] seatsCharArray = seats.getSeatSituationInPlane();

        for (int i = 0; i < seats.getLength(); i++) {
            for (int j = 0; j < seats.getWidth(); j++) {
                System.out.print(seatsCharArray[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean makeTest(PlaneSeats testData, int i) {
        return getNumberOfTakenAfterAllMovents(testData) == i;
    }

    private static boolean makeTestPartTwo(PlaneSeats testData, int i) {
        return getNumberOfTakenAfterAllMoventsPartTwo(testData) == i;
    }

    private static int getNumberOfTakenAfterAllMovents(PlaneSeats data) {
        int takenSeats;
        PlaneSeats testPlaneNew = data.newArrangemnt();
        do {
            takenSeats = testPlaneNew.getTaken();
            testPlaneNew = testPlaneNew.newArrangemnt();
        } while (takenSeats != testPlaneNew.getTaken());
        return takenSeats;
    }

    private static int getNumberOfTakenAfterAllMoventsPartTwo(PlaneSeats data) {
        int takenSeats;
        PlaneSeats testPlaneNew = data.newArrangemntPartTwo();
        do {
            takenSeats = testPlaneNew.getTaken();
            testPlaneNew = testPlaneNew.newArrangemntPartTwo();
        } while (takenSeats != testPlaneNew.getTaken());
        return takenSeats;
    }
}
