package groupBy;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

public class GroupByFileHandler {
	
	// this method groups an error that exist in different files together
		public void handleGroupError(JTextPane textArea) {
			
			
			BufferedReader bufReader = new BufferedReader(new StringReader(textArea.getText()));
			BufferedReader bufReader2 = new BufferedReader(new StringReader(textArea.getText()));

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
								
								if (line2.contains(errorMessageString) && !errorMatches.containsKey(line2) && !linesProcessed.containsKey(line2)) {
									errorCount++;
									errorMatches.put(line2,errorCount);
									linesProcessed.put(line2,"processed");
								}
							}
						}catch (Exception ex) {
							ex.printStackTrace();
						}
						
						// Found an error across many files
						if (errorMatches.size() > 1) {
							String allFiles = "";
							
							for (String match : errorMatches.keySet()) {
								int indexFile = match.indexOf("File: ");
								int indexLine = match.indexOf("Line: ");
								allFiles += match.substring(indexFile + "File: ".length(),indexLine)+",";
							}
							StringBuilder builder = new StringBuilder(allFiles);
							builder.setCharAt(allFiles.length() - 1, ' ');							
							finalTextAreaContents.add("Files: " + builder +  " " + "Error: " + errorMessageString);
						} 
						
						
						errorMatches.clear();
						errorCount = 0;	
						
						// reset the buffer to start of String contents
						bufReader2 = new BufferedReader(new StringReader(textArea.getText()));
					}
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			// now setting the gathered text into the main text panel
			textArea.setText("");
			StyledDocument styledDoc = textArea.getStyledDocument();
			
			for (String groupLine : finalTextAreaContents) {
				try {
					styledDoc.insertString(styledDoc.getLength(),groupLine + "\n\n", null);
				}catch (Exception ex) {
					System.out.println(ex);
				}
			}	
		}
}
