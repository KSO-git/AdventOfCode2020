package Day9;

public class ListEntry {
    private int lineNumber;
    private long value;

    public ListEntry() {
    }

    public ListEntry(int lineNumber, long value) {
        this.lineNumber = lineNumber;
        this.value = value;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
