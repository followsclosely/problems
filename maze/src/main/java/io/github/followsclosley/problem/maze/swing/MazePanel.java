package io.github.followsclosley.problem.maze.swing;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.AccountableMaze;

import javax.swing.*;
import java.awt.*;

/**
 * Renders a maze on a canvas that is DEFAULT_CELL_SIZE * maze.getLength() wide
 * and DEFAULT_CELL_SIZE * maze.getHeight() tall.
 */
public class MazePanel extends JPanel {

    private final int DEFAULT_CELL_SIZE = 20;
    private final Dimension DEFAULT_MINIMUM_SIZE;

    private final AccountableMaze maze;
    private final Color PATH_COLOR = Color.RED;
    private int stepNumber = 0;

    public MazePanel(AccountableMaze maze) {
        this.maze = maze;
        this.DEFAULT_MINIMUM_SIZE = new Dimension(maze.getWidth() * DEFAULT_CELL_SIZE, maze.getHeight() * DEFAULT_CELL_SIZE);
    }

    @Override
    public Dimension getPreferredSize() {
        return DEFAULT_MINIMUM_SIZE;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                Coordinate c = new Coordinate(x, y);
                if (maze.isPath(c)) {
                    int index = maze.getPath().indexOf(c);
                    if (index != -1 && index < stepNumber) {
                        g.setColor(PATH_COLOR);
                        g.fill3DRect(x * DEFAULT_CELL_SIZE, y * DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, true);
                    }

                } else {
                    g.setColor(getForeground());
                    g.fillRect(x * DEFAULT_CELL_SIZE, y * DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE);
                }
            }
        }
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
