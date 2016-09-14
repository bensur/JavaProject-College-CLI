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
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public interface Model {
	/**
	 * @return the controller
	 */
	public Controller getController();

	/**
	 * @param controller to set
	 */
	public void setController(Controller controller);
	
	/**
	 * Save maze to file
	 * @param mazeName to save
	 * @param fileName to save to
	 */
	public void saveMaze(String mazeName, String fileName);
	
	/**
	 * Generate new maze
	 * @param mazeName to set
	 * @param floors to generate
	 * @param rows to generate
	 * @param columns to generate
	 */
	public void generateMaze(String mazeName, int floors, int rows, int columns, String alg);
	
	/**
	 * Solve maze
	 * @param mazeName to solve
	 * @param alg to use
	 */
	public void solveMaze(String mazeName, String alg);
	
	/**
	 * Load maze
	 * @param mazeName to load
	 * @param fileName to load from
	 */
	public void loadMaze(String mazeName, String fileName);
	
	/**
	 * Add maze to mazes HashMap
	 * @param mazeName to add
	 * @param maze to add
	 */
	public void addMaze(String mazeName, Maze3d maze);

	/**
	 * Add maze solution to solutions HashMap
	 * @param mazeName to add
	 * @param solution to add
	 */
	public void addMazeSolution(String mazeName, Solution<Position> solution);
	
	/**
	 * Exit from all threads
	 */
	public void exit();
	
	/**
	 * Return mazes HashMap
	 * @return mazes HashMap
	 */
	public HashMap<String, Maze3d> getMazes();
	
	/**
	 * Return solutions HashMap
	 * @return solutions HashMap
	 */
	HashMap<String, Solution<Position>> getSolutions();
}
