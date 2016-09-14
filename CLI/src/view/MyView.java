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
	private CLI cli;
		
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
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), controller.getCommands());
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
