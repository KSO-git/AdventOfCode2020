package Day3;

import java.util.List;

public class SloepLevel {
    private int level;
    private List<Integer> treesPosions;
    private int maxSlopeWidth;

    public SloepLevel() {
    }

    public SloepLevel(int level, List<Integer> treesPosions, int maxSlopeWidth) {
        this.level = level;
        this.treesPosions = treesPosions;
        this.maxSlopeWidth = maxSlopeWidth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Integer> getTreesPosions() {
        return treesPosions;
    }

    public void setTreesPosions(List<Integer> treesPosions) {
        this.treesPosions = treesPosions;
    }

    public int getMaxSlopeWidth() {
        return maxSlopeWidth;
    }

    public void setMaxSlopeWidth(int maxSlopeWidth) {
        this.maxSlopeWidth = maxSlopeWidth;
    }

    public boolean isTreePresent(int position) {
        int realPosition = position % this.maxSlopeWidth;
        if (realPosition == 0) {
            realPosition = 31;
        }
        return this.treesPosions.contains(realPosition);
    }
}
