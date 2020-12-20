package Day19;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static String[] rulesset;
    private static ArrayList<String> messages;

    public static void main(String[] args) {
        //getTestData();
        getData();
        part1();
        part2();
    }

    public static void getTestData() {
        Scanner reader = null;
        try {
            reader = new Scanner(new File("AdventOfCode2020/TestData/inputDay19test.txt"));
        } catch (Exception e) {
            System.out.print("File not found");
        }

        rulesset = new String[150];
        messages = new ArrayList<>();

        assert reader != null;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            if (line.contains(":")) {
                int i = Integer.parseInt(line.substring(0, line.indexOf(":")));
                rulesset[i] = line.substring(line.indexOf(":") + 2).replace("\"", "");
            } else if (!line.equals(""))
                messages.add(line);
        }
    }

    public static void getData() {
        Scanner reader = null;
        try {
            reader = new Scanner(new File("AdventOfCode2020/TestData/inputDay19.txt"));
        } catch (Exception e) {
            System.out.print("File not found");
        }

        rulesset = new String[150];
        messages = new ArrayList<>();

        assert reader != null;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            if (line.contains(":")) {
                int i = Integer.parseInt(line.substring(0, line.indexOf(":")));
                rulesset[i] = line.substring(line.indexOf(":") + 2).replace("\"", "");
            } else if (!line.equals(""))
                messages.add(line);
        }
    }

    public static void part1() {
        String re = "^" + makeRegex(rulesset, 0) + "$";

        int count = 0;
        for (String m : messages)
            if (m.matches(re))
                count++;

        System.out.println("Valid messages for Part 1: " + count);

    }

    public static void part2() {
        String r42 = makeRegex(rulesset, 42);
        String r31 = makeRegex(rulesset, 31);

        String masterRegex = "^((42+) ((42 31) | (42{2} 31{2}) | (42{3} 31{3}) | (42{4} 31{4}) | (42{5} 31{5}) | (42{6} 31{6}) | (42{7} 31{7}) | (42{8} 31{8}) | (42{9} 31{9}) | (42{10} 31{10})))$";
        masterRegex = masterRegex.replace("42", r42).replace("31", r31).replace(" ", "");


        int count = 0;
        for (String m : messages) {
            if (m.matches(masterRegex)) {
                count++;
            }
        }

        System.out.println("Valid messages for Part 2: " + count);
    }

    private static String makeRegex(String[] rules, int i) {

        while (rules[i].matches(".*\\d.*")) {
            String[] parts = rules[i].split(" ");
            StringBuilder rep = new StringBuilder();
            for (String part : parts) {
                if (part.matches("\\d+")) {
                    if (rules[Integer.parseInt(part)].matches("[ab]")) {
                        rep.append(rules[Integer.parseInt(part)]);
                    } else {
                        rep.append("( ").append(rules[Integer.parseInt(part)]).append(" )");
                    }
                } else {
                    rep.append(part);
                }
            }
            rules[i] = rep.toString();
        }
        return "(" + rules[i] + ")";
    }

}
