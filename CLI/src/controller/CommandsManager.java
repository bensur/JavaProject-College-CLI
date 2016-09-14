/**
 * 
 */
package controller;

import java.io.File;
import java.util.HashMap;

import algorithms.search.Solution;
import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;
import model.Model;
import view.View;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public class CommandsManager {
	protected Model model;
	protected View view;
	protected Controller controller;
	/**
	 * 
	 * @param model
	 * @param view
	 * @param controller
	 */
	public CommandsManager(Model model, View view, Controller controller) {
		this.model = model;
		this.view = view;
		this.controller = controller;
	}
	/**
	 * gets the commands map
	 * @return commands HashMap
	 */
	public HashMap<String, Command> getCommands() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("dir", new Dir());
		commands.put("generate_maze", new GenerateMaze());
		commands.put("display", new Display());
		commands.put("display_cross_section", new DisplayCrossSection());
		commands.put("save_maze", new SaveMaze());
		commands.put("load_maze", new LoadMaze());
		commands.put("solve", new SolveMaze());
		commands.put("display_solution", new DisplaySolution());
		commands.put("exit", new Exit());
		return commands;
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * dir command, displays the folders and files in the given directory
	 */
	public class Dir implements Command {
		private File file;
		private String[] filesList;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String[] args) {
			this.file = new File(args[0]);
			if(file.isDirectory()) {
				filesList = file.list();
				for (int i = 0; i < filesList.length; i++) {
					controller.print(filesList[i]);
				}
			}
			else {
				controller.print("Not a directory");
			}
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * display command, display the maze of the given maze name
	 */
	public class Display implements Command {
		private String mazeName;
		
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[0];
			HashMap <String, Maze3d> mazes = model.getMazes();
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
			// Print using controller print method
			controller.print(sb.toString());
		}
		/**
		 * representation of maze2d
		 * @param maze 2d int representation of maze //TODO - change to global function
		 */
		private String maze2dToString(int[][] maze) {
			StringBuilder sb = new StringBuilder();
			sb.append("{\n");
			for (int i = 0 ; i < (maze.length - 1) ; i++) {
				sb.append("\t{");
				for (int j = 0 ; j < (maze[i].length - 1) ; j++)
					sb.append(maze[i][j] + ", ");
				sb.append(maze[i][maze[i].length - 1] + "},\n");
			}
			sb.append("\t{");
			for (int i = 0 ; i < (maze[maze.length - 1].length - 1) ; i++)
				sb.append(maze[maze.length - 1][i] + ", ");
			sb.append(maze[maze.length - 1][maze[maze.length - 1].length - 1] + "}\n");
			sb.append("}");
			return sb.toString();
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * display_cross_section command, displays the cross section maze of the given maze name, axis and index
	 */
	public class DisplayCrossSection implements Command {
		private String mazeName;
		private HashMap<String, Maze3d> mazes;
		private char axis;
		private int index;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[0];
			this.mazes = model.getMazes();
			this.axis = (args[1]).toCharArray()[0];
			this.index = Integer.parseInt(args[2]);
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
			controller.print(sb.toString());
		}
		/**
		 * 
		 * @param maze 2d int representation of maze //TODO - change to global function
		 */
		private String maze2dToString(int[][] maze) {
			StringBuilder sb = new StringBuilder();
			sb.append("{\n");
			for (int i = 0 ; i < (maze.length - 1) ; i++) {
				sb.append("\t{");
				for (int j = 0 ; j < (maze[i].length - 1) ; j++)
					sb.append(maze[i][j] + ", ");
				sb.append(maze[i][maze[i].length - 1] + "},\n");
			}
			sb.append("\t{");
			for (int i = 0 ; i < (maze[maze.length - 1].length - 1) ; i++)
				sb.append(maze[maze.length - 1][i] + ", ");
			sb.append(maze[maze.length - 1][maze[maze.length - 1].length - 1] + "}\n");
			sb.append("}");
			return sb.toString();
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * display_solution command, displays the solution of the maze of the given maze name
	 */
	public class DisplaySolution implements Command {
		String mazeName;
		HashMap<String, Solution<Position>> solutions;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[0];
			this.solutions = model.getSolutions();
			Solution<Position> sol = solutions.get(mazeName);
			if (solutions.containsKey(mazeName)) {
				controller.print(sol.toString());
			}
			else {
				controller.print("No solution found for maze " + mazeName);
			}
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * exit command, exits the cli
	 */
	public class Exit implements Command {
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			model.exit();
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * generate_maze command, generates a maze with the given maze name, floors number, rows number, columns number and a generating algorithm
	 */
	public class GenerateMaze implements Command {
		private String mazeName;
		private int floors;
		private int rows;
		private int columns;
		private String alg;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[0];
			this.floors = Integer.parseInt(args[1]);
			this.rows = Integer.parseInt(args[2]);
			this.columns = Integer.parseInt(args[3]);
			this.alg = args[4];
			model.generateMaze(mazeName, floors, rows, columns, alg);
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * load_maze command, loads a saved and compressed maze of the given maze name into a given file name
	 */
	public class LoadMaze implements Command {
		private String mazeName;
		private String fileName;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[0];
			this.fileName = args[1];
			model.loadMaze(mazeName, fileName);
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * save_maze command, compresses and saves a maze of the given maze name into a given file name
	 */
	public class SaveMaze implements Command {
		private String mazeName;
		private String fileName;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[0];
			this.fileName = args[1];
			model.saveMaze(mazeName, fileName);
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * solve command, solves the maze of the given maze name using the given algorithm
	 */
	public class SolveMaze implements Command {
		private String mazeName;
		private String alg;
		
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[0];
			this.alg = args[1];
			model.solveMaze(mazeName, alg);
		}
	}
}
