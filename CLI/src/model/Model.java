/**
 * 
 */
package model;

import controller.Controller;
import mazeGenerators.algorithms.Maze3d;

/**
 * @author yschori
 *
 */
public interface Model {
	/**
	 * @return the controller
	 */
	public Controller getController();

	/**
	 * @param controller the controller to set
	 */
	public void setController(Controller controller);
	
	/**
	 * 
	 * @param mazeName
	 * @param fileName
	 */
	public void saveMaze(Maze3d maze, String fileName);
}
