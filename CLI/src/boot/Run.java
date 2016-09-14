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
		Model model = new MyModel();
		View view = new MyView();
		Controller controller = new MyController(model, view);

		model.setController(controller);
		view.setController(controller);
		
		view.start();
	}

}
