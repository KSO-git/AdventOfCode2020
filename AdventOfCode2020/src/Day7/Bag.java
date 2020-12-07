package Day7;

import java.util.List;

public class Bag {
    private BagProperties colorOfTheBag;
    private List<BagProperties> bagsInside;

    public Bag() {
    }

    public BagProperties getColorOfTheBag() {
        return colorOfTheBag;
    }

    public void setColorOfTheBag(BagProperties colorOfTheBag) {
        this.colorOfTheBag = colorOfTheBag;
    }

    public List<BagProperties> getBagInside() {
        return bagsInside;
    }

    public void setBagInside(List<BagProperties> bagInside) {
        this.bagsInside = bagInside;
    }

    public boolean isBagContainingColour(BagProperties colorOfTheBag){
        return this.bagsInside.stream()
                .filter(bagProperties -> bagProperties.getAdjectiveOfColor().equals(colorOfTheBag.getAdjectiveOfColor()))
                .anyMatch(bagProperties -> bagProperties.getColour().equals(colorOfTheBag.getColour()));
    }
}
