/**
 * 
 */
package model;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import algorithms.search.Solution;
import controller.Controller;
import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public class MyModel implements Model {
	private Controller controller;
	private HashMap<String, Maze3d> mazes = new HashMap<String, Maze3d>();
	private HashMap<String, Solution<Position>> solutions = new HashMap<String, Solution<Position>>();
	private ExecutorService executor = Executors.newCachedThreadPool();
	/* (non-Javadoc)
	 * @see model.Model#getController()
	 */
	@Override
	public Controller getController() {
		return controller;
	}
	/* (non-Javadoc)
	 * @see model.Model#setController(Controller controller)
	 */
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	/* (non-Javadoc)
	 * @see model.Model#getMazesr()
	 */
	@Override
	public HashMap<String, Maze3d> getMazes() {
		return this.mazes;
	}
	/* (non-Javadoc)
	 * @see model.Model#getSolutions()
	 */
	@Override
	public HashMap<String, Solution<Position>> getSolutions() {
		return this.solutions;
	}
	/* (non-Javadoc)
	 * @see model.Model#generateMaze()
	 */
	@Override
	public void generateMaze(String mazeName, int floors, int rows, int columns, String alg) {
		executor.execute(new GenerateMaze(mazeName, floors, rows, columns, alg, this));
	}
	/* (non-Javadoc)
	 * @see model.Model#saveMaze()
	 */
	@Override
	public void saveMaze(String mazeName, String fileName){
		executor.execute(new SaveMaze(mazeName, fileName, mazes, this, controller));
	}
	
	@Override
	public void loadMaze(String mazeName, String fileName) {
		executor.execute(new LoadMaze(mazeName, fileName, mazes, this, controller));
	}

	@Override
	public void solveMaze(String mazeName, String alg) {
		if (!mazes.containsKey(mazeName)) {
			controller.print("No such maze " + mazeName);
		} else {
			executor.execute(new SolveMaze(this, mazes.get(mazeName), mazeName, alg));
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
	
	@Override
	public void exit() {
		try {
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
