package io.github.followsclosley.problem.maze.solvers;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.AccountableMaze;
import io.github.followsclosley.problem.maze.Maze;
import io.github.followsclosley.problem.maze.Solver;

import java.util.Collections;
import java.util.List;

/**
 * Uses a Depth-first search to solve the maze.
 *
 * <p>
 *   Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.
 *   The algorithm starts at the root node (selecting some arbitrary node as the root node in the case
 *   of a graph) and explores as far as possible along each branch before backtracking.
 * </p>
 *
 * <p>
 *   <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Depth-first-tree.svg/250px-Depth-first-tree.svg.png" />
 *   --https://en.wikipedia.org/wiki/Depth-first_search
 * </p>
 */
public class DepthFirstSearchSolver implements Solver {

    private static final Coordinate[] SEARCH_DIRECTIONS = {
            new Coordinate(0, 1),
            new Coordinate(1, 0),
            new Coordinate(0, -1),
            new Coordinate(-1, 0)};

    @Override
    public List<Coordinate> solve(Maze maze) {
        //Create an AccountableMaze or use it if it already is an AccountableMaze
        AccountableMaze accountableMaze = ( maze instanceof AccountableMaze am) ? am : new AccountableMaze(maze);
        if (explore(accountableMaze, maze.getStart())) {
            return accountableMaze.getPath();
        } else {
            return Collections.emptyList();
        }
    }

    private boolean explore(AccountableMaze maze, Coordinate coordinate) {

        if (!maze.isInbounds(coordinate)
                || !maze.isPath(coordinate)
                || !maze.visit(coordinate)
        ) {
            return false;
        }

        maze.getPath().add(coordinate);

        if (maze.getEnd().equals(coordinate)) {
            return true;
        }

        for (Coordinate movement : SEARCH_DIRECTIONS) {
            if (explore(maze, coordinate.translate(movement))) {
                return true;
            }
        }

        maze.getPath().remove(maze.getPath().size() - 1);
        return false;
    }
}