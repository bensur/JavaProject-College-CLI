/**
 * 
 */
package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.util.HashMap;

import controller.Command;
import controller.Controller;
import controller.Dir;

/**
 * @author yschori
 *
 */
public class MyView implements View {
	private Controller controller;
	private Dir dir = new Dir("/", controller);
	private CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), new HashMap<String, Command>());
	
//	public MyView (Controller controller) {
//		this.controller = controller;
//	}
	
	public void start() {
		dir.doCommand();
		//cli.start();
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
	
	/**
	 * 
	 * @param s String to print
	 */
	public void print(String s) {
		//cli.print(s);
		System.out.println(s);
	}
}
