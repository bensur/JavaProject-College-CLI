/**
 * 
 */
package boot;

import controller.Controller;
import controller.MyController;
import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public class Run {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Controller controller = new MyController();
		View view = new MyView(controller);
		Model model = new MyModel(controller);
		
		controller.setModel(model);
		controller.setView(view);
		view.start();
	}

}
