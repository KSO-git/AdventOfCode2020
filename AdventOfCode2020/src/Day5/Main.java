package Day5;

import Day3.SloepLevel;
import Day4.Passport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Ticket> data = getData();
        Long biggstSeatId = data.stream().mapToLong(Ticket::getTicketID).max().orElseThrow();
        long missignSeat = -1;

        for (int i = 1; i < 127; i++) {
            int currentRowNumber = i;
            List<Ticket> tempTicketList = data.stream().filter(ticket -> ticket.getRow() == currentRowNumber).collect(Collectors.toList());
            if (tempTicketList.size() == 7) {
                missignSeat = getMissingSeat(tempTicketList);
                break;
            }
        }
        if (missignSeat != -1) {
            System.out.println("Missing seat is: " + missignSeat);
        } else {
            System.out.println("Fuck");
        }

        System.out.println("Biggest seat ID: " + biggstSeatId);

    }

    private static long getMissingSeat(List<Ticket> ticketList) {
        List<Long> sortedIdList = ticketList.stream().map(Ticket::getTicketID).sorted().collect(Collectors.toList());
        Long previous = 0L;
        for (Long temp : sortedIdList) {
            if ((temp - previous) == 2) {
                return temp - 1;
            } else {
                previous = temp;
            }
        }
        return -1;
    }

    private static List<Ticket> getData() {

        List<Ticket> dataList = new ArrayList<>();
        try {
            File myObj = new File("AdventOfCode2020/inputDay5.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 1;
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                Ticket ticket = new Ticket(str);
                dataList.add(ticket);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return dataList;
    }

}
