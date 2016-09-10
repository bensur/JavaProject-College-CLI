/**
 * 
 */
package model;

import controller.Controller;
import mazeGenerators.algorithms.GrowingTreeGenerator;
import mazeGenerators.algorithms.Maze3dGenerator;
import mazeGenerators.algorithms.SimpleMaze3dGenerator;
import mazeGenerators.algorithms.lastCellChooser;
import mazeGenerators.algorithms.randomCellChooser;

/**
 * @author bensu
 *
 */
public class GenerateMaze implements Runnable {
	private String mazeName;
	private int floors;
	private int rows;
	private int columns;
	private String alg;
	private Controller controller;
	
	/**
	 * 
	 * @param mazeName
	 * @param floors
	 * @param rows
	 * @param columns
	 * @param controller
	 */
	public GenerateMaze(String mazeName, int floors, int rows, int columns, String alg, Controller controller) {
		this.mazeName = mazeName;
		this.floors = floors;
		this.rows = rows;
		this.columns = columns;
		this.alg = alg;
		this.controller = controller;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Maze3dGenerator gen;
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
		controller.addMaze(mazeName, gen.generate(rows, columns, floors));
	}
	
}
