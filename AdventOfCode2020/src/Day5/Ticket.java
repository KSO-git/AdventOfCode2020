package Day5;

import Day4.Passport;

public class Ticket {
    private static final int MIN_ROW = 0;
    private static final int MAX_ROW = 127;
    private static final int MIN_SEAT = 0;
    private static final int MAX_SEAT = 7;
    private String stringCode;
    private int row;
    private int seatInRow;
    private long ticketID;

    public Ticket() {
    }

    public Ticket(String stringCode) {
        this.stringCode = stringCode;
        this.row = getRow(stringCode);
        this.seatInRow = getSeatInRow(stringCode);
        this.ticketID = (this.row * 8) + this.seatInRow;
    }

    public String getStringCode() {
        return stringCode;
    }

    public void setStringCode(String stringCode) {
        this.stringCode = stringCode;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatInRow() {
        return seatInRow;
    }

    public void setSeatInRow(int seatInRow) {
        this.seatInRow = seatInRow;
    }

    public long getTicketID() {
        return ticketID;
    }

    public void setTicketID(long ticketID) {
        this.ticketID = ticketID;
    }

    private int getRow(String ticketStringCode) {
        //With Recursion
        return getGenreticSeatOrRow(ticketStringCode.substring(0, 7), MIN_ROW, MAX_ROW, 'F');

        //Without Recursion
        /*int currentMin = MIN_ROW;
        int currentMax = MAX_ROW;
        for (int i = 0; i < 6; i++) {
            int half = (currentMax + currentMin) / 2;
            if (ticketStringCode.charAt(i) == 'F') {
                currentMax = half;
            } else {
                currentMin = half + 1;
            }
        }
        if (ticketStringCode.charAt(6) == 'F') {
            return currentMin;
        }
        return currentMax;*/
    }

    private int getSeatInRow(String ticketStringCode) {
        //With Recursion
        return getGenreticSeatOrRow(ticketStringCode.substring(7, 10), MIN_SEAT, MAX_SEAT, 'L');

        //Without Recursion
        /*int currentMin = MIN_SEAT;
        int currentMax = MAX_SEAT;
        for (int i = 7; i < 10; i++) {
            int half = (currentMax + currentMin) / 2;
            if (ticketStringCode.charAt(i) == 'L') {
                currentMax = half;
            } else {
                currentMin = half + 1;
            }
        }
        if (ticketStringCode.charAt(9) == 'L') {
            return currentMin;
        }
        return currentMax;*/
    }

    private int getGenreticSeatOrRow(String ticketStringCode, int min, int max, char oneOfConditions) {
        if (ticketStringCode.length() == 1) {
            if (oneOfConditions == ticketStringCode.charAt(0)) {
                return min;
            }
            return max;
        } else {
            int half = (max + min) / 2;
            if (ticketStringCode.charAt(0) == oneOfConditions) {
                return getGenreticSeatOrRow(ticketStringCode.substring(1), min, half, oneOfConditions);
            } else {
                return getGenreticSeatOrRow(ticketStringCode.substring(1), half + 1, max, oneOfConditions);
            }
        }
    }
}

