/**
 * 
 */
package controller;

import java.util.HashMap;

import model.Model;
import view.View;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public interface Controller {
	/**
	 * @return the view
	 */
	public View getView();

	/**
	 * @param view the view to set
	 */
	public void setView(View view);

	/**
	 * @return the model
	 */
	public Model getModel();

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model);
	
	/**
	 * 
	 * @return Commands map
	 */
	public HashMap<String, Command> getCommands();
	
	/**
	 * 
	 * @param string to print
	 */
	public void print(String string);
}
