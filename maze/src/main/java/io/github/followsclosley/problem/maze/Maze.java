package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 0 -> Road
 * 1 -> Wall
 * 2 -> Maze entry
 * 3 -> Maze exit
 * 4 -> Cell part of the path from entry to exit
 * 7 -> Visited
 */
public class Maze {

    private final int width, height;
    private final int[][] data;
    private final Coordinate start;
    private final Coordinate end;

    public Maze(Maze parent) {
        this.height = parent.getHeight();
        this.width = parent.getWidth();
        this.data = new int[height][];
        for (int y = 0; y < height; y++) {
            this.data[y] = parent.data[y].clone();
        }
        this.start = new Coordinate(parent.start.getX(), parent.start.getY());
        this.end = new Coordinate(parent.end.getX(), parent.end.getY());
    }

    private Maze(int[][] data, Coordinate start, Coordinate end) {
        this.height = data.length;
        this.width = data[0].length;
        this.data = data;
        this.start = start;
        this.end = end;
    }

    public static Maze of(Path path) throws IOException {

        Coordinate start = null;
        Coordinate end = null;

        String content = Files.readString(path, StandardCharsets.US_ASCII);
        String[] lines = content.split("\r\n|\r|\n");
        int[][] data = new int[lines.length][];

        for (int y = 0; y < lines.length; y++) {
            String[] values = lines[y].split("");
            data[y] = new int[values.length];
            for (int x = 0; x < values.length; x++) {
                data[y][x] = Integer.parseInt(values[x].trim());
                switch (data[y][x]) {
                    case 2 -> start = new Coordinate(x, y);
                    case 3 -> end = new Coordinate(x, y);
                }
            }
        }

        return new Maze(data, start, end);
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public boolean canMoveTo(Coordinate c) {
        int value = this.data[c.getY()][c.getX()];
        return value != 1;
    }

    public boolean visit(Coordinate c) {
        System.out.printf("visit(height=%s, width=%s) - %s%n", height, width, c);

        if (isInbounds(c)) {
            int value = this.data[c.getY()][c.getX()];
            if (value == 0 || value == 2) {
                this.data[c.getY()][c.getX()] = 7;
                return true;
            }
        }

        return false;
    }

    /**
     * Check to see if the movement is within bounds of the maze. This is only
     * needed if the loaded maze does not contain walls along all the edges.
     *
     * @param c Coordinate to check
     * @return true if Coordinate is in bounds of the underlying two-dimensional array
     */
    private boolean isInbounds(Coordinate c) {
        return (c.getY() >= 0 && c.getY() < height && c.getX() >= 0 && c.getX() < width);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void printMaze() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                String value = switch (data[y][x]) {
                    case 1 -> "#";
                    case 2 -> "S";
                    case 3 -> "F";
                    case 4 -> ".";
                    default -> " ";
                };
                System.out.print(value);
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Maze{" +
                "width=" + width +
                ", height=" + height +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
