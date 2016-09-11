/**
 * 
 */
package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import controller.Controller;
import io.MyCompressorOutputStream;
import mazeGenerators.algorithms.Maze3d;

/**
 * @author yschori
 *
 */
public class MyModel implements Model {
	private Controller controller;

	/**
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void saveMaze(Maze3d maze, String fileName) {
		OutputStream out;
		try {
			out = new MyCompressorOutputStream(
			new FileOutputStream(fileName));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			controller.print("Cannot access '" + fileName + "': No such file");
			e.printStackTrace();
		} catch (IOException e) {
			controller.print("IOException occured while saving to '" + fileName +"'");
			e.printStackTrace();
		}
	}

	@Override
	public void GenerateMaze(String mazeName, int floors, int rows, int columns, String alg) {
		// TODO Auto-generated method stub
		//Thread t = new Thread(new GenerateMaze());
	}
	
	public void SolveMaze(String mazeName, String alg) {
		// TODO Auto-generated method stub
		//Thread t = new Thread(new SolveMaze());
	}
}
