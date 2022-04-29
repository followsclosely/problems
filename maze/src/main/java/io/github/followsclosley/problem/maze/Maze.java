package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

public interface Maze {
    int getHeight();
    int getWidth();
    boolean isPath(Coordinate coordinate);
    Coordinate getStart();
    Coordinate getEnd();
}