/**
 * 
 */
package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import controller.Controller;
import io.MyCompressorOutputStream;
import mazeGenerators.algorithms.Maze3d;

/**
 * @author yschori
 *
 */
public class SaveMaze implements Runnable {
	private String mazeName;
	private String fileName;
	private HashMap<String, Maze3d> mazes;
	private Model model;
	private Controller controller;
	
	/**
	 * C'tor
	 * @param mazeName
	 * @param fileName
	 * @param mazes
	 * @param model
	 * @param controller
	 */
	public SaveMaze(String mazeName, String fileName, HashMap<String, Maze3d> mazes, Model model, Controller controller) {
		this.mazeName = mazeName;
		this.fileName = fileName;
		this.mazes = mazes;
		this.model = model;
		this.controller = controller;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (!mazes.containsKey(mazeName))
			controller.print("No such maze " + mazeName);
		else {
			OutputStream out;
			try {
				out = new MyCompressorOutputStream(new FileOutputStream(fileName));
				out.write(mazes.get(mazeName).toByteArray());
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
	}

}