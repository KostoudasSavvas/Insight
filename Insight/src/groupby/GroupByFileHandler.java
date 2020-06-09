package groupby;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

public class GroupByFileHandler implements GroupBy {
	
	// this method groups an error that exist in different files together
	public ArrayList<String> handleGroupError(String textArea) {
		
		
		BufferedReader bufReader = new BufferedReader(new StringReader(textArea));
		BufferedReader bufReader2 = new BufferedReader(new StringReader(textArea));

		String line,line2;
		int errorWordIndex = -1;
		int lineIndex = -1;
		int errorCount = 0;
		HashMap<String,Integer> errorMatches = new HashMap<String,Integer>();  // this arraylist contains the matching errors
		HashMap<String, String> linesProcessed = new HashMap<String, String>(); 
		HashMap<String, Boolean> errorCheck = new HashMap<String, Boolean>();
		ArrayList<String> finalTextAreaContents = new ArrayList<String>();
		
		
		try {
			while( (line = bufReader.readLine()) != null ){
				errorWordIndex = line.indexOf("Error: ");
				
				
				if (errorWordIndex != -1) {
					lineIndex = line.indexOf("Line:");
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
						int indexFile= -1, indexLine = -1;
						errorCheck.put(line, true);
						
						
						for (String match : errorMatches.keySet()) {
							errorCheck.put(match, true);
							indexFile = match.indexOf("File: ");
							indexLine = match.indexOf("Line: ");
							
							if  (!allFiles.contains(match.substring(indexFile + "File: ".length(),indexLine))) {
								allFiles += match.substring(indexFile + "File: ".length(),indexLine)+",";
							}
						}
					
						
						StringBuilder builder = new StringBuilder(allFiles);
						builder.setCharAt(allFiles.length() - 1, ' ');							
						finalTextAreaContents.add("Files: " + builder +  " " + "Error: " + errorMessageString);
						
					}else { // this case catches a one time show error
						
						HashMap.Entry<String,Integer> entry = errorMatches.entrySet().iterator().next();
						
						if (!finalTextAreaContents.contains(entry.getKey()) && !errorCheck.containsKey(line)) {
							finalTextAreaContents.add(entry.getKey());
						}
						
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
