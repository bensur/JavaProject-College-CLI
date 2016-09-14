/**
 * 
 */
package model;

import java.util.HashMap;

import algorithms.search.Solution;
import controller.Controller;
import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;

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
	public void saveMaze(String mazeName, String fileName);
	
	/**
	 * 
	 * @param mazeName
	 * @param floors
	 * @param rows
	 * @param columns
	 */
	public void generateMaze(String mazeName, int floors, int rows, int columns, String alg);
	
	/**
	 * 
	 * @param mazeName
	 * @param alg
	 */
	public void solveMaze(String mazeName, String alg);
	
	/**
	 * 
	 * @param mazeName
	 * @param fileName
	 */
	public void loadMaze(String mazeName, String fileName);
	
	/**
	 * 
	 * @param mazeName
	 * @param generate
	 */
	public void addMaze(String mazeName, Maze3d generate);

	/**
	 * 
	 * @param mazeName
	 * @param search
	 */
	public void addMazeSolution(String mazeName, Solution<Position> search);
	
	/**
	 * 
	 */
	public void exit();
	
	/**
	 * 
	 * @return mazes
	 */
	public HashMap<String, Maze3d> getMazes();
	
	/**
	 * 
	 * @return solutions
	 */
	HashMap<String, Solution<Position>> getSolutions();
}
