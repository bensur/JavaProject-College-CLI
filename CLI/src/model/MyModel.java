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
	public void generateMaze(String mazeName, int floors, int rows, int columns, String alg) {
		Thread t = new Thread(new GenerateMaze(mazeName, floors, rows, columns, alg, this));
		t.start(); // TODO thread pool
	}
	
	@Override
	public void saveMaze(String mazeName, String fileName){
		Thread t = new Thread(new SaveMaze(mazeName, fileName, mazes, this, controller));
		t.start(); //TODO thread pool
	}
	
	@Override
	public void loadMaze(String mazeName, String fileName) {
		Thread t = new Thread(new LoadMaze(mazeName, fileName, mazes, this, controller));
		t.start(); //TODO thread pool
	}

	@Override
	public void solveMaze(String mazeName, String alg) {
		if (!mazes.containsKey(mazeName)) {
			controller.print("No such maze " + mazeName);
		} else {
			Thread t = new Thread(new SolveMaze(this, mazes.get(mazeName), mazeName, alg));
			t.start(); // TODO thread pool
		}
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
