package io.github.followsclosley.problem.maze.solvers;

import io.github.followsclosley.problem.Coordinate;
import io.github.followsclosley.problem.LinkedCoordinate;
import io.github.followsclosley.problem.maze.AccountableMaze;
import io.github.followsclosley.problem.maze.Maze;
import io.github.followsclosley.problem.maze.Solver;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Uses a Breadth-first search to solve the maze. This locates the most efficient path through the maze.
 *
 * <p>
 *   Breadth-first search (BFS) is an algorithm for searching a tree data structure for a node that satisfies a
 *   given property. It starts at the tree root and explores all nodes at the present depth prior to moving on
 *   to the nodes at the next depth level. Extra memory, usually a queue, is needed to keep track of the child
 *   nodes that were encountered but not yet explored.
 * </p>
 * <p>
 *   <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Breadth-first-tree.svg/300px-Breadth-first-tree.svg.png" />
 *   -https://en.wikipedia.org/wiki/Breadth-first_search
 * </p>
 */
public class BreadthFirstSolver implements Solver {

    private static final Coordinate[] SEARCH_DIRECTIONS = {
            new Coordinate(0, 1),
            new Coordinate(1, 0),
            new Coordinate(0, -1),
            new Coordinate(-1, 0)};

    @Override
    public List<Coordinate> solve(Maze maze) {
        //Create an AccountableMaze or use it if it already is an AccountableMaze
        AccountableMaze accountableMaze = ( maze instanceof AccountableMaze am) ? am : new AccountableMaze(maze);

        //This list will contain all the coordinates that need to be explored at any given point in time. For large
        //mazes this collection could get large. It will never be larger than the number of valid coordinates on a
        //maze.
        LinkedList<LinkedCoordinate> nextToExplore = new LinkedList<>();

        //Get the starting point and prime the nextToExplore collection.
        LinkedCoordinate start = new LinkedCoordinate(maze.getStart(), null);
        nextToExplore.add(start);
        accountableMaze.visit(start);

        while (!nextToExplore.isEmpty()) {
            LinkedCoordinate coordinate = nextToExplore.remove();

            //If we are at the end then use the current coordinate to build the path to the end.
            if (maze.getEnd().equals(coordinate)) {
                accountableMaze.getPath().addAll(coordinate.getPath());
                return accountableMaze.getPath();
            }

            //Locate all the valid coordinates that can be moved to from the current coordinate.
            for (Coordinate movement : SEARCH_DIRECTIONS) {
                Coordinate next = coordinate.translate(movement);
                if (accountableMaze.isInbounds(next) && accountableMaze.visit(next) ) {
                    nextToExplore.add(new LinkedCoordinate(next, coordinate));
                }
            }
        }

        return Collections.emptyList();
    }


}
