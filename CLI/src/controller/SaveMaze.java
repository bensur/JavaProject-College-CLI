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
	private View view;
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		if (!mazes.containsKey(mazeName))
			view.print("No such maze " + mazeName);
		else {
			model.saveMaze(mazes.get(mazeName), fileName);
		}
	}

}
