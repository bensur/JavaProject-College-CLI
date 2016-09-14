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
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public class MyView implements View {
	private Controller controller;
	private CLI cli;
	
	/**
	 * start method from the view, calls the start method of the cli
	 */
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
	 * set the controller and the cli with in, out and the commands map
	 * @param controller the controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), controller.getCommands());
	}
	
	/**
	 * print data from the view
	 * @param s String to print
	 */
	public void print(String s) {
		//cli.print(s);
		System.out.println(s);
	}
}
