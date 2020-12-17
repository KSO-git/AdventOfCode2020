package Day17;

import Day16.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Cube> cubesAfter6ThIteration = getAllcubesAfterIteration(getData());
        long startTime = System.nanoTime();
        List<Hypercube> hypercubesAfter6ThIteration = getAllcubesAfterIterationForPartTwo(getDataForHyperCube());
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
        System.out.println("Part 1 answer" + cubesAfter6ThIteration.stream().filter(Cube::isActive).count());
        System.out.println("Part 2 answer" + hypercubesAfter6ThIteration.stream().filter(Hypercube::isActive).count());
        System.out.println("Part 2 took " + totalTime/1000000000 + " seconds");
    }

    private static List<Cube> getData() {

        List<Cube> result = new ArrayList<>();
        int rowNumber = 0;
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay17.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    Cube cube = new Cube(rowNumber, i, 0);
                    if ("#".equals(Character.toString(str.charAt(i)))) {
                        cube.setActive(true);
                    }
                    result.add(cube);
                }
                rowNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    private static List<Hypercube> getDataForHyperCube() {

        List<Hypercube> result = new ArrayList<>();
        int rowNumber = 0;
        try {
            File myObj = new File("AdventOfCode2020/TestData/inputDay17.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    Hypercube cube = new Hypercube(rowNumber, i, 0, 0);
                    if ("#".equals(Character.toString(str.charAt(i)))) {
                        cube.setActive(true);
                    }
                    result.add(cube);
                }
                rowNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    private static List<Cube> getAllcubesAfterIteration(List<Cube> cubesList) {
        List<Cube> finalList = new ArrayList<>(cubesList);
        List<Cube> tempFinalList;
        int iterationsForPart1 = 6;
        for (int cycle = 0; cycle < iterationsForPart1; cycle++) {
            tempFinalList = new ArrayList<>(finalList);
            List<Cube> finalTempFinalList = tempFinalList;
            finalList.forEach(cube -> {
                List<Cube> tempCubeList = getNeighbors(cube);
                finalTempFinalList.addAll(tempCubeList.stream().filter(cubes -> !finalList.contains(cubes)).collect(Collectors.toList()));
            });
            tempFinalList = new ArrayList<>(new HashSet<>(finalTempFinalList));
            for (Cube temp : tempFinalList) {
                long numberOfActiveNeighors = countActiveNeighbors(tempFinalList, temp);
                if (temp.isActive()) {
                    if (numberOfActiveNeighors != 2 && numberOfActiveNeighors != 3) {
                        finalList.remove(temp);
                    }
                } else {
                    if (numberOfActiveNeighors == 3) {
                        Cube temp2 = new Cube(temp.getX(), temp.getY(), temp.getZ());
                        finalList.remove(temp);
                        temp2.setActive(true);
                        finalList.add(temp2);
                    }
                }
            }
        }
        return finalList;
    }

    private static List<Hypercube> getAllcubesAfterIterationForPartTwo(List<Hypercube> cubesList) {
        List<Hypercube> finalList = new ArrayList<>(cubesList);
        List<Hypercube> tempFinalList;
        int iterationsForPart1 = 6;
        for (int cycle = 0; cycle < iterationsForPart1; cycle++) {
            tempFinalList = new ArrayList<>(finalList);
            List<Hypercube> finalTempFinalList = tempFinalList;
            finalList.forEach(cube -> {
                List<Hypercube> tempCubeList = getNeighborsForHyperCube(cube);
                finalTempFinalList.addAll(tempCubeList.stream().filter(cubes -> !finalList.contains(cubes)).collect(Collectors.toList()));
            });
            tempFinalList = new ArrayList<>(new HashSet<>(finalTempFinalList));
            for (Hypercube temp : tempFinalList) {
                long numberOfActiveNeighors = countActiveNeighborsForHyperCube(tempFinalList, temp);
                if (temp.isActive()) {
                    if (numberOfActiveNeighors != 2 && numberOfActiveNeighors != 3) {
                        finalList.remove(temp);
                    }
                } else {
                    if (numberOfActiveNeighors == 3) {
                        Hypercube temp2 = new Hypercube(temp.getX(), temp.getY(), temp.getZ(), temp.getW());
                        finalList.remove(temp);
                        temp2.setActive(true);
                        finalList.add(temp2);
                    }
                }
            }
        }
        return finalList;
    }

    private static long countActiveNeighbors(List<Cube> universe, Cube cube) {
        long count = 0;
        List<Cube> neighbors = getNeighbors(cube);
        for (Cube neighbor : neighbors) {
            long a = universe.stream().filter(neighbor::isEqual).filter(Cube::isActive).count();
            count += a;
        }

        return count;
    }

    private static long countActiveNeighborsForHyperCube(List<Hypercube> universe, Hypercube cube) {
        long count = 0;
        List<Hypercube> neighbors = getNeighborsForHyperCube(cube);
        for (Hypercube neighbor : neighbors) {
            long a = universe.stream().filter(neighbor::isEqual).filter(Hypercube::isActive).count();
            count += a;
        }

        return count;
    }

    private static List<Cube> getNeighbors(Cube cube) {
        List<Cube> result;
        List<Cube> tempRresult = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                tempRresult.add(new Cube(cube.getX() + i, cube.getY() + j, cube.getZ()));
            }
        }
        result = new ArrayList<>(tempRresult);
        for (Cube value : tempRresult) {
            result.add(new Cube(value.getX(), value.getY(), value.getZ() + 1));
            result.add(new Cube(value.getX(), value.getY(), value.getZ() - 1));
        }

        return result.stream().filter(cubeStream -> !cubeStream.isEqual(cube)).collect(Collectors.toList());
    }

    private static List<Hypercube> getNeighborsForHyperCube(Hypercube cube) {
        List<Hypercube> result;
        List<Hypercube> tempRresult = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int l = -1; l < 2; l++) {
                    tempRresult.add(new Hypercube(cube.getX() + i, cube.getY() + j, cube.getZ() + l, cube.getW()));
                }
            }
        }
        result = new ArrayList<>(tempRresult);
        for (Hypercube hypercube : tempRresult) {
            result.add(new Hypercube(hypercube.getX(), hypercube.getY(), hypercube.getZ(), hypercube.getW() - 1));
            result.add(new Hypercube(hypercube.getX(), hypercube.getY(), hypercube.getZ(), hypercube.getW() + 1));
        }

        return result.stream().filter(cubeStream -> !cubeStream.isEqual(cube)).collect(Collectors.toList());
    }
}
