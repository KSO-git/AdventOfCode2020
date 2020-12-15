package Day8;

import Day7.Bag;
import Day7.BagProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<SingleEntry> dataset = getData();
        List<Integer> listOfVisitedLines = new ArrayList<>();

        int i = 1;
        int accValue = 0;
        while (!listOfVisitedLines.contains(i)) {
            listOfVisitedLines.add(i);
            int currentLine = i;
            Optional<SingleEntry> currentEntryOpt = dataset.stream().filter(data -> data.getLine() == currentLine).findFirst();
            if (currentEntryOpt.isPresent()) {
                SingleEntry currentEntry = currentEntryOpt.get();
                switch (currentEntry.getFunction()) {
                    case ACC:
                        accValue += currentEntry.getFunctionValue();
                        i++;
                        break;
                    case JMP:
                        i += currentEntry.getFunctionValue();
                        break;
                    case NOP:
                    default:
                        i++;
                        break;
                }
            }
        }
        int accValueTemp = -1;
        while (accValueTemp == -1) {
            int maxValue = listOfVisitedLines.stream().mapToInt(integer -> integer).max().orElseThrow(NoSuchElementException::new);
            listOfVisitedLines.remove(Integer.valueOf(maxValue));
            accValueTemp = getAccValue(dataset, maxValue);
        }


        System.out.println("Accumulator Before Loop: " + accValue);
        System.out.println("Accumulator beofre last line: " + accValueTemp);

    }

    private static int getAccValue(List<SingleEntry> dataset, int maxLineNumer) {
        int lineNumber = 1;
        int accValue = 0;
        List<Integer> listOfVisitedLines = new ArrayList<>();
        while (!listOfVisitedLines.contains(lineNumber)) {
            int currentLine = lineNumber;
            listOfVisitedLines.add(lineNumber);
            Optional<SingleEntry> currentEntryOpt = dataset.stream().filter(data -> data.getLine() == currentLine).findFirst();
            if (currentEntryOpt.isPresent()) {
                SingleEntry currentEntry = currentEntryOpt.get();
                if (lineNumber == maxLineNumer) {
                    if (SingleEntry.Function.NOP.equals(currentEntry.getFunction())) {
                        lineNumber += currentEntry.getFunctionValue();
                    } else if (SingleEntry.Function.JMP.equals(currentEntry.getFunction())) {
                        lineNumber++;
                    }
                } else {
                    switch (currentEntry.getFunction()) {
                        case ACC:
                            accValue += currentEntry.getFunctionValue();
                            lineNumber++;
                            break;
                        case JMP:
                            lineNumber += currentEntry.getFunctionValue();
                            break;
                        case NOP:
                        default:
                            lineNumber++;
                            break;
                    }
                }
                if (dataset.size() <= lineNumber) {
                    return accValue;
                }
            }
        }
        return -1;
    }

    private static List<SingleEntry> getData() {

        List<SingleEntry> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay8.txt");
            Scanner myReader = new Scanner(myObj);
            int lineNumber = 1;
            while (myReader.hasNextLine()) {
                SingleEntry entry = new SingleEntry();
                entry.setLine(lineNumber);
                String str = myReader.nextLine();
                String[] dividedData = str.split(" ");
                switch (dividedData[0]) {
                    case "acc":
                        entry.setFunction(SingleEntry.Function.ACC);
                        break;
                    case "jmp":
                        entry.setFunction(SingleEntry.Function.JMP);
                        break;
                    case "nop":
                        entry.setFunction(SingleEntry.Function.NOP);
                        break;
                }
                entry.setFunctionValue(Integer.parseInt(dividedData[1]));
                lineNumber++;
                result.add(entry);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }
}
