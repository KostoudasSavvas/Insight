package harddrivemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class is responsible of loading the contents of the selected log file
 * into the main and secondary text panels.
 * @author savaf
 *
 */


public class FileLoaderController {
	String DocName="";
	ArrayList<String> areaMinorContents = new ArrayList<String>();
		
	
	/**
	 * This method loads the contents of the log file and returns as string contents. Furthermore
	 * the method finds the minor errors such as syntax errors which saves them in the arrayList area
	 * minorContents.
	 * @return log file contents
	 */
	public String load(){
		JFileChooser openFileChooser = new JFileChooser();
		String openDocPath="";
		
		JTextPane textArea = new JTextPane();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "txt files (*.txt)", "txt");
		openFileChooser.setFileFilter(xmlfilter);
		//openFileChooser.setCurrentDirectory(new java.io.File("."));
		openFileChooser.setDialogTitle("Please select the log file.");
		
		if(openFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			openDocPath = openFileChooser.getSelectedFile().toString();
		   	FileReader reader = null;
			try {
				reader = new FileReader(openDocPath);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		    BufferedReader br = new BufferedReader(reader);
		    File fileParent = new File(openFileChooser.getSelectedFile().getParent().toString()).getParentFile(); 
		    DocName =  fileParent.getAbsolutePath();
		  
		    try {
				textArea.read(br, null);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		    
		    try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    textArea.requestFocus();
		    JOptionPane.showMessageDialog(null, openDocPath, "Directory of chosen log file", JOptionPane.INFORMATION_MESSAGE);
			    
		}
		
		// remove the minor errors from main textpane
		ArrayList<String> areaContents = new ArrayList<String>();
		BufferedReader bufReader = new BufferedReader(new StringReader(textArea.getText()));
		String line;
		
		try {
			while( (line = bufReader.readLine()) != null ){	
				if (line.contains("[major]") || line.contains("Schema Folder:")) {
					areaContents.add(line);
				}else if (line.contains("[minor]")) {
					areaMinorContents.add(line);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return textArea.getText();
	}
	
	/**
	 * This method returns the absolute path of the folder which
	 * the log file is in.
	 * @return the absolute path of the folder of the log file
	 */
	
	public String getName(){
		return DocName;
	}
	
	
	/**
	 * This method returns an arraylist which contains the minor errors of the
	 * log file such as syntactic errors.
	 * @return
	 */
	public ArrayList<String> getMinorContents(){
		return areaMinorContents;
	}
}