/**
 * 
 */
package view;

import controller.Controller;

/**
 * @author yschori
 *
 */
public class MyView implements View {
private Controller controller;
	
//	public MyView (Controller controller) {
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
