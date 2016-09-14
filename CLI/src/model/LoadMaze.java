/**
 * 
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import controller.Controller;
import io.MyDecompressorInputStream;
import mazeGenerators.algorithms.Maze3d;

/**
 * @author yschori
 *
 */
public class LoadMaze implements Runnable {
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
	public LoadMaze(String mazeName, String fileName, HashMap<String, Maze3d> mazes, Model model, Controller controller) {
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
		if (mazes.containsKey(mazeName))
			controller.print("Maze '" + mazeName + "' already exist!");
		else {
			InputStream in;
			try {
				in = new MyDecompressorInputStream(new FileInputStream(fileName));
				byte[] sizeArr = new byte[4];
				for (int i = 0; i < sizeArr.length; i++)
					sizeArr[i] = (byte) in.read();
				int size = ((sizeArr[0] * sizeArr[1]) + (sizeArr[2] * sizeArr[3]));
				byte b[] = new byte[size];
				in.read(b);
				in.close();
				model.addMaze(mazeName, new Maze3d(b));
			} catch (FileNotFoundException e) {
				controller.print("Cannot access '" + fileName + "': No such file");
				e.printStackTrace();
			} catch (IOException e) {
				controller.print("IOException occured while loading from '" + fileName + "'");
				e.printStackTrace();
			}
		}
	}

}
