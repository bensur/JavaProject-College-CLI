/**
 * 
 */
package controller;

import model.Model;

/**
 * @author yschori
 *
 */
public class Exit implements Command {
	private Model model;
	
	public Exit(Model model) {
		this.model = model;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		model.exit();
	}

}
