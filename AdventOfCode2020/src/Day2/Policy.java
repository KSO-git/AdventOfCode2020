package Day2;

public class Policy {
    private int numberMin;
    private int numberMax;
    private String letter;
    private String passowrdValue;

    public Policy(int numberMin, int numberMax, String letter, String passowrdValue) {
        this.numberMin = numberMin;
        this.numberMax = numberMax;
        this.letter = letter;
        this.passowrdValue = passowrdValue;
    }

    public int getNumberMin() {
        return numberMin;
    }

    public void setNumberMin(int numberMin) {
        this.numberMin = numberMin;
    }

    public int getNumberMax() {
        return numberMax;
    }

    public void setNumberMax(int numberMax) {
        this.numberMax = numberMax;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getPassowrdValue() {
        return passowrdValue;
    }

    public void setPassowrdValue(String passowrdValue) {
        this.passowrdValue = passowrdValue;
    }

    public boolean isValid() {
        int numberOfLetters = this.passowrdValue.length();
        int numberOfDesiredLetters = numberOfLetters - this.passowrdValue.replace(this.letter, "").length();
        return (this.numberMin <= numberOfDesiredLetters) && (this.numberMax >= numberOfDesiredLetters);
    }

    public boolean isValidWithMoreRestrictions() {
        String firstLetter = Character.toString(this.passowrdValue.charAt(this.numberMin - 1));
        String secondLetter = Character.toString(this.passowrdValue.charAt(this.numberMax - 1));
        if (this.letter.equals(firstLetter) || this.letter.equals(secondLetter)) {
            return !this.letter.equals(firstLetter) || !this.letter.equals(secondLetter);
        }
        return false;
    }
}
