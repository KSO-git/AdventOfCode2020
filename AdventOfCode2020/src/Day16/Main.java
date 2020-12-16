package Day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Validator> validationList = new ArrayList<>();

    public static void main(String[] args) {

        //List<Ticket> a = getTestData();
        List<Ticket> dataFromTicket = getData();

        List<Short> listOfNotValidTickerNumbers = new ArrayList<>();
        List<Ticket> result2 = new ArrayList<>();

        for (Ticket temp : dataFromTicket) {
            if (!temp.isTicketValid(validationList)) {
                listOfNotValidTickerNumbers.addAll(temp.getInvalidTicketNumbersValid(validationList));
            }
        }

        long numberOfValuesThatDoesntMatchAnyPattern = listOfNotValidTickerNumbers.stream()
                .mapToInt(Short::toUnsignedInt).sum();

        for (Ticket temp : dataFromTicket) {
            if (temp.isTicketValid(validationList)) {
                result2.add(temp);
            }
        }
        List<String> possibleLabelsOnColumnsOnTicker = getCalcualatedFieldsOrder(validationList, result2);

        String[] resultatColumnLabels = getFinalArrayOfLabels(possibleLabelsOnColumnsOnTicker);

        Ticket myticket = dataFromTicket.get(0);
        long sumOfAllDepartureColumnsOnMyTicket = 1L;
        for (int i = 0; i < resultatColumnLabels.length; i++) {
            if (resultatColumnLabels[i].contains("departure")) {
                sumOfAllDepartureColumnsOnMyTicket *= myticket.getNumberOnTicket().get(i);
            }
        }
        System.out.println("Sum of not valid numbhers: " + numberOfValuesThatDoesntMatchAnyPattern);
        System.out.println("Sum Of all columns from my ticket that starts with word 'departure': " + sumOfAllDepartureColumnsOnMyTicket);

    }

    private static String[] getFinalArrayOfLabels(List<String> possibleLabelsOnColumnsOnTicker) {
        String[] result = new String[20];
        String singleResultFromLabelTable = possibleLabelsOnColumnsOnTicker.stream().filter(str -> str.split(",").length == 1).collect(Collectors.toList()).get(0);
        int index = possibleLabelsOnColumnsOnTicker.indexOf(singleResultFromLabelTable);
        result[index] = singleResultFromLabelTable;
        for (int i = 2; i <= 20; i++) {
            int iterator = i;
            String nextResultFromLabelTable = possibleLabelsOnColumnsOnTicker.stream().filter(str -> str.split(",").length == iterator).collect(Collectors.toList()).get(0);
            List<String> listOfStringOnPosition = new LinkedList<>(Arrays.asList(nextResultFromLabelTable.split(",")));
            for (String s : result) {
                if (s != null) {
                    listOfStringOnPosition.remove(s);
                }
            }
            int indexOfNextResultFromLabelTable = possibleLabelsOnColumnsOnTicker.indexOf(nextResultFromLabelTable);
            result[indexOfNextResultFromLabelTable] = listOfStringOnPosition.get(0);
        }
        return result;
    }

    private static List<Ticket> getData() {

        List<Ticket> result = new ArrayList<>();
        int section = 0;
        int numerOfLinesAfterNewLineChar = 1;
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay16.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (str.isEmpty()) {
                    section++;
                    numerOfLinesAfterNewLineChar = 0;
                } else {
                    if (section == 0) {
                        validationList.add(getValidatorFromString(str));
                    } else {
                        if (numerOfLinesAfterNewLineChar != 0) {
                            result.add(new Ticket(Arrays.stream(str.split(",")).map(Short::parseShort).collect(Collectors.toList())));
                        } else {
                            numerOfLinesAfterNewLineChar++;
                        }
                    }
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    private static List<Ticket> getTestData() {

        List<Ticket> result = new ArrayList<>();
        int section = 0;
        int numerOfLinesAfterNewLineChar = 1;
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay16test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                if (str.isEmpty()) {
                    section++;
                    numerOfLinesAfterNewLineChar = 0;
                } else {
                    if (section == 0) {
                        validationList.add(getValidatorFromString(str));
                    } else {
                        if (numerOfLinesAfterNewLineChar != 0) {
                            result.add(new Ticket(Arrays.stream(str.split(",")).map(Short::parseShort).collect(Collectors.toList())));
                        } else {
                            numerOfLinesAfterNewLineChar++;
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    private static Validator getValidatorFromString(String input) {
        Validator result = new Validator();
        String[] startingValues = input.split(":");
        result.setName(startingValues[0]);
        List<Short> validNummberList = new ArrayList<>();
        String[] validValues = new String[2];
        validValues[0] = startingValues[1].trim().split(" ")[0];
        validValues[1] = startingValues[1].trim().split(" ")[2];
        for (String validValue : validValues) {
            String[] values = validValue.split("-");
            for (int j = Integer.parseInt(values[0].trim()); j <= Integer.parseInt(values[1].trim()); j++) {
                validNummberList.add((short) j);
            }
        }
        result.setValidNumbers(validNummberList);
        return result;
    }

    public static List<String> getCalcualatedFieldsOrder(List<Validator> validators, List<Ticket> ticketList) {
        String[] result = new String[validationList.size()];
        for (Ticket ticket : ticketList) {
            for (Short shortValueOnTicket : ticket.getNumberOnTicket()) {
                List<Validator> listOfValidatorsThatValueFromTicketMatchOn = validators.stream().filter(validator -> validator.getValidNumbers().contains(shortValueOnTicket)).collect(Collectors.toList());

                int index = ticket.getNumberOnTicket().indexOf(shortValueOnTicket);
                StringBuilder oldValue = new StringBuilder();
                int iter = 0;
                for (Validator validator : listOfValidatorsThatValueFromTicketMatchOn) {
                    if (iter == 0) {
                        oldValue.append(validator.getName());
                    } else {
                        oldValue.append(",").append(validator.getName());
                    }
                    iter++;
                }
                String check = result[index];
                if (check != null) {
                    List<String> matchingColumnLables = getEntryAfterModification(result[index], oldValue.toString());
                    StringBuilder newValue = new StringBuilder();
                    int secondIterator = 0;
                    for (String newString : matchingColumnLables) {
                        if (secondIterator == 0) {
                            newValue.append(newString);
                        } else {
                            newValue.append(",").append(newString);
                        }
                        secondIterator++;
                    }
                    result[index] = newValue.toString();
                } else {
                    result[index] = oldValue.toString();
                }

            }
        }
        return Arrays.asList(result);
    }

    private static List<String> getEntryAfterModification(String current, String toAdd) {
        List<String> result = new ArrayList<>();
        List<String> currentList = Arrays.asList(current.split(","));
        String[] newList = toAdd.split(",");
        for (String temp : newList) {
            if (currentList.contains(temp)) {
                result.add(temp);
            }
        }
        return result;
    }


}
