/**
 * 
 */
package controller;

import model.Model;
import view.View;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public class MyController implements Controller {
	private View view;
	private Model model;

	/* (non-Javadoc)
	 * @see controller.Controller#getView()
	 */
	@Override
	public View getView() {
		return view;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#setView(View view)
	 */
	@Override
	public void setView(View view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#getModel(Model model)
	 */
	@Override
	public Model getModel() {
		return model;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#getModel(Model model)
	 */
	@Override
	public void setModel(Model model) {
		this.model = model;
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#print(String string)
	 */
	@Override
	public void print(String string) {
		view.print(string);
	}
}