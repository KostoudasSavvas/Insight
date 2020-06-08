package groupBy;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;


public class GroupErrorHandler implements GroupBy{

	
	// this method groups the error in the same file together
	public ArrayList<String> handleGroupError(String textArea) {
		
		
		BufferedReader bufReader = new BufferedReader(new StringReader(textArea));
		BufferedReader bufReader2 = new BufferedReader(new StringReader(textArea));

		String line,line2;
		int errorWordIndex = -1;
		int lineIndex = -1;
		int errorCount = 0;
		HashMap<String,Integer> errorMatches = new HashMap<String,Integer>();  // this arraylist contains the matching errors
		HashMap<String, String> linesProcessed = new HashMap<String, String>(); 
		ArrayList<String> finalTextAreaContents = new ArrayList<String>();
		
		
		try {
			while( (line = bufReader.readLine()) != null ){
				errorWordIndex = line.indexOf("Error: ");
				
				if (errorWordIndex != -1) {
					lineIndex = line.indexOf("Line:");
					String fileName = line.substring("File: ".length(),lineIndex);
					errorCount ++;
					errorMatches.put(line,errorCount);
					linesProcessed.put(line,"processed");
					String errorMessageString = line.substring(errorWordIndex + ("Error: ".length())); // this contains the error message

					
					try {
						while ((line2 = bufReader2.readLine()) != null ) {
							
							if (line2.contains(errorMessageString) && line2.contains(fileName) && !errorMatches.containsKey(line2) && !linesProcessed.containsKey(line2)) {
								errorCount++;
								errorMatches.put(line2,errorCount);
								linesProcessed.put(line2,"processed");
							}
						}
					}catch (Exception ex) {
						ex.printStackTrace();
					}
					
					
					if (errorMatches.size() > 1) {
						String allLines = "";
						int indexLine = -1,indexError = -1;
						
						for (String match : errorMatches.keySet()) {
							indexLine = match.indexOf("Line: ");
							indexError = match.indexOf("Error: ");
							allLines += match.substring(indexLine + "Line: ".length(),indexError)+",";
						}
						StringBuilder builder = new StringBuilder(allLines);
						builder.setCharAt(allLines.length() - 1, ' ');
						
						finalTextAreaContents.add("File: " + fileName + " Lines: "+ builder + " " + errorMessageString);
					} 
					
					
					errorMatches.clear();
					errorCount = 0;	
					
					// reset the buffer to start of String contents
					bufReader2 = new BufferedReader(new StringReader(textArea));
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalTextAreaContents;
	}
	
	
	public void writeGroupOutput(JTextPane textArea, ArrayList<String> groupLines) {
		
		// now setting the gathered text into the main text panel
		textArea.setText("");
		StyledDocument styledDoc = textArea.getStyledDocument();
		
		for (String groupLine : groupLines) {
			try {
				styledDoc.insertString(styledDoc.getLength(),groupLine + "\n\n", null);
			}catch (Exception ex) {
				System.out.println(ex);
			}
		}	
	}
	
	
}
