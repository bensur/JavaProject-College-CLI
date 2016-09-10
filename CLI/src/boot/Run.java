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
 * @author yschori
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Controller controller = new MyController();
		View view = new MyView();
		Model model = new MyModel();
		
		view.start();
	}

}
