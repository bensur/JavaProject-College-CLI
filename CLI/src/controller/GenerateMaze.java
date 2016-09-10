/**
 * 
 */
package controller;

import model.Model;

/**
 * @author bensu
 *
 */
public class GenerateMaze implements Command {
	private String mazeName;
	private int floors;
	private int rows;
	private int columns;
	private String alg;
	private Model model;
	
	/**
	 * C'tor
	 * @param mazeName
	 * @param floors
	 * @param rows
	 * @param columns
	 * @param model
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
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		model.GenerateMaze(mazeName, floors, rows, columns, alg);
	}

}
