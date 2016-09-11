/**
 * 
 */
package controller;

import java.util.HashMap;

import algorithms.search.Solution;
import algorithms.search.State;
import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;
import view.View;

/**
 * @author yschori
 *
 */
public class DisplaySolution implements Command {
	View view;
	String mazeName;
	HashMap<String, Solution> solutions;
	
	public DisplaySolution(View view, String mazeName, HashMap<String, Solution> solutions) {
		this.view = view;
		this.mazeName = mazeName;
		this.solutions = solutions;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		Solution sol = new Solution();
		if (solutions.containsKey(mazeName)) {
			view.print(sol.toString());
		}
		else {
			view.print("No solution found for maze " + mazeName);
		}
	}

}
