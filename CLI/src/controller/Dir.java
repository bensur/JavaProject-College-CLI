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
	private Controller controller;
	private File file;
	private String[] filesList;
	//private String path;
	
	public Dir(String url, Controller controller) {
		this.controller = controller;
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
				controller.print(filesList[i]);
			}
		}
		else {
			controller.print("Not a directory");
		}
	}

}
