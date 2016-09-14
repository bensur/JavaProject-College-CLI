/**
 * 
 */
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

/**
 * @author Ben Surkiss & Yovel Shchori
 * 
 */
public class CLI {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commandsMap;
	
	/**
	 * C'tor
	 * @param in Input stream
	 * @param out Output stream
	 * @param commandsMap Commands map
	 */
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> commandsMap) {
		this.in = in;
		this.out = out;
		this.commandsMap = commandsMap;
	}

	/* 
	 * Start the CLI in separate thread
	 */
	public void start() {
		// Creates new thread using anonymous
		Thread t = new Thread()
	    {
	        public void run() {
	        	try {
	    			String cliInput;
	    			// Run as long as user did not entered 'exit'
	    			do {
	    				print("Please enter command");
	    				cliInput = in.readLine();
	    				// Split user input by ' '
	    				String[] arr = cliInput.split(" ");
	    				// Set commands as first argument
	    				String command = arr[0];
	    				if (commandsMap.containsKey(command)) {
	    					String[] args = null;
	    					// If there are more strings after the command, assemble argument array from strings
	    					if (arr.length > 1) {
	    						String commandArgs = cliInput.substring(cliInput.indexOf(" ") + 1);
	    						args = commandArgs.split(" ");
	    					}
	    					commandsMap.get(command).doCommand(args);
	    				} else {
	    					print(command + ": command not found");
	    				}
	    			} while (!cliInput.equals("exit"));
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	        }
	    }; 
	    // Start the thread
	    t.start();
	}
	
	/**
	 * TODO use out var
	 * @param s String to print
	 */
	public void print(String s) {
		System.out.println(s);
	}
}
