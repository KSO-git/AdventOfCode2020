package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int PREAMLE = 25;
        List<ListEntry> dataset = getData();
        long firstThatDoesntMatch = findFirstPreamble(dataset, PREAMLE);
        List<ListEntry> seriesThatSumsUpToFirstThatDoesntMatch = getListThatSumsUpToNumber(dataset, firstThatDoesntMatch);
        long sum = sumUpMinAndMax(seriesThatSumsUpToFirstThatDoesntMatch);

        System.out.println("Code to crack Encryption: " + firstThatDoesntMatch);
        System.out.println("Sum Of Min and Max: " + sum);

    }

    private static List<ListEntry> getData() {

        List<ListEntry> result = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay9.txt");
            Scanner myReader = new Scanner(myObj);
            int line = 1;
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                ListEntry currentEntry = new ListEntry(line, Long.parseLong(str));
                result.add(currentEntry);
                line++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

    private static long findFirstPreamble(List<ListEntry> dataEntry, int preabmle) {
        long result = 0;
        int iteratior = preabmle;
        do {
            iteratior++;
            List<ListEntry> dataToCheck = getdataSetToCheck(dataEntry, preabmle, iteratior);
            int finalIteratior = iteratior;
            Optional<ListEntry> entryToCheck = dataEntry.stream().filter(entry -> entry.getLineNumber() == finalIteratior).findFirst();
            if (entryToCheck.isPresent()) {
                result = isAllValid(dataToCheck, entryToCheck.get().getValue());
            }
        } while (result == 0);

        return result;
    }

    private static List<ListEntry> getdataSetToCheck(List<ListEntry> dataEntry, int preabmle, int rowStartNumber) {
        List<ListEntry> result = new ArrayList<>();
        for (int i = 1; i <= preabmle; i++) {
            int currentRowNumber = rowStartNumber - i;
            Optional<ListEntry> singleEntry = dataEntry.stream().filter(entry -> entry.getLineNumber() == (currentRowNumber)).findFirst();
            singleEntry.ifPresent(result::add);

        }
        return result;
    }

    private static long isAllValid(List<ListEntry> dataEntry, long valueToCheck) {
        long result = 0;
        boolean isThereAMatch = false;
        for (ListEntry temp : dataEntry) {
            if (isThereAMatch) {
                break;
            }
            long valueLooking4 = valueToCheck - temp.getValue();

            if (valueLooking4 >= 0) {
                ListEntry entryWithoutAnyMatch = dataEntry.stream()
                        .filter(entry -> entry.getValue() != temp.getValue())
                        .filter(entry -> entry.getValue() == (valueLooking4))
                        .findFirst().orElse(null);
                if (entryWithoutAnyMatch != null) {
                    isThereAMatch = true;
                }
            }
        }
        if (!isThereAMatch) {
            result = valueToCheck;
        }
        return result;
    }

    private static List<ListEntry> getListThatSumsUpToNumber(List<ListEntry> dataset, long desiredNumber) {
        List<ListEntry> result = new ArrayList<>();
        ListIterator<ListEntry> intertaorMain = dataset.listIterator();
        while (intertaorMain.hasNext()) {
            intertaorMain.next();
            Iterator<ListEntry> intertaorTemp = dataset.listIterator(intertaorMain.previousIndex());
            long currentSum = result.stream().mapToLong(ListEntry::getValue).sum();
            while (currentSum < desiredNumber) {
                result.add(intertaorTemp.next());
                currentSum = result.stream().mapToLong(ListEntry::getValue).sum();
            }
            if (currentSum == desiredNumber) {
                return result;
            }
            result.clear();
        }
        return new ArrayList<>();

    }

    private static Long sumUpMinAndMax(List<ListEntry> datasetPart) {
        long result = 0L;
        Optional<Long> min = datasetPart.stream()
                .min(Comparator.comparing(ListEntry::getValue))
                .map(ListEntry::getValue);

        Optional<Long> max = datasetPart.stream()
                .max(Comparator.comparing(ListEntry::getValue))
                .map(ListEntry::getValue);

        if (min.isPresent() && max.isPresent()) {
            return min.get() + max.get();
        }
        return result;
    }
}
