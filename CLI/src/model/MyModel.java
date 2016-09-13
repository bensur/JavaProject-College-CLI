/**
 * 
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import algorithms.search.Solution;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;

/**
 * @author yschori
 *
 */
public class MyModel implements Model {
	private Controller controller;
	private HashMap<String, Maze3d> mazes = new HashMap<String, Maze3d>();
	private HashMap<String, Solution<Position>> solutions = new HashMap<String, Solution<Position>>();

	public MyModel(Controller controller) {
		this.controller = controller;
	}

	/**
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * @return mazes HashMap
	 */
	public HashMap<String, Maze3d> getMazes() {
		return mazes;
	}

	@Override
	public void saveMaze(Maze3d maze, String fileName) {
		OutputStream out;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			controller.print("Cannot access '" + fileName + "': No such file");
			e.printStackTrace();
		} catch (IOException e) {
			controller.print("IOException occured while saving to '" + fileName + "'");
			e.printStackTrace();
		}
	}

	@Override
	public void GenerateMaze(String mazeName, int floors, int rows, int columns, String alg) {
		Thread t = new Thread(new GenerateMaze(mazeName, floors, rows, columns, alg, this));
		t.start(); // TODO thread pool
	}
	
	@Override
	public void SolveMaze(String mazeName, String alg) {
		if (!mazes.containsKey(mazeName)) {
			controller.print("No such maze " + mazeName);
		} else {
			Thread t = new Thread(new SolveMaze(this, mazes.get(mazeName), mazeName, alg));
			t.start(); // TODO thread pool
		}
	}
	
	public void SaveMaze(String mazeName, String fileName, HashMap<String, Maze3d> mazes, Model model, Controller controller) {
		Maze3d maze;
		Thread t = new Thread(new SaveMaze(mazeName, fileName, mazes, model, controller));
		t.start(); //TODO thread pool
	}
	
	public void LoadMaze(String mazeName, String fileName, HashMap<String, Maze3d> mazes, Model model, Controller controller) {
		Maze3d maze;
		Thread t = new Thread(new LoadMaze(mazeName, fileName, mazes, model, controller));
		t.start(); //TODO thread pool
	}
	
	@Override
	public void addMaze(String mazeName, Maze3d maze) {
		if (mazes.containsKey(mazeName))
			controller.print("Maze " + mazeName + " already exist!");
		else
			mazes.put(mazeName, maze);
	}

	@Override
	public void addMazeSolution(String mazeName, Solution<Position> solution) {
		if (solutions.containsKey(mazeName))
			controller.print("Solution for maze " + mazeName + " already exist!");
		else
			solutions.put(mazeName, solution);
	}
}
