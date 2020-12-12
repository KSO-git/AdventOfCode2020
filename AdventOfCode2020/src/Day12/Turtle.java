package Day12;

public class Turtle {
    private final static int NORTH = 0;
    private final static int EAST = 1;
    private final static int SOUTH = 2;
    private final static int WEST = 3;
    private final static String LEFT = "L";
    private final static String RIGHT = "R";
    private final static String DIRECTION_NORTH = "N";
    private final static String DIRECTION_SOUTH = "S";
    private final static String DIRECTION_EAST = "E";
    private final static String DIRECTION_WEST = "W";
    int posX;
    int posY;
    int directionFacing;
    String directionX;
    String directionY;
    int posXWaypoint;
    int posYWaypoint;
    String directionXWaypoint;
    String directionYWaypoint;

    public Turtle() {
        this.posX = 0;
        this.posY = 0;
        this.directionFacing = EAST;
        this.posXWaypoint = 10;
        this.posYWaypoint = 1;
        this.directionXWaypoint = DIRECTION_EAST;
        this.directionYWaypoint = DIRECTION_NORTH;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getDirectionFacing() {
        return directionFacing;
    }

    public void setDirectionFacing(int directionFacing) {
        this.directionFacing = directionFacing;
    }

    public String getDirectionX() {
        return directionX;
    }

    public void setDirectionX(String directionX) {
        this.directionX = directionX;
    }

    public String getDirectionY() {
        return directionY;
    }

    public void setDirectionY(String directionY) {
        this.directionY = directionY;
    }

    public void makeTurn(String direction, int value) {
        int modifier = 1;
        int numerOf90Turns = 0;
        if (LEFT.equals(direction)) {
            modifier = -1;
        }
        for (int i = 0; i < value / 90; i++) {
            numerOf90Turns++;
        }
        this.setDirectionFacing((this.directionFacing + 4 + (numerOf90Turns * modifier)) % 4);
    }

    public void makeTurnOnWaypoint(String direction, int value) {
        int modifier = 1;
        int numerOf90Turns = 0;
        int modifiedX = this.posXWaypoint;
        int modifiedY = this.posYWaypoint;
        if (LEFT.equals(direction)) {
            modifier = -1;
        }
        for (int i = 0; i < value / 90; i++) {
            int temp = modifiedX;
            modifiedX = modifiedY * modifier;
            modifiedY = -1 * temp * modifier;
        }
        this.posYWaypoint = modifiedY;
        this.posXWaypoint = modifiedX;
    }

    public void moveForward(int value) {
        makeMove(getWorldSideFromDirection(this.directionFacing), value);

    }

    public void moveForwardToWaypoint(int value) {
        this.posX = this.posX + value * this.posXWaypoint;
        this.posY = this.posY + value * this.posYWaypoint;
        //makeMove(getWorldSideFromDirection(this.directionFacing), value);

    }

    public void makeMove(String direction, int value) {
        switch (direction) {
            case DIRECTION_EAST:
                this.posX += value;
                break;
            case DIRECTION_WEST:
                this.posX -= value;
                break;
            case DIRECTION_NORTH:
                this.posY += value;
                break;
            case DIRECTION_SOUTH:
                this.posY -= value;
                break;
        }
    }

    public void makeMoveOnWaypoint(String direction, int value) {
        switch (direction) {
            case DIRECTION_EAST:
                this.posXWaypoint += value;
                break;
            case DIRECTION_WEST:
                this.posXWaypoint -= value;
                break;
            case DIRECTION_NORTH:
                this.posYWaypoint += value;
                break;
            case DIRECTION_SOUTH:
                this.posYWaypoint -= value;
                break;
        }
    }

    private String getWorldSideFromDirection(int directionValue) {
        String direction = DIRECTION_EAST;
        switch (directionValue) {
            case EAST:
                direction = DIRECTION_EAST;
                break;
            case WEST:
                direction = DIRECTION_WEST;
                break;
            case NORTH:
                direction = DIRECTION_NORTH;
                break;
            case SOUTH:
                direction = DIRECTION_SOUTH;
                break;
        }
        return direction;
    }

    public int getManthatianDistance() {
        return Math.abs(this.posX) + Math.abs(this.posY);
    }
}
