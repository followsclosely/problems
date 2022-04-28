package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze);
}
