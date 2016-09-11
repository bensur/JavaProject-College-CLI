/**
 * 
 */
package controller;

import algorithms.search.Solution;
import mazeGenerators.algorithms.Maze3d;
import model.Model;
import view.View;

/**
 * @author yschori
 *
 */
public interface Controller {
	/**
	 * @return the view
	 */
	public View getView();

	/**
	 * @param view the view to set
	 */
	public void setView(View view);

	/**
	 * @return the model
	 */
	public Model getModel();

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model);
	
	/**
	 * 
	 * @param string
	 */
	public void print(String string);
	
	/**
	 * 
	 * @param mazeName
	 * @param generate
	 */
	public void addMaze(String mazeName, Maze3d maze);
	public void addMazeSolution(String mazeName, Solution solution);
}
