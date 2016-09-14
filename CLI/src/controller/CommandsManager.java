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
	 * 
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
	 * 
	 * @author Ben Surkiss & Yovel Shchori
	 *
	 */
	public class Dir implements Command {
		private File file;
		private String[] filesList;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String[] args) {
			this.file = new File(args[1]);
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
	 * 
	 * @author Ben Surkiss & Yovel Shchori
	 * TODO - Get maze from model not mazes hashmap
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
		public void doCommand(String args[]) {
			this.mazeName = args[1];
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
		 * TODO - change pringln to sb.append
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
	/**
	 * TODO - same as above TODO!
	 * @author Ben Surkiss & Yovel Shchori
	 *
	 */
	public class DisplayCrossSection implements Command {
		private Controller controller;
		private String mazeName;
		private HashMap<String, Maze3d> mazes;
		private char axis;
		private int index;
		/**
		 * C'tor
		 * @param controller
		 * @param view
		 */
		public DisplayCrossSection(Controller controller, String mazeName, HashMap<String, Maze3d> mazes, char axis, int index) {
			this.controller = controller;
			this.mazeName = mazeName;
			this.mazes = mazes;
			this.axis = axis;
			this.index = index;
		}
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
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
		 * TODO - change pringln to sb.append
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
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 * 
	 */
	public class DisplaySolution implements Command {
		View view;
		String mazeName;
		HashMap<String, Solution<Position>> solutions; //TODO get from model
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[1];
			Solution<Position> sol = new Solution<Position>();
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
	 *
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
	 *
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
			this.mazeName = args[1];
			this.floors = Integer.parseInt(args[2]);
			this.rows = Integer.parseInt(args[3]);
			this.columns = Integer.parseInt(args[4]);
			this.alg = args[5];
			model.generateMaze(mazeName, floors, rows, columns, alg);
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 *
	 */
	public class LoadMaze implements Command {
		private String mazeName;
		private String fileName;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[1];
			this.fileName = args[2];
			model.loadMaze(mazeName, fileName);
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 *
	 */
	public class SaveMaze implements Command {
		private String mazeName;
		private String fileName;
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[1];
			this.fileName = args[2];
			model.saveMaze(mazeName, fileName);
		}
	}
	/**
	 * @author Ben Surkiss & Yovel Shchori
	 *
	 */
	public class SolveMaze implements Command {
		private String mazeName;
		private String alg;
		
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand(String args[]) {
			this.mazeName = args[1];
			this.alg = args[2];
			model.solveMaze(mazeName, alg);
		}
	}
}
