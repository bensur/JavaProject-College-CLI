/**
 * 
 */
package model;

import controller.Controller;

/**
 * @author yschori
 *
 */
public interface Model {
	/**
	 * @return the controller
	 */
	public Controller getController();

	/**
	 * @param controller the controller to set
	 */
	public void setController(Controller controller);
}
