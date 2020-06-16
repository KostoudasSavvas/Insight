package eventhandlertexteditor;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import versionmanager.Document;

/**
 * This class is responsible to roll back to a previous saved version of the sql file
 * if a version exists.
 * @author savaf
 *
 */

public class EventHandlerRollBack {
	private EventHandlerSplit splitter = new EventHandlerSplit();
	
	public String rollBack(int Number,JTextPane textArea,String name,JCheckBoxMenuItem checkBox1,Document[] VolatileVersionsList){
		if (checkIfEmpty(VolatileVersionsList)){
			JOptionPane.showMessageDialog(null,"You must save a version first in order to load it.","You haven't store any versions yet.", JOptionPane.INFORMATION_MESSAGE);
		}else if(checkBox1.isSelected() && Number > 0){         // volatile storage
			if (Number <= 0){
				textArea.setText(null);
				return "";
			}else{
				Number--;
				return splitter.splitArrayList(VolatileVersionsList[Number].getContents());	
			}	
		}
		return "";
	}
	
	/**
	 * This method checks if the Document List is not empty which means a saved version of the file
	 * exists and so allows to roll back to that version.
	 * @param list
	 * @return boolean
	 */
	
	public static Boolean checkIfEmpty(Document[] list){
		if (list[1] == null){
			return true;
		}else{
			return false;
		}
	}
}