package Day16;

import java.util.List;

public class Validator {
    private String name;
    private List<Short> validNumbers;

    public Validator() {
    }

    public Validator(String name, List<Short> validNumbers) {
        this.name = name;
        this.validNumbers = validNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Short> getValidNumbers() {
        return validNumbers;
    }

    public void setValidNumbers(List<Short> validNumbers) {
        this.validNumbers = validNumbers;
    }
}
