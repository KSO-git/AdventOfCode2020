package Day6;

import Day5.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<GroupAnswers> dataset = getData();
        long answersYes = dataset.stream().mapToLong(groupAnswers -> groupAnswers.getListOfYesAnswers().keySet().size()).sum();
        int allGroupMembersAnsweredYes = dataset.stream().mapToInt(GroupAnswers::numerOfPeopleInGroupThatMadeTheSameAnswer).sum();


        System.out.println("given numeber of yes answers from all groups: " + answersYes);
        System.out.println("Number where all people said yes within grooup: " + allGroupMembersAnsweredYes);

    }

    private static List<GroupAnswers> getData() {

        List<GroupAnswers> dataList = new ArrayList<>();
        List<String> listOfAnswers = new ArrayList<>();
        GroupAnswers groupAnswer;
        int numberOfPeopleInGroup = 0;
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay6.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (str.isEmpty()) {
                    groupAnswer = new GroupAnswers(listOfAnswers, numberOfPeopleInGroup);
                    dataList.add(groupAnswer);
                    listOfAnswers.clear();
                    numberOfPeopleInGroup = 0;
                } else {
                    for (int i = 0; i < str.length(); i++) {
                        listOfAnswers.add(Character.toString(str.charAt(i)));
                    }
                    numberOfPeopleInGroup++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        groupAnswer = new GroupAnswers(listOfAnswers, numberOfPeopleInGroup);
        dataList.add(groupAnswer);
        return dataList;
    }

}
