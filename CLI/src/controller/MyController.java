/**
 * 
 */
package controller;

import java.util.HashMap;

import controller.CommandsManager.Dir;
import model.Model;
import view.View;

/**
 * @author yschori
 *
 */
public class MyController implements Controller {
	private View view;
	private Model model;
	private HashMap<String, Command> commands;
	
	/**
	 * 
	 */
	public MyController() {
		
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
	 * 
	 */
	@Override
	public void print(String string) {
		view.print(string);
	}
}
