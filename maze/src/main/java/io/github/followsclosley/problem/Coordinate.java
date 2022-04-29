package io.github.followsclosley.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple pojo to hold an x,y and parent coordinate.
 */
public class Coordinate {

    private final int x, y;
    private final Coordinate parent;

    public Coordinate(int x, int y) {
        this(x, y, null);
    }

    public Coordinate(int x, int y, Coordinate parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public Coordinate(Coordinate coordinate, Coordinate parent) {
        this(coordinate.getX(), coordinate.getY(), parent);
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
     * Builds the path by calling getParent until the start of the path is discovered.
     *
     * @return The path from start to finish
     */
    public List<Coordinate> getPath() {
        List<Coordinate> path = new ArrayList<>();

        for (Coordinate step = this; step != null; step = step.parent) {
            path.add(step);
        }

        Collections.reverse(path);

        return path;
    }

    /**
     * The values in the vector are added to the coordinate.
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
        if (o == null || getClass() != o.getClass()) return false;
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
