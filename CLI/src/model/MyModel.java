/**
 * 
 */
package model;

import controller.Controller;

/**
 * @author yschori
 *
 */
public class MyModel implements Model {
	private Controller controller;
	
//	public MyModel (Controller controller) {
//		this.controller = controller;
//	}

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
