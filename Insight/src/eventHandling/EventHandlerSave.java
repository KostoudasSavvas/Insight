package eventHandling;

import java.util.ArrayList;
import javax.swing.JTextPane;

import HardDriveManager.FileSavingController;

public class EventHandlerSave {
	//default constructor
	
	public void save(JTextPane textArea,String openDocPath,ArrayList<String> list){
		
		FileSavingController svFile = new FileSavingController();
		
		// if text area is empty just set it to "" in order to save
		if (textArea.getText().trim().length() == 0){
			textArea.setText(" ");
		}
		
		svFile.save(openDocPath, textArea.getText(),textArea);
	}
}