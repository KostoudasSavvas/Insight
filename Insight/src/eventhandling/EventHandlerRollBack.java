package eventhandling;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import versionmanager.Document;

public class EventHandlerRollBack {
	private EventHandlerSplit splitter = new EventHandlerSplit();
	// default constructor
	
	public void rollBack(int Number,JTextPane textArea,String name,JCheckBoxMenuItem checkBox1,Document[] VolatileVersionsList){
		if (checkIfEmpty(VolatileVersionsList)){
			JOptionPane.showMessageDialog(null,"You must save a version first in order to load it.","You haven't store any versions yet.", JOptionPane.INFORMATION_MESSAGE);
		}else if(checkBox1.isSelected() && Number > 0){         // volatile storage
			if (Number <= 0){
				textArea.setText(null);
			}else{
				Number--;
				textArea.setText(splitter.splitArrayList(VolatileVersionsList[Number].getContents()));	
			}	
		}
	}
	
	// this method checks if the document list with the versions is empty
	public static Boolean checkIfEmpty(Document[] list){
		if (list[1] == null){
			return true;
		}else{
			return false;
		}
	}
}