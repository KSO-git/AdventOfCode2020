package Day7;

import Day6.GroupAnswers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Bag> dataset = getData();
        BagProperties bagILookFor = new BagProperties("shiny", "gold");
        int result = getNumberOfOutmostBag(dataset, bagILookFor);

        int numberOfInsideBagsNeeded = getBagNeededInsideOfABag(dataset, bagILookFor);

        System.out.println("Number of Outmost bags: " + result);
        System.out.println("Number of bags inside " + bagILookFor.getAdjectiveOfColor() + " " + bagILookFor.getColour() + " is: " + numberOfInsideBagsNeeded);

    }

    private static List<Bag> getData() {

        List<Bag> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay7.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String[] dividedData = str.split(",");
                Bag bag = new Bag();
                List<BagProperties> coloursOfBagInside = new ArrayList<>();
                for (int i = 0; i < dividedData.length; i++) {
                    String[] dividedDataPerEntry = dividedData[i].split(" ");
                    if (i == 0) {
                        BagProperties bagProps = new BagProperties(dividedDataPerEntry[0], dividedDataPerEntry[1]);
                        bag.setColorOfTheBag(bagProps);
                        BagProperties bagInside = new BagProperties(dividedDataPerEntry[5], dividedDataPerEntry[6]);
                        String numberOfBags = dividedDataPerEntry[4];
                        if (numberOfBags.equals("no")) {
                            bagInside.setNumberOfBags(0);
                        } else {
                            bagInside.setNumberOfBags(Integer.parseInt(numberOfBags));
                            coloursOfBagInside.add(bagInside);
                        }
                    } else {
                        BagProperties bagInside = new BagProperties(dividedDataPerEntry[2], dividedDataPerEntry[3]);
                        String numberOfBags = dividedDataPerEntry[1];
                        if (numberOfBags.equals("no")) {
                            bagInside.setNumberOfBags(0);
                        } else {
                            bagInside.setNumberOfBags(Integer.parseInt(numberOfBags));
                        }
                        coloursOfBagInside.add(bagInside);
                    }
                }
                bag.setBagInside(coloursOfBagInside);
                result.add(bag);
                coloursOfBagInside = new ArrayList<>();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

    private static int getNumberOfOutmostBag(List<Bag> dataset, BagProperties bagProperties) {
        HashSet<BagProperties> resultSet = new HashSet<>();
        List<Bag> bagsThatContainsGold = dataset.stream().filter(bag -> bag.isBagContainingColour(bagProperties)).collect(Collectors.toList());
        int numberOfEnties = resultSet.size();
        int previousNumberOfEnties = resultSet.size() + 1;
        while (previousNumberOfEnties != numberOfEnties) {
            previousNumberOfEnties = resultSet.size();
            for (Bag temp : bagsThatContainsGold) {
                resultSet.add(temp.getColorOfTheBag());
            }
            for (BagProperties props : resultSet) {
                bagsThatContainsGold.addAll(dataset.stream().filter(bag -> bag.isBagContainingColour(props)).collect(Collectors.toList()));
            }
            numberOfEnties = resultSet.size();
        }
        return resultSet.size();
    }

    private static int getBagNeededInsideOfABag(List<Bag> dataset, BagProperties bagProperties){

        List<Bag> bagList = dataset.stream().filter(bag -> bag.getColorOfTheBag().equals(bagProperties)).collect(Collectors.toList());
        int result = 0;
        for(Bag bagTemp : bagList){
            if(bagTemp.getBagInside().size() == 0){
                return 0;
            }
            for(BagProperties bagPropertiesList : bagTemp.getBagInside())
                result+= bagPropertiesList.getNumberOfBags() + (bagPropertiesList.getNumberOfBags() * getBagNeededInsideOfABag(dataset,  bagPropertiesList));
        }
        return result;
    }

}
