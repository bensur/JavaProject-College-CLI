/**
 * 
 */
package controller;

import model.Model;

/**
 * @author bensu
 *
 */
public class SaveMaze implements Command {
	private String mazeName;
	private String fileName;
	private Model model;
	/**
	 * C'tor
	 * @param mazeName
	 * @param fileName
	 * @param mazes
	 * @param model
	 * @param controller
	 */
	public SaveMaze(String mazeName, String fileName, Model model, Controller controller) {
		this.mazeName = mazeName;
		this.fileName = fileName;
		this.model = model;
	}
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		model.saveMaze(mazeName, fileName);
	}

}
