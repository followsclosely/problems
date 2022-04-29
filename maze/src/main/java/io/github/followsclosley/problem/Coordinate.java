package io.github.followsclosley.problem;

import java.util.Objects;

/**
 * Simple pojo to hold an x,y
 */
public class Coordinate {
    private final int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate(Coordinate coordinate) {
        this(coordinate.getX(), coordinate.getY());
    }

    /**
     * @return The value of x
     */
    public int getX() {
        return x;
    }

    /**
     * @return The value of y
     */
    public int getY() {
        return y;
    }

    /**
     * The values in the vector are added to the coordinate and then returned.
     *
     * @param vector The distance to translate the Coordinate
     * @return The translated coordinate
     */
    public Coordinate translate(Coordinate vector) {
        return new Coordinate(this.x + vector.x, this.y + vector.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[y=" + y + ",x=" + x + "]";
    }
}
