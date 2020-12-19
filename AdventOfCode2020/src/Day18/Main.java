package Day18;

import Day17.Cube;
import Day17.Hypercube;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Equation> testData = getTestData();
        List<Equation> equationList = getData();
        if (isTestPart1Ok(testData, 437)) {
            System.out.println("Test for part 1 - Successful");
        }
        if (isTestPart2Ok(testData, 1445)) {
            System.out.println("Test for part 2 - Successful");
        }

        long total = getTotalForPart1(equationList);
        long total2 = getTotalForPart2(equationList);
        System.out.println("Result for part 1: " + total);
        System.out.println("Result for Part 2: " + total2);
    }

    private static long getTotalForPart2(List<Equation> equationList) {
        long total2 = 0;
        for (Equation entry : equationList) {
            total2 += getValueFromEquasionForPart2(entry.getValuesArray(), 0).getKey();
        }
        return total2;
    }

    private static long getTotalForPart1(List<Equation> equationList) {
        long total = 0;
        for (Equation entry : equationList) {
            total += getValueFromEquasion(entry.getValuesArray(), 0).getKey();
        }
        return total;
    }

    private static boolean isTestPart1Ok(List<Equation> testData, int testValue) {
        long totalTest = getTotalForPart1(testData);
        return totalTest == testValue;
    }

    private static boolean isTestPart2Ok(List<Equation> testData, int testValue) {
        long totalTest = getTotalForPart2(testData);
        return totalTest == testValue;
    }

    private static List<Equation> getData() {

        List<Equation> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay18.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                str = str.replace("(", "( ");
                str = str.replace(")", " )");
                String[] a = str.split(" ");
                result.add(new Equation(a));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    private static List<Equation> getTestData() {

        List<Equation> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay18test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                str = str.replace("(", "( ");
                str = str.replace(")", " )");
                String[] a = str.split(" ");
                result.add(new Equation(a));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    private static Pair<Long, Integer> getValueFromEquasion(String[] stringArray, int startingPoint) {
        Pair<Long, Integer> result = new Pair<>(0L, startingPoint);
        long resultTemp = 0;
        String prevousOperation = "";
        for (int i = startingPoint; i < stringArray.length; i++) {
            result = new Pair<>(resultTemp, i);
            if (")".equals(stringArray[i])) {
                break;
            }
            if ("(".equals(stringArray[i])) {
                Pair<Long, Integer> pairResult = getValueFromEquasion(stringArray, i + 1);
                if ("+".equals(prevousOperation)) {
                    resultTemp = resultTemp + pairResult.getKey();
                } else if ("*".equals(prevousOperation)) {
                    resultTemp = resultTemp * pairResult.getKey();
                } else {
                    resultTemp = pairResult.getKey();
                }
                if (pairResult.getValue() > i) {
                    i = pairResult.getValue();
                }
            } else {
                if ("+".equals(stringArray[i]) || "*".equals(stringArray[i])) {
                    prevousOperation = stringArray[i];
                } else {
                    if ("+".equals(prevousOperation)) {
                        resultTemp = resultTemp + Long.parseLong(stringArray[i]);
                    } else if ("*".equals(prevousOperation)) {
                        resultTemp = resultTemp * Long.parseLong(stringArray[i]);
                    } else {
                        resultTemp = Long.parseLong(stringArray[i]);
                    }
                }
            }
            result = new Pair<>(resultTemp, i);
        }
        return result;
    }

    private static Pair<Long, Integer> getValueFromEquasionForPart2(String[] stringArray, int startingPoint) {
        Pair<Long, Integer> result = new Pair<>(0L, startingPoint);
        List<Long> addAndPass = new ArrayList<>();
        String prevousOperation = "";
        for (int i = startingPoint; i < stringArray.length; i++) {
            if (")".equals(stringArray[i])) {
                break;
            }
            if ("(".equals(stringArray[i])) {
                Pair<Long, Integer> pairResult = getValueFromEquasionForPart2(stringArray, i + 1);
                if ("+".equals(prevousOperation)) {
                    long last = addAndPass.get(addAndPass.size() - 1);
                    addAndPass.remove(last);
                    addAndPass.add(last + pairResult.getKey());
                } else {
                    addAndPass.add(pairResult.getKey());
                }
                if (pairResult.getValue() > i) {
                    i = pairResult.getValue() + 1;
                }
            } else {
                if ("+".equals(stringArray[i]) || "*".equals(stringArray[i])) {
                    prevousOperation = stringArray[i];
                } else {
                    if ("+".equals(prevousOperation)) {
                        long last = addAndPass.get(addAndPass.size() - 1);
                        addAndPass.remove(last);
                        addAndPass.add(last + Long.parseLong(stringArray[i]));
                    } else {
                        addAndPass.add(Long.parseLong(stringArray[i]));
                    }
                }
            }
            if (!addAndPass.isEmpty()) {
                result = new Pair<>(addAndPass.stream().reduce((a, b) -> a * b).get(), i);
            }
        }
        return result;
    }
}
