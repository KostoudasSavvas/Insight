package harddrivemanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

/**
 * This class is saving the contents of the text panel of the editor into the current opened
 * file that is getting edited.
 * @author savaf
 */

public class FileSavingController {
	// default constructor
	
	public void save(String FileName,String contents,JTextPane textArea){
		FileWriter fw = null;
		
		if (FileName.contains(".sql")) {
			File f = new File(FileName);
			
			try {
				fw = new FileWriter(f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			// Writes the content to the file
		    try {
				fw.write(contents);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		    try {
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Pleas select only .sql files to edit","Invalid type of File", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
}