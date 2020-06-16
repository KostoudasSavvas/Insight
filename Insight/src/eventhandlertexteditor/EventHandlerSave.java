package eventhandlertexteditor;

import java.util.ArrayList;
import javax.swing.JTextPane;

import harddrivemanager.FileSavingController;

/**
 * This class adds a control on the text panel and if the conditions are met
 * then proceeds to saving the desired sql file.
 * @author savaf
 *
 */

public class EventHandlerSave {
	
	public void save(JTextPane textArea,String openDocPath,ArrayList<String> list){
		
		FileSavingController svFile = new FileSavingController();
		
		// if text area is empty just set it to "" in order to save
		if (textArea.getText().trim().length() == 0){
			textArea.setText(" ");
		}
		
		svFile.save(openDocPath, textArea.getText());
	}
}