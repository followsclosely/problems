package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

import java.util.List;

public interface Solver {
    /**
     * Solves the given maze returning the path from start to finish.
     *
     * @param maze The maze to be solved
     * @return A List of Coordinates starting from the beginning of the maze.
     * Returns an empty Collection if no solution is found.
     */
    List<Coordinate> solve(Maze maze);
}
