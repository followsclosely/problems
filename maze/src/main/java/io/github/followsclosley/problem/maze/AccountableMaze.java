package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * This mutable implementation of Maze keeps track of coordinates that have been visited.
 */
public class AccountableMaze extends DefaultMaze {

    // Holds all the locations on the map that have been visited.
    private final boolean[][] visited;

    // The path through the maze.
    private final List<Coordinate> path;

    public AccountableMaze(Maze maze) {
        super(maze);
        this.path = new ArrayList<>();
        this.visited = new boolean[maze.getHeight()][maze.getWidth()];
    }

    /**
     * @return The path of Coordinates starting at the beginning of the maze
     */
    public List<Coordinate> getPath() {
        return this.path;
    }

    /**
     * Visits the Coordinate on the map marking this spot as visited.
     *
     * @param c Coordinate of the map to visit.
     *
     * @return true if this is the first successful visit,
     *         return false if this Coordinate is a wall or has already been visited.
     */
    public boolean visit(Coordinate c) {
        if( !this.data[c.getY()][c.getX()] ){
            return false;
        }

        if (this.visited[c.getY()][c.getX()]) {
            return false;
        } else {
            return this.visited[c.getY()][c.getX()] = true;
        }
    }
}