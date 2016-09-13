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
 * @author bensu
 *
 */
public class CLI {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commandsMap;
	/**
	 * 
	 */
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> commandsMap) {
		this.in = in;
		this.out = out;
		this.commandsMap = commandsMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	
	public void start() {
		// TODO Auto-generated method stub
		
		Thread t = new Thread()
	    {
	        public void run() {
	        	try {
	    			String option;
	    			do {
	    				option = in.readLine();
	    				if (commandsMap.containsKey(option)) {
	    					commandsMap.get(option).doCommand();
	    				} else {
	    					out.println(option + ": command not found");
	    				}
	    				
	    			} while (!option.equals("exit"));
	    			
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        }
	    }; 
	    
	    t.start();
	}
	
	/**
	 * 
	 * @param s String to print
	 */
	public void print(String s) {
		out.println(s);
	}
}
