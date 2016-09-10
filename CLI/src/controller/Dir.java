/**
 * 
 */
package controller;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import view.MyView;
import view.View;

/**
 * @author yschori
 *
 */
public class Dir implements Command {
	//private URI uri;
	private View view;
	private File file;
	private String[] filesList;
	//private String path;
	
	public Dir(String url, View view) {
		this.view = view;
		file = new File(url);
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		if(file.isDirectory()) {
			filesList = file.list();
			for (int i = 0; i < filesList.length; i++) {
				//System.out.println(filesList[i]);
				view.print(filesList[i]);
			}
		}
		else {
			view.print("Not a directory");
		}
	}

}
