package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfValid;
        List<Policy> dataList = getData();
        //numberOfValid = (int) dataList.stream().filter(Policy::isValid).count();
        numberOfValid = (int) dataList.stream().filter(Policy::isValidWithMoreRestrictions).count();
        System.out.println(numberOfValid);

    }

    private static List<Policy> getData() {

        List<Policy> dataList = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String[] arrOfStr = str.split(" ", 3);
                String[] arrOfNumber = arrOfStr[0].split("-", 2);
                Policy temp = new Policy(Integer.parseInt(arrOfNumber[0]), Integer.parseInt(arrOfNumber[1]), arrOfStr[1].replace(":", ""), arrOfStr[2]);
                dataList.add(temp);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return dataList;
    }
}
