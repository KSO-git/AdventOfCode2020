package Day7;

public class BagProperties {
    private String colour;
    private String adjectiveOfColor;
    private int numberOfBags;

    public BagProperties() {
    }

    public BagProperties(String colour, String adjectiveOfColor) {
        this.colour = colour;
        this.adjectiveOfColor = adjectiveOfColor;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getAdjectiveOfColor() {
        return adjectiveOfColor;
    }

    public void setAdjectiveOfColor(String adjectiveOfColor) {
        this.adjectiveOfColor = adjectiveOfColor;
    }

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        BagProperties bag = (BagProperties) obj;

        // Compare the data members and return accordingly
        return this.colour.equals(bag.getColour()) && this.adjectiveOfColor.equals(bag.getAdjectiveOfColor());
    }
}
