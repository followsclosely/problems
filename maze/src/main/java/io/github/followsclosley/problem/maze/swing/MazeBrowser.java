package io.github.followsclosley.problem.maze.swing;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.maze.AccountableMaze;
import io.github.followsclosley.problem.maze.DefaultMaze;
import io.github.followsclosley.problem.maze.solvers.BreadthFirstSolver;
import io.github.followsclosley.problem.maze.solvers.DepthFirstSearchSolver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

public class MazeBrowser {

    public static void main(String[] args) throws IOException, URISyntaxException {
        AccountableMaze maze = new AccountableMaze(DefaultMaze.of(Path.of(ClassLoader.getSystemResource("maze001.txt").toURI())));
        List<Coordinate> path = new DepthFirstSearchSolver().solve(maze);
        new MazeBrowser().show(maze);
    }

    public void show(AccountableMaze maze) {
        MazePanel mazePanel = new MazePanel(maze);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, maze.getPath().size(), 0);
        slider.addChangeListener(event -> {
            mazePanel.setStepNumber(slider.getValue());
            SwingUtilities.invokeLater(mazePanel::repaint);
        });

        JCheckBox showFailedSteps = new JCheckBox("Show Failed Steps");
        showFailedSteps.addActionListener(event -> {
            mazePanel.setShowFailedSteps(showFailedSteps.isSelected());
            slider.setValue(0);
            slider.setMaximum(maze.getMoves().size());
            mazePanel.setStepNumber(0);
        });

        JPanel controls = new JPanel(new BorderLayout());
        controls.add(showFailedSteps, BorderLayout.WEST);
        controls.add(slider, BorderLayout.CENTER);

        JFrame frame = new JFrame("Maze Browser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.add(mazePanel, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);
        frame.pack();

        frame.setVisible(true);
    }
}
