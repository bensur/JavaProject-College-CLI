/**
 * 
 */
package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Controller;

/**
 * @author yschori
 *
 */
public class MyView implements View {
	private Controller controller;
	private CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), new HashMap<String, Command>());
	
//	public MyView (Controller controller) {
//		this.controller = controller;
//	}
	
	public void start() {
		cli.start();
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
}
