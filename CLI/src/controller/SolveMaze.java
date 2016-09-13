/**
 * 
 */
package controller;

import model.Model;

/**
 * @author yschori
 *
 */
public class SolveMaze implements Command {
	private Model model;
	private Controller controller;
	private String mazeName;
	private String alg;
	
	public SolveMaze(Model model, Controller controller, String mazeName, String alg) {
		this.model = model;
		this.controller = controller;
		this.mazeName = mazeName;
		this.alg = alg;
	}
	
	@Override
	public void doCommand() {
		model.SolveMaze(mazeName, alg);
	}
	
}
