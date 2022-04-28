package io.github.followsclosley.problem.maze.solvers;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.Maze;
import io.github.followsclosley.problem.maze.Solver;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearchSolver implements Solver {

    private static final Coordinate[] SEARCH_DIRECTIONS = {
            new Coordinate(0, 1),
            new Coordinate(1, 0),
            new Coordinate(0, -1),
            new Coordinate(-1, 0)};

    @Override
    public List<Coordinate> solve(Maze master) {

        System.out.println("Solve: " + master);
        Maze maze = new Maze(master);

        List<Coordinate> path = new ArrayList<>();

        explore(maze, maze.getStart(), path);

        System.out.println("path = " + path);
        return path;
    }

    private boolean explore(Maze maze, Coordinate coordinate, List<Coordinate> path) {

        if (maze.getEnd().equals(coordinate)) {
            path.add(coordinate);
            System.out.println("FOUND A WAY OUT!");
            return true;
        }

        if (maze.visit(coordinate)) {
            System.out.printf("Can move to %s%n", coordinate);
            path.add(coordinate);

            for (Coordinate movement : SEARCH_DIRECTIONS) {
                if (explore(maze, coordinate.translate(movement), path)) {
                    return true;
                }
            }
        } else {
            int size = path.size();
            if (size > 0) {
                path.remove(size - 1);
            }
        }

        System.out.printf("Can NOT move to %s%n", coordinate);

        return false;

    }
}