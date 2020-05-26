package HardDriveManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledDocument;

public class FileLoaderController {
	String DocName="";
	ArrayList<String> areaMinorContents = new ArrayList<String>();

	// default constructor
	
	public String load(){
		JFileChooser openFileChooser = new JFileChooser();
		String openDocPath="";
		
		JTextPane textArea = new JTextPane();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "txt files (*.txt)", "txt");
		openFileChooser.setFileFilter(xmlfilter);
		openFileChooser.setCurrentDirectory(new java.io.File("."));
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
			    DocName =  openFileChooser.getSelectedFile().toString();
			  
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
		
		StyledDocument styledDoc = textArea.getStyledDocument();
		textArea.setText("");
		
		for (String lineMajor : areaContents) {
			try {
				if (lineMajor.contains("Schema Folder:")) {
					styledDoc.insertString(styledDoc.getLength(),lineMajor + "\n\n", null);
				}else {
					styledDoc.insertString(styledDoc.getLength(),lineMajor + "\n", null);
				}
			}catch (Exception ex) {
				System.out.println(ex);
			}
		}	
		
		return textArea.getText();
	}
	
	public String getName(){
		return DocName;
	}
	
	public ArrayList<String> getMinorContents(){
		return areaMinorContents;
	}
}