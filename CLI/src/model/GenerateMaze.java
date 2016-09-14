/**
 * 
 */
package model;

import mazeGenerators.algorithms.GrowingTreeGenerator;
import mazeGenerators.algorithms.Maze3dGenerator;
import mazeGenerators.algorithms.SimpleMaze3dGenerator;
import mazeGenerators.algorithms.lastCellChooser;
import mazeGenerators.algorithms.randomCellChooser;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public class GenerateMaze implements Runnable {
	private String mazeName;
	private int floors;
	private int rows;
	private int columns;
	private String alg;
	private Model model;
	
	/**
	 * C'tor
	 * @param mazeName to save in mazes HashMap
	 * @param floors to generate in maze
	 * @param rows to generate in maze
	 * @param columns to generate in maze
	 * @param model to return maze to
	 */
	public GenerateMaze(String mazeName, int floors, int rows, int columns, String alg, Model model) {
		this.mazeName = mazeName;
		this.floors = floors;
		this.rows = rows;
		this.columns = columns;
		this.alg = alg;
		this.model = model;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Maze3dGenerator gen;
		// Choose algorithm based on given alg
		switch (alg) {
		case "GrowingTreeRand":
			gen = new GrowingTreeGenerator(new randomCellChooser());
			break;
		case "GrowingTreeLast":
			gen = new GrowingTreeGenerator(new lastCellChooser());
			break;
		case "Simple":
			gen = new SimpleMaze3dGenerator();
			break;
		default:
			throw new IllegalArgumentException("No such algorithm '" + alg + "'");
		}
		// Generate maze and add to model
		model.addMaze(mazeName, gen.generate(rows, columns, floors));
	}
	
}
