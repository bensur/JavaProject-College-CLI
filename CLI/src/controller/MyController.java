/**
 * 
 */
package controller;

import java.util.HashMap;

import controller.CommandsManager.Dir;
import model.Model;
import view.View;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public class MyController implements Controller {
	private View view;
	private Model model;
	
	/**
	 * c'tor
	 * @param model model to set
	 * @param view view to set
	 */
	public MyController(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	
	/**
	 * gets the commands map
	 * @return commands map
	 */
	public HashMap<String, Command> getCommands() {
		CommandsManager manager = new CommandsManager(model, view, this);
		return manager.getCommands();
	}
	
	/**
	 * 
	 */
	@Override
	public void print(String string) {
		view.print(string);
	}
}
