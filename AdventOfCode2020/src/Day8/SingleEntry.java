package Day8;

public class SingleEntry {
    private int line;
    private Function function;
    private int functionValue;

    public enum Function {
        ACC,
        JMP,
        NOP
    }

    public SingleEntry() {
    }

    public SingleEntry(int line, Function function, int functionValue) {
        this.line = line;
        this.function = function;
        this.functionValue = functionValue;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public int getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(int functionValue) {
        this.functionValue = functionValue;
    }
}
