/**
 * 
 */
package model;

import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.SearchableMaze3d;
import algorithms.search.Searcher;
import controller.Controller;
import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;

/**
 * @author yschori
 *
 */
public class SolveMaze implements Runnable {
	private Controller controller;
	private Maze3d maze;
	private String mazeName;
	private String alg;
	
	public SolveMaze(Controller controller, Maze3d maze, String mazeName, String alg) {
		this.controller = controller;
		this.maze = maze;
		this.mazeName = mazeName;
		this.alg = alg;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Searcher<Position> search;
		switch (alg) {
		case "BFS":
			search = new BFS<Position>();
			break;
		case "DFS":
			search = new DFS<Position>();
			break;
		default:
			throw new IllegalArgumentException("No such algorithm '" + alg + "'");
		}
		controller.addMazeSolution(mazeName, search.search(new SearchableMaze3d(maze)));

	}

}
