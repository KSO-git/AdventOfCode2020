package Day17;

import java.util.Objects;

public class Cube {
    private int x;
    private int y;
    private int z;
    boolean active;

    public Cube() {
    }

    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEqual(Cube cube) {
        return this.x == cube.getX() && this.y == cube.getY() && this.z == cube.getZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return x == cube.x &&
                y == cube.y &&
                z == cube.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
