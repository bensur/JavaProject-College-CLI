/**
 * 
 */
package controller;

import java.util.HashMap;

import mazeGenerators.algorithms.Maze3d;
import model.Model;
import view.View;

/**
 * @author bensu
 *
 */
public class SaveMaze implements Command {
	private String mazeName;
	private String fileName;
	private HashMap<String, Maze3d> mazes;
	private Model model;
	private Controller controller;
	
	/**
	 * C'tor
	 * @param mazeName
	 * @param fileName
	 * @param mazes
	 * @param model
	 * @param controller
	 */
	public SaveMaze(String mazeName, String fileName, HashMap<String, Maze3d> mazes, Model model, Controller controller) {
		this.mazeName = mazeName;
		this.fileName = fileName;
		this.mazes = mazes;
		this.model = model;
		this.controller = controller;
	}
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		if (!mazes.containsKey(mazeName))
			controller.print("No such maze " + mazeName);
		else {
			model.saveMaze(mazes.get(mazeName), fileName);
		}
	}

}
