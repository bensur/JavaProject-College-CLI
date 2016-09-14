/**
 * 
 */
package controller;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public interface Command {
	/**
	 * the implementation of each command
	 * @param args - the arguments that are sent with the command
	 */
	void doCommand(String[] args);
}
