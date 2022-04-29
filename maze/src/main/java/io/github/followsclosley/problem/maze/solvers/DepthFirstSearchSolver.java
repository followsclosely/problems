package io.github.followsclosley.problem.maze.solvers;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.ImmutableMaze;
import io.github.followsclosley.problem.maze.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstSearchSolver implements Solver {

    private static final Coordinate[] SEARCH_DIRECTIONS = {
            new Coordinate(0, 1),
            new Coordinate(1, 0),
            new Coordinate(0, -1),
            new Coordinate(-1, 0)};

    @Override
    public List<Coordinate> solve(ImmutableMaze maze) {
        System.out.println("Solve: " + maze);

        MazeContext context = new MazeContext(maze);
        if (explore(context, maze.getStart())) {
            return context.getPath();
        } else {
            return Collections.emptyList();
        }
    }

    private boolean explore(MazeContext context, Coordinate coordinate) {

        if (!context.isInbounds(coordinate)
                || !context.getMaze().isPath(coordinate)
                || context.visit(coordinate)
        ) {
            return false;
        }

        context.getPath().add(coordinate);

        if (context.getMaze().getEnd().equals(coordinate)) {
            System.out.println("FOUND A WAY OUT!");
            System.out.println(context.getPath());
            return true;
        }

        for (Coordinate movement : SEARCH_DIRECTIONS) {
            if (explore(context, coordinate.translate(movement))) {
                return true;
            }
        }

        context.getPath().remove(context.getPath().size() - 1);
        return false;
    }
}