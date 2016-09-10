/**
 * 
 */
package view;

import controller.Controller;

/**
 * @author yschori
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
	
	public void start();
	
	/**
	 * 
	 * @param s String to print
	 */
	public void print(String s);
}
