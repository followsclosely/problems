package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

/**
 * A network of paths and hedges designed as a puzzle through which one has to find a way.
 */
public interface Maze {

    /**
     * @return The height of the maze
     */
    int getHeight();

    /**
     * @return The width of the maze
     */
    int getWidth();

    /**
     * Determines if the coordinate is walkable.
     *
     * @param coordinate The location in the maze to inspect
     * @return true if the coordinate in the maze is a path and not a wall
     */
    boolean isPath(Coordinate coordinate);

    /**
     * @return The Coordinate of the start
     */
    Coordinate getStart();

    /**
     * @return The Coordinate of the goal or exit
     */
    Coordinate getEnd();
}