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

import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerators.algorithms.Maze3d;

/**
 * @author yschori
 *
 */
public class MyModel implements Model {
	private Controller controller;

	
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
		Thread t = new Thread(new GenerateMaze(mazeName, floors, rows, columns, alg, controller));
		t.start(); //TODO thread pool
	}
	
	public void SolveMaze(String mazeName, String alg) {
		Maze3d maze;
		Thread t = new Thread(new SolveMaze(controller, maze, mazeName, alg));
		t.start(); //TODO thread pool
	}
	
	@Override
	public void loadMaze(String mazeName, String fileName) {
		InputStream in;
		try {
			in = new MyDecompressorInputStream(
			new FileInputStream("1.maz"));
			byte[] sizeArr = new byte[4];
			for (int i = 0 ; i < sizeArr.length ; i++)
				sizeArr[i] = (byte)in.read();
			int size = ((sizeArr[0] * sizeArr[1]) + (sizeArr[2] * sizeArr[3]));
			byte b[]=new byte[size];
			in.read(b);
			in.close();
			controller.addMaze(mazeName, new Maze3d(b));
		} catch (FileNotFoundException e) {
			controller.print("Cannot access '" + fileName + "': No such file");
			e.printStackTrace();
		} catch (IOException e) {
			controller.print("IOException occured while loading from '" + fileName +"'");
			e.printStackTrace();
		}
	}
}
