package groupby;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

/**
 * This class is responsible of grouping the instances of the same error inside each sql file together.
 * 
 * @author savaf
 *
 */

public class GroupErrorHandler implements GroupBy{

	/**
	 * This method handles the grouping by error in the same file. This means that all the same errors
	 * in the same file they are grouped and presented as one line in Insight.
	 */
	
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
					
					int indexLine = -1,indexError = -1;
					
					if (errorMatches.size() > 1) {
						String allLines = "";
						errorCheck.put(line, true);
						
						for (String match : errorMatches.keySet()) {
							errorCheck.put(match, true);
							indexLine = match.indexOf("Line: ");
							indexError = match.indexOf("Error: ");
							allLines += match.substring(indexLine + "Line: ".length(),indexError)+",";
						}
						StringBuilder builder = new StringBuilder(allLines);
						builder.setCharAt(allLines.length() - 1, ' ');
						
						finalTextAreaContents.add("File: " + fileName + " Lines: "+ builder + " " + errorMessageString);
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
	
	/**
	 * This method writes to the main text panel the groups that were assembled 
	 * by the method handleGroupError
	 */
	
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
