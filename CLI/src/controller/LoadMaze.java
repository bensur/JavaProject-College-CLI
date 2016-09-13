/**
 * 
 */
package controller;

import java.util.HashMap;

import mazeGenerators.algorithms.Maze3d;
import model.Model;

/**
 * @author bensu
 *
 */
public class LoadMaze implements Command {
	private String mazeName;
	private String fileName;
	private Model model;
	
	/**
	 * C'tor
	 * @param mazeName
	 * @param fileName
	 * @param mazes
	 * @param model
	 * @param controller
	 */
	public LoadMaze(String mazeName, String fileName, Model model) {
		this.mazeName = mazeName;
		this.fileName = fileName;
		this.model = model;
	}
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		model.loadMaze(mazeName, fileName);
	}

}
