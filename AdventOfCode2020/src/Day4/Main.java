package Day4;

import Day3.SloepLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Passport> passportList = getData();
        long numberOfCorrectPassports = passportList.stream().filter(Passport::isValidWithOptional).count();
        long numberOfCorrectCorrectPassports = passportList.stream().filter(Passport::isValidWithOptional).filter(Passport::areAllFieldsValid).count();
        System.out.println(numberOfCorrectPassports);
        System.out.println(numberOfCorrectCorrectPassports);
    }

    private static List<Passport> getData() {

        List<Passport> dataList = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay4.txt");
            Scanner myReader = new Scanner(myObj);
            Passport object = new Passport();
            Map<String, String> hashMap = new HashMap<>();
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if(str.trim().isEmpty()){
                    object.setPassportFields(hashMap);
                    dataList.add(object);
                    object = new Passport();
                    hashMap = new HashMap<>();
                } else {
                    String[] parts = str.split(" ");
                    for (String temp : parts) {
                        String[] keyValue = temp.split(":");
                        hashMap.put(keyValue[0], keyValue[1]);
                    }
                }
            }
            object.setPassportFields(hashMap);
            dataList.add(object);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return dataList;
    }
}
