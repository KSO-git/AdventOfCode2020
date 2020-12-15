package Day15;

public class Turn {

    private int turnNumber;
    private int value;

    public Turn() {
    }

    public Turn(int turnNumber, int value) {
        this.turnNumber = turnNumber;
        this.value = value;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
