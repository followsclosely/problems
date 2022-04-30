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

    private final int DEFAULT_CELL_SIZE = 10;
    private final Dimension DEFAULT_MINIMUM_SIZE;

    private final AccountableMaze maze;

    private final Color PATH_COLOR = Color.RED;
    private final Color BACKTRACK_COLOR = Color.LIGHT_GRAY;

    private int stepNumber = 0;
    private boolean showFailedSteps = false;

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
                if (!maze.isPath(c)) {
                    g.setColor(getForeground());
                    g.fillRect(x * DEFAULT_CELL_SIZE, y * DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE);
                }
            }
        }

        if (showFailedSteps){
            paintAllSteps(g);
        } else {
            paintSolution(g);
        }
    }

    public void paintAllSteps(Graphics g) {
        for (int i = 0; i < stepNumber; i++) {
            AccountableMaze.Move move = maze.getMoves().get(i);
            g.setColor(maze.getPath().contains(move.getCoordinate()) ? PATH_COLOR : BACKTRACK_COLOR);
            g.setColor(AccountableMaze.Move.MoveType.EXPLORE == move.getMoveType() ? PATH_COLOR : BACKTRACK_COLOR);
            g.fill3DRect(move.getCoordinate().getX() * DEFAULT_CELL_SIZE, move.getCoordinate().getY() * DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, true);
        }
    }

    public void paintSolution(Graphics g) {
        for (int i = 0; i < stepNumber; i++) {
            Coordinate c = maze.getPath().get(i);
            g.setColor(PATH_COLOR);
            g.fill3DRect(c.getX() * DEFAULT_CELL_SIZE, c.getY() * DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE, true);
        }
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void setShowFailedSteps(boolean showFailedSteps) {
        this.showFailedSteps = showFailedSteps;
    }
}
