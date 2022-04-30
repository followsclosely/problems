package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This mutable implementation of Maze keeps track of coordinates that have been visited.
 */
public class AccountableMaze extends DefaultMaze {

    // Holds all the locations on the map that have been visited.
    private final boolean[][] visited;

    // The path through the maze.
    private final LinkedList<Coordinate> path;
    private final List<Move> moves;

    public AccountableMaze(Maze maze) {
        super(maze);
        this.path = new LinkedList<>();
        this.visited = new boolean[maze.getHeight()][maze.getWidth()];
        this.moves = new ArrayList<>();
    }

    /**
     * @return The path of Coordinates starting at the beginning of the maze
     */
    public LinkedList<Coordinate> getPath() {
        return this.path;
    }

    /**
     * @return All the moves performed to solve this maze.
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Visits the Coordinate on the map marking this spot as visited.
     *
     * @param c Coordinate of the map to visit.
     * @return true if this is the first successful visit,
     * return false if this Coordinate is a wall or has already been visited.
     */
    public boolean visit(Coordinate c) {
        if (!this.data[c.getY()][c.getX()]) {
            return false;
        }

        if (this.visited[c.getY()][c.getX()]) {
            return false;
        } else {
            moves.add(new Move(Move.MoveType.EXPLORE, c));
            return this.visited[c.getY()][c.getX()] = true;
        }
    }

    /**
     * @return The {@link Coordinate} of the move undone
     */
    public Coordinate backtrack() {
        Coordinate c = path.remove(path.size()-1);
        moves.add(new Move(Move.MoveType.BACKTRACK, c));
        return c;
    }

    public static class Move {

        public enum MoveType {
            EXPLORE,
            BACKTRACK,
            DEAD_PATH
        }

        private final MoveType moveType;
        private final Coordinate coordinate;

        public Move(MoveType moveType, Coordinate coordinate) {
            this.moveType = moveType;
            this.coordinate = coordinate;
        }

        public MoveType getMoveType() {
            return moveType;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        @Override
        public String toString() {
            return "Move{moveType=" + moveType + ", coordinate=" + coordinate + '}';
        }
    }
}