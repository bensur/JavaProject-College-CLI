/**
 * 
 */
package model;

import java.util.HashMap;

import controller.Controller;
import mazeGenerators.algorithms.Maze3d;

/**
 * @author yschori
 *
 */
public class SaveMaze implements Runnable {
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
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (!mazes.containsKey(mazeName))
			controller.print("No such maze " + mazeName);
		else {
			model.saveMaze(mazes.get(mazeName), fileName);
		}
	}

}
