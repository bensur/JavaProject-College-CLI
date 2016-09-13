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
	private Model model;
	
	/**
	 * 
	 * @param mazeName
	 * @param floors
	 * @param rows
	 * @param columns
	 * @param controller
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
		model.addMaze(mazeName, gen.generate(rows, columns, floors));
	}
	
}
