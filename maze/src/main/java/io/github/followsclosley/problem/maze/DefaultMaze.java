package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Default immutable implementation of a Maze. It is meant to be extended to be useful.
 */
public class DefaultMaze implements Maze {

    protected final int width, height;
    protected final boolean[][] data;
    protected final Coordinate start;
    protected final Coordinate end;

    protected DefaultMaze(Maze maze) {
        this.height = maze.getHeight();
        this.width = maze.getWidth();
        this.start = maze.getStart();
        this.end = maze.getEnd();
        this.data = new boolean[maze.getHeight()][maze.getWidth()];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.data[y][x] = maze.isPath(new Coordinate(x, y));
            }
        }
    }

    protected DefaultMaze(boolean[][] data, Coordinate start, Coordinate end) {
        this.data = data;
        this.height = data.length;
        this.width = data[0].length;
        this.start = start;
        this.end = end;
    }

    /**
     * Load a maze from a text file. The text file should contain integers 0-4.
     * <ul>
     *     <li>0 = Road</li>
     *     <li>1 = Wall</li>
     *     <li>2 = Start/Entry</li>
     *     <li>3 = End/Exit</li>
     * </ul>
     *
     * @param path The location of the text file
     * @return A populated Maze
     * @throws IOException if resource can not be located
     */
    public static DefaultMaze of(Path path) throws IOException {

        Coordinate start = null;
        Coordinate end = null;

        String content = Files.readString(path, StandardCharsets.US_ASCII);
        String[] lines = content.split("\r\n|\r|\n");
        boolean[][] data = new boolean[lines.length][];

        for (int y = 0; y < lines.length; y++) {
            String[] values = lines[y].split("");
            data[y] = new boolean[values.length];
            for (int x = 0; x < values.length; x++) {
                int value = Integer.parseInt(values[x].trim());
                switch (value) {
                    case 0 -> data[y][x] = true;
                    case 2 -> {
                        data[y][x] = true;
                        start = new Coordinate(x, y);
                    }
                    case 3 -> {
                        data[y][x] = true;
                        end = new Coordinate(x, y);
                    }
                }
            }
        }

        return new DefaultMaze(data, start, end);
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public boolean isPath(Coordinate c) {
        return this.data[c.getY()][c.getX()];
    }

    /**
     * Check to see if the movement is within bounds of the maze. This is only
     * needed if the loaded maze does not contain walls along all the edges.
     *
     * @param c Coordinate to check
     * @return true if Coordinate is in bounds of the underlying two-dimensional array
     */
    public boolean isInbounds(Coordinate c) {
        return (c.getY() >= 0 && c.getY() < height && c.getX() >= 0 && c.getX() < width);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void printMaze(List<Coordinate> path) {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (data[y][x]) {
                    if (path.contains(new Coordinate(x, y))) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Maze{width=" + width + ", height=" + height + ", start=" + start + ", end=" + end + '}';
    }
}
