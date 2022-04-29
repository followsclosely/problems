package io.github.followsclosley.problem.maze;

import io.github.followsclosley.problem.Coordinate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * 0 -> Road
 * 1 -> Wall
 * 2 -> Maze entry
 * 3 -> Maze exit
 */
public class ImmutableMaze implements Maze {

    private final int width, height;
    private final boolean[][] data;
    private final Coordinate start;
    private final Coordinate end;

    private ImmutableMaze(boolean[][] data, Coordinate start, Coordinate end) {
        this.data = data;
        this.height = data.length;
        this.width = data[0].length;
        this.start = start;
        this.end = end;
    }

    public static ImmutableMaze of(Path path) throws IOException {

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

        return new ImmutableMaze(data, start, end);
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
                    if( path.contains(new Coordinate(x, y))){
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print("X");
                }
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
