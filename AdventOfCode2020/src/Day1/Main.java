package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static List<Integer> numberList = new ArrayList<>();
    private final static Integer VALUE_SEARCHED_FOR = 2020;

    public static void main(String[] args) {
        getDataFromFile();
        //get2Numbers(VALUE_SEARCHED_FOR);
        get3Numbers();


    }

    private static void getDataFromFile() {
        try {
            File myObj = new File("AdventOfCode2020/inputDay1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                numberList.add(Integer.parseInt(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void get2Numbers(Integer searchedFor) {
        numberList.forEach((number) -> {
            Integer substrationNumber = searchedFor - number;
            Optional<Integer> missingValue = numberList.stream().filter(integer -> integer.equals(substrationNumber)).findAny();
            missingValue.ifPresent(integer -> System.out.println(number * integer));
        });
    }

    private static void get3Numbers() {
        int sumFirst;
        int sumSecond;
        Integer[] integerArray = new Integer[numberList.size()];
        numberList.toArray(integerArray);
        for (int i = 0; i < numberList.size(); i++) {
            sumFirst = integerArray[i];
            for (int j = i + 1; j < numberList.size(); j++) {
                sumSecond = integerArray[j];
                Integer substrationNumber = VALUE_SEARCHED_FOR - (sumFirst + sumSecond);
                Optional<Integer> missingValue = numberList.stream().filter(integer -> integer.equals(substrationNumber)).findAny();
                int finalSumFirst = sumFirst;
                int finalSumSecond = sumSecond;
                missingValue.ifPresent(integer -> System.out.println(finalSumFirst * finalSumSecond * integer));
            }
        }
    }
}
