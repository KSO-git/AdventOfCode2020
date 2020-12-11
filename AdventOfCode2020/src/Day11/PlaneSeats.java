package Day11;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaneSeats {
    public static final char SEAT_FREE = 'L';
    public static final char SEAT_TAKEN = '#';
    public static final char FLOOR = '.';

    private char[][] seatSituationInPlane;
    private int length;
    private int width;
    private int taken;

    public PlaneSeats(char[][] seatSituationInPlane, int width, int length) {
        this.seatSituationInPlane = seatSituationInPlane;
        this.width = width;
        this.length = length;
        this.taken = 0;
    }

    public char[][] getSeatSituationInPlane() {
        return seatSituationInPlane;
    }

    public void setSeatSituationInPlane(char[][] seatSituationInPlane) {
        this.seatSituationInPlane = seatSituationInPlane;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    public PlaneSeats newArrangemnt() {
        char[][] result = new char[this.length][this.width];
        int occupied = 0;

        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                char curentChar = this.seatSituationInPlane[i][j];
                char newChar = FLOOR;
                switch (curentChar) {
                    case SEAT_FREE:
                        newChar = checkUpdatedPositionForEmpty(getAdjecent(i, j));
                        break;
                    case SEAT_TAKEN:
                        newChar = checkUpdatedPositionForOccupied(getAdjecent(i, j), 4);
                        break;
                    case FLOOR:
                        newChar = FLOOR;
                        break;
                }
                if (newChar == SEAT_TAKEN) {
                    occupied++;
                }
                result[i][j] = newChar;
            }
        }
        PlaneSeats resultPlaneSeat = new PlaneSeats(result, this.width, this.length);
        resultPlaneSeat.setTaken(occupied);
        return resultPlaneSeat;
    }

    public PlaneSeats newArrangemntPartTwo() {
        char[][] result = new char[this.length][this.width];
        int occupied = 0;

        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                char curentChar = this.seatSituationInPlane[i][j];
                char newChar = FLOOR;
                switch (curentChar) {
                    case SEAT_FREE:
                        newChar = checkUpdatedPositionForEmpty(getInSightRange(i, j));
                        break;
                    case SEAT_TAKEN:
                        newChar = checkUpdatedPositionForOccupied(getInSightRange(i, j), 5);
                        break;
                    case FLOOR:
                        newChar = FLOOR;
                        break;
                }
                if (newChar == SEAT_TAKEN) {
                    occupied++;
                }
                result[i][j] = newChar;
            }
        }
        PlaneSeats resultPlaneSeat = new PlaneSeats(result, this.width, this.length);
        resultPlaneSeat.setTaken(occupied);
        return resultPlaneSeat;
    }

    private List<Character> getAdjecent(int posX, int posY) {
        List<Character> result = new ArrayList<>();
        List<Pair<Integer, Integer>> positionsOfAdjecents = new ArrayList<>();
        List<Pair<Integer, Integer>> finalListOfPositions;
        positionsOfAdjecents.add(new Pair<>(posX - 1, posY - 1));
        positionsOfAdjecents.add(new Pair<>(posX - 1, posY));
        positionsOfAdjecents.add(new Pair<>(posX - 1, posY + 1));

        positionsOfAdjecents.add(new Pair<>(posX, posY - 1));
        positionsOfAdjecents.add(new Pair<>(posX, posY + 1));

        positionsOfAdjecents.add(new Pair<>(posX + 1, posY - 1));
        positionsOfAdjecents.add(new Pair<>(posX + 1, posY));
        positionsOfAdjecents.add(new Pair<>(posX + 1, posY + 1));
        finalListOfPositions = positionsOfAdjecents.stream()
                .filter(pair -> pair.getKey() >= 0 && pair.getKey() < this.length)
                .filter(pair -> pair.getValue() >= 0 && pair.getValue() < this.width)
                .collect(Collectors.toList());
        for (Pair<Integer, Integer> temp : finalListOfPositions) {
            result.add(this.seatSituationInPlane[temp.getKey()][temp.getValue()]);
        }
        return result;
    }

    private Character getFirstSeatInSightInDirection(int startPosX, int startPosY, int changeX, int changeY) {
        int currentPosX = startPosX + changeX;
        int currentPosY = startPosY + changeY;
        while (currentPosX >= 0 && currentPosX < this.length && currentPosY >= 0 && currentPosY < this.width) {
            if (this.seatSituationInPlane[currentPosX][currentPosY] != '.') {
                return this.seatSituationInPlane[currentPosX][currentPosY];
            }
            currentPosX += changeX;
            currentPosY += changeY;
        }
        return FLOOR;
    }

    private List<Character> getInSightRange(int posX, int posY) {
        List<Character> result = new ArrayList<>();
        result.add(getFirstSeatInSightInDirection(posX, posY, -1, -1));
        result.add(getFirstSeatInSightInDirection(posX, posY, -1, 0));
        result.add(getFirstSeatInSightInDirection(posX, posY, -1, 1));

        result.add(getFirstSeatInSightInDirection(posX, posY, 0, -1));
        result.add(getFirstSeatInSightInDirection(posX, posY, 0, 1));

        result.add(getFirstSeatInSightInDirection(posX, posY, 1, -1));
        result.add(getFirstSeatInSightInDirection(posX, posY, 1, 0));
        result.add(getFirstSeatInSightInDirection(posX, posY, 1, 1));
        return result;
    }

    private char checkUpdatedPositionForEmpty(List<Character> charsAdjecent) {
        char result = SEAT_FREE;
        if (!charsAdjecent.contains(SEAT_TAKEN)) {
            result = SEAT_TAKEN;
        }
        return result;
    }

    private char checkUpdatedPositionForOccupied(List<Character> charsAdjecent, int minNumerOfTaken) {
        char result = SEAT_TAKEN;
        long takenAdjecentSeats = charsAdjecent.stream().filter(seat -> seat == SEAT_TAKEN).count();
        if (takenAdjecentSeats >= minNumerOfTaken) {
            result = SEAT_FREE;
        }
        return result;
    }
}
