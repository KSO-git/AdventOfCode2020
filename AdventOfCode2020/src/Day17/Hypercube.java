package Day17;

import java.util.Objects;

public class Hypercube {
    private int x;
    private int y;
    private int z;
    private int w;
    boolean active;

    public Hypercube() {
    }

    public Hypercube(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEqual(Hypercube hyperCube) {
        return this.x == hyperCube.getX() && this.y == hyperCube.getY() && this.z == hyperCube.getZ() && this.w == hyperCube.getW();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hypercube hyperCube = (Hypercube) o;
        return x == hyperCube.x &&
                y == hyperCube.y &&
                w == hyperCube.w &&
                z == hyperCube.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }
}
