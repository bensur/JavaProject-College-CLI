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
public class DisplayCrossSection implements Command {
	View view;
	String mazeName;
	HashMap<String, Maze3d> mazes;
	char axis;
	int index;
	
	/**
	 * C'tor
	 * @param controller
	 * @param view
	 */
	public DisplayCrossSection(View view, String mazeName, HashMap<String, Maze3d> mazes, char axis, int index) {
		this.view = view;
		this.mazeName = mazeName;
		this.mazes = mazes;
		this.axis = axis;
		this.index = index;
	}
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		StringBuilder sb = new StringBuilder();
		if (!mazes.containsKey(mazeName))
			sb.append("No such maze " + mazeName);
		else {
			Maze3d maze = mazes.get(mazeName);
			int[][] maze2d;
			if (axis == 'X')
				maze2d = maze.getCrossSectionByX(index);
			else if (axis == 'Y')
				maze2d = maze.getCrossSectionByY(index);
			else if (axis == 'Z')
				maze2d = maze.getCrossSectionByZ(index);
			else
				throw new IllegalArgumentException("No such axis " + axis);
			sb.append(maze2dToString(maze2d));
		}
		view.print(sb.toString());
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
