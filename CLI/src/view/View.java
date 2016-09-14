/**
 * 
 */
package view;

import controller.Controller;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public interface View {
	/**
	 * @return the controller
	 */
	public Controller getController();

	/**
	 * @param controller the controller to set
	 */
	public void setController(Controller controller);
	
	/**
	 * start method from the view
	 */
	public void start();
	
	/**
	 * start method from the view
	 * @param s String to print
	 */
	public void print(String s);
}
