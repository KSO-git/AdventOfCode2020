package Day10;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        double allPossibleSets = getNumberOfAllPossibleSetsForDataset(getData());
        boolean isTestCorrect = makeTest(getTestData(), 19208);

        if (isTestCorrect) {
            System.out.println("Test Passed!");
        }

        System.out.println("Result of multiplication of 1s and 3s: " + getNumberOfOnesTimesNumberOfThrees(getData()));
        System.out.printf("Number of all possible sets from dataset" + ": %.0f\n", allPossibleSets);

    }

    private static int getNumberOfOnesTimesNumberOfThrees(List<Integer> dataset) {
        List<Integer> datasetSorted = dataset.stream().sorted().collect(Collectors.toList());
        int startingJolt = 0;
        int numberOfOnes = 0;
        int numberOfTwos = 0;
        int numberOfThrees = 0;
        for (Integer temp : datasetSorted) {
            int currentJolt = temp - startingJolt;
            if (currentJolt <= 3) {
                switch (currentJolt) {
                    case 1:
                        numberOfOnes++;
                        break;
                    case 2:
                        numberOfTwos++;
                        break;
                    case 3:
                        numberOfThrees++;
                        break;
                }
                startingJolt = temp;
            }
        }
        numberOfThrees++;

        return numberOfOnes * numberOfThrees;
    }

    private static double getNumberOfAllPossibleSetsForDataset(List<Integer> dataset) {
        List<Integer> datasetSorted = dataset.stream().sorted().collect(Collectors.toList());
        int startingJolt = 0;
        int consecutiveOnes = 0;
        double result = 1;
        List<Integer> listOfConcurentPlugs = new ArrayList<>();
        List<Pair<Integer, Integer>> a = new ArrayList<>();
        for (Integer temp : datasetSorted) {
            int currentJolt = temp - startingJolt;
            if (currentJolt <= 3) {
                switch (currentJolt) {
                    case 1:
                        consecutiveOnes++;
                        break;
                    case 2:
                        break;
                    case 3:
                        if (consecutiveOnes > 1) {
                            if (consecutiveOnes <= 3) {
                                result = result * Math.pow(2, consecutiveOnes - 1);
                            } else {
                                result = result * (Math.pow(2, consecutiveOnes - 1) - 1);
                            }
                        }
                        consecutiveOnes = 0;
                        break;
                }
                startingJolt = temp;
            }
            listOfConcurentPlugs.add(currentJolt);
        }
        if (consecutiveOnes > 1) {
            if (consecutiveOnes <= 3) {
                result = result * Math.pow(2, consecutiveOnes - 1);
            } else {
                result = result * (Math.pow(2, consecutiveOnes - 1) - 1);
            }
        }

        return result;
    }

    private static List<Integer> getData() {

        List<Integer> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay10.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                result.add(Integer.parseInt(str));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

    private static List<Integer> getTestData() {

        List<Integer> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay10test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                result.add(Integer.parseInt(str));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

    private static boolean makeTest(List<Integer> testData, int i) {
        return getNumberOfAllPossibleSetsForDataset(testData) == i;
    }
}
