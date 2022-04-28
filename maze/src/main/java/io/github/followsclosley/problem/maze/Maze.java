package io.github.followsclosley.problem.maze;

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
 *
 */
public class Maze {

    private final int[][] data;

    private Maze(int[][] data) {
        this.data = data;
    }

    public int getHeight() {
        return data.length;
    }

    public int getWidth() {
        return data[0].length;
    }

    public void printMaze() {
        for(int y=0; y<getHeight(); y++){
            for(int x=0; x<getWidth(); x++){

                String value = switch(data[y][x]){
                    case 1 -> "#";
                    default -> " ";
                };

                System.out.print(value);
            }
            System.out.println();
        }
    }

    public static Maze of(Path path) throws IOException {

        String content = Files.readString(path, StandardCharsets.US_ASCII);
        String[] lines = content.split("\r\n|\r|\n");
        int[][] data = new int[lines.length][];

        for(int y=0; y<lines.length; y++){
            String[] values = lines[y].split("");
            data[y] = new int[values.length];
            for(int x=0; x<values.length; x++){
                data[y][x] = Integer.parseInt(values[x].trim());
            }
        }

        return new Maze(data);
    }
}
