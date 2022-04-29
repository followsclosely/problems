package io.github.followsclosley.problem.maze.swing;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.AccountableMaze;
import io.github.followsclosley.problem.maze.DefaultMaze;
import io.github.followsclosley.problem.maze.solvers.BreadthFirstSolver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

public class MazeBrowser {

    public static void main(String[] args) throws IOException, URISyntaxException {
        AccountableMaze maze = new AccountableMaze(DefaultMaze.of(Path.of(ClassLoader.getSystemResource("maze001.txt").toURI())));
        List<Coordinate> path = new BreadthFirstSolver().solve(maze);
        new MazeBrowser().show(maze);
    }

    public void show(AccountableMaze maze) {
        MazePanel mazePanel = new MazePanel(maze);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, maze.getPath().size(), 0);
        slider.addChangeListener(event -> {
            mazePanel.setStepNumber(slider.getValue());
            SwingUtilities.invokeLater(mazePanel::repaint);
        });

        JFrame frame = new JFrame("Maze Browser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.add(mazePanel, BorderLayout.CENTER);
        frame.add(slider, BorderLayout.SOUTH);
        frame.pack();

        frame.setVisible(true);
    }
}
