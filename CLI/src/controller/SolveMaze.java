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
	private String mazeName;
	private String alg;
	
	public SolveMaze(Model model, String mazeName, String alg) {
		this.model = model;
		this.mazeName = mazeName;
		this.alg = alg;
	}
	
	@Override
	public void doCommand() {
		model.solveMaze(mazeName, alg);
	}
	
}
