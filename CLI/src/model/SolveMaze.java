/**
 * 
 */
package model;

import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.SearchableMaze3d;
import algorithms.search.Searcher;
import controller.Controller;
import mazeGenerators.algorithms.GrowingTreeGenerator;
import mazeGenerators.algorithms.Maze3dGenerator;
import mazeGenerators.algorithms.Position;
import mazeGenerators.algorithms.SimpleMaze3dGenerator;
import mazeGenerators.algorithms.lastCellChooser;
import mazeGenerators.algorithms.randomCellChooser;

/**
 * @author yschori
 *
 */
public class SolveMaze implements Runnable {
	private Controller controller;
	private String mazeName;
	private String alg;
	
	public SolveMaze(Controller controller, String mazeName, String alg) {
		this.controller = controller;
		this.mazeName = mazeName;
		this.alg = alg;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Searcher search;
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
		controller.addMazeSolution(mazeName, search.search(alg));

	}

}
