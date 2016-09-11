/**
 * 
 */
package controller;

import java.util.HashMap;

import algorithms.search.Solution;
import mazeGenerators.algorithms.Maze3d;
import model.Model;
import view.View;

/**
 * @author yschori
 *
 */
public class MyController implements Controller {
	private View view;
	private Model model;
	private HashMap<String, Maze3d> mazes;
	private HashMap<String, Solution> solutions;

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	
	/**
	 * @return mazes HashMap
	 */
	public HashMap<String, Maze3d> getMazes() {
		return mazes;
	}
	
	/**
	 * 
	 */
	@Override
	public void print(String string) {
		view.print(string);
	}

	@Override
	public void addMaze(String mazeName, Maze3d maze) {
		// TODO Auto-generated method stub
		mazes.put(mazeName, maze);
		print("Maze '" + mazeName + "' is ready");
	}

	@Override
	public void addMazeSolution(String mazeName, Solution solution) {
		solutions.put(mazeName, solution);
		print("Solution for '" + mazeName + "' is ready");
	}
}
