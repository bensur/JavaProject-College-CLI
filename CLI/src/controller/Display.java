/**
 * 
 */
package controller;

import java.util.HashMap;

import mazeGenerators.algorithms.Maze3d;
import view.View;

/**
 * @author bensu
 *
 */
public class Display implements Command {
	private Controller controller;
	private String mazeName;
	private HashMap<String, Maze3d> mazes;
	
	/**
	 * C'tor
	 * @param controller
	 * @param view
	 */
	public Display(Controller controller, String mazeName, HashMap<String, Maze3d> mazes) {
		this.controller = controller;
		this.mazeName = mazeName;
		this.mazes = mazes;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		// Add error msg if no such mazeName
		StringBuilder sb = new StringBuilder();
		if (!mazes.containsKey(mazeName)) {
			sb.append("No such maze " + mazeName);
		} else { // Convert maze to string
			Maze3d maze = mazes.get(mazeName);
			for (int z = 0 ; z < maze.getFlos() -1 ; z++) {
				sb.append(maze2dToString(maze.getCrossSectionByZ(z)) + "\n");
			}
			sb.append(maze2dToString(maze.getCrossSectionByZ(maze.getFlos() -1)));
		}
		// Print using view pring method
		controller.print(sb.toString());
	}
	
	/**
	 * 
	 * @param maze 2d int representation of maze //TODO change to Maze3d method
	 */
	private String maze2dToString(int[][] maze) {
		StringBuilder sb = new StringBuilder();
		System.out.println("{");
		for (int i = 0 ; i < (maze.length - 1) ; i++) {
			System.out.print("\t{");
			for (int j = 0 ; j < (maze[i].length - 1) ; j++)
				System.out.print(maze[i][j] + ", ");
			System.out.println(maze[i][maze[i].length - 1] + "},");
		}
		System.out.print("\t{");
		for (int i = 0 ; i < (maze[maze.length - 1].length - 1) ; i++)
			System.out.print(maze[maze.length - 1][i] + ", ");
		System.out.println(maze[maze.length - 1][maze[maze.length - 1].length - 1] + "}");
		System.out.println("}");
		return sb.toString();
	}
	
}
