package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static int slopeHeight = 0;

    public static void main(String[] args) {
        List<SloepLevel> dataList = getData();
        List<Integer> finalList = new ArrayList<>();
        finalList.add(findNumberOfTreesHit(1, 1, dataList));
        finalList.add(findNumberOfTreesHit(3, 1, dataList));
        finalList.add(findNumberOfTreesHit(5, 1, dataList));
        finalList.add(findNumberOfTreesHit(7, 1, dataList));
        finalList.add(findNumberOfTreesHit(1, 2, dataList));
        long finalNumer = 1;
        for (int temp : finalList) {
            finalNumer *= temp;
        }
        System.out.println(finalNumer);

    }

    private static int findNumberOfTreesHit(int posXChange, int posYChange, List<SloepLevel> dataList) {
        int numberOfTrees = 0;
        int posX = 1;
        int posY;
        for (posY = 1; posY < slopeHeight; posY += posYChange) {
            int currentLevel = posY;
            Optional<SloepLevel> optSlope = dataList.stream().filter(data -> data.getLevel() == currentLevel).findAny();
            if (optSlope.isPresent() && optSlope.get().isTreePresent(posX)) {
                numberOfTrees++;
            }
            posX += posXChange;
        }
        return numberOfTrees;
    }

    private static List<SloepLevel> getData() {

        List<SloepLevel> dataList = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay3.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 1;
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                dataList.add(getSlopeObject(i, str));
                i++;
            }
            slopeHeight = i;
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return dataList;
    }

    private static SloepLevel getSlopeObject(int level, String input) {
        SloepLevel result = new SloepLevel();
        List<Integer> trees = new ArrayList<>();
        result.setMaxSlopeWidth(input.length());
        result.setLevel(level);
        for (int i = 0; i < input.length(); i++) {
            if ('#' == (input.charAt(i))) {
                trees.add(i + 1);
            }
        }
        result.setTreesPosions(trees);
        return result;
    }
}
