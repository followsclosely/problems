package io.github.followsclosley.problem.maze.solvers;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.Maze;

import java.util.ArrayList;
import java.util.List;

public class MazeContext {
    private final Maze maze;
    private final boolean[][] visited;
    private final List<Coordinate> path;

    public MazeContext(Maze maze) {
        this.maze = maze;
        this.visited = new boolean[maze.getHeight()][maze.getWidth()];
        this.path = new ArrayList<>();
    }

    public Maze getMaze() {
        return maze;
    }

    public List<Coordinate> getPath() {
        return this.path;
    }

    /**
     * Check to see if the movement is within bounds of the maze. This is only
     * needed if the loaded maze does not contain walls along all the edges.
     *
     * @param c Coordinate to check
     * @return true if Coordinate is in bounds of the underlying two-dimensional array
     */
    public boolean isInbounds(Coordinate c) {
        return (c.getY() >= 0 && c.getY() < maze.getHeight()
                && c.getX() >= 0 && c.getX() < maze.getWidth());
    }

    public boolean hasVisited(Coordinate c) {
        if (this.visited[c.getY()][c.getX()]) {
            return true;
        } else {
            this.visited[c.getY()][c.getX()] = true;
            return false;
        }
    }
}
