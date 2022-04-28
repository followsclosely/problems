package io.github.followsclosley.problem.maze;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Maze maze = Maze.of(Path.of(ClassLoader.getSystemResource("maze001.txt").toURI()));
        maze.printMaze();
    }
}
