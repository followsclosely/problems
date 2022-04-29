package io.github.followsclosley.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple implementation of a linked coordinate. Can be used to track a path of coordinates
 */
public class LinkedCoordinate extends Coordinate {
    private final LinkedCoordinate parent;

    public LinkedCoordinate(Coordinate coordinate, LinkedCoordinate parent) {
        super(coordinate);
        this.parent = parent;
    }

    /**
     * Builds the path by calling getParent until the start of the path is discovered.
     *
     * @return The path from start to finish
     */
    public List<Coordinate> getPath() {
        List<Coordinate> path = new ArrayList<>();

        for (LinkedCoordinate step = this; step != null; step = step.parent) {
            path.add(step);
        }

        Collections.reverse(path);

        return path;
    }
}
