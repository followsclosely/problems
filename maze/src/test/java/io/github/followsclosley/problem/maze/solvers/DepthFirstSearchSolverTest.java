package io.github.followsclosley.problem.maze.solvers;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.ImmutableMaze;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

class DepthFirstSearchSolverTest {
    @Test
    void solve() throws IOException, URISyntaxException {
        DepthFirstSearchSolver solver = new DepthFirstSearchSolver();
        ImmutableMaze maze = ImmutableMaze.of(Path.of(ClassLoader.getSystemResource("maze001.txt").toURI()));
        List<Coordinate> path = solver.solve(maze);
        System.out.println("path = " + path);
        maze.printMaze(path);
    }
}