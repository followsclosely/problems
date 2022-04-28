package io.github.followsclosley.problem.maze.solvers;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.Maze;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

class DepthFirstSearchSolverTest {
    @Test
    void solve() throws IOException, URISyntaxException {
        DepthFirstSearchSolver solver = new DepthFirstSearchSolver();

        Maze maze = Maze.of(Path.of(ClassLoader.getSystemResource("maze001.txt").toURI()));

        List<Coordinate> path = solver.solve(maze);

        System.out.println("path = " + path);
    }
}