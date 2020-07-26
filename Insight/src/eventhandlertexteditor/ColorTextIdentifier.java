package eventhandlertexteditor;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 * This class is responsible to identify the important keywords of the
 * DDL language and writ them with different colors. 
 * @author savaf
 * 
 *
 */
public class ColorTextIdentifier {

	
	/**
	 * This method  will provide the coloring of the important keywords.
	 * The parameter pattern contains the keyword we color each time.
	 * @param textArea
	 * @param colorsMap
	 * @param pattern
	 */
	
	public void identifyAndColor(JTextComponent textArea,HashMap<String, String> colorsMap,String pattern) {
		try {
			Document doc = textArea.getDocument();
			String text = doc.getText(0, doc.getLength());
			int position = 0;
			
			
			while ((position = text.indexOf(pattern, position)) >= 0) {
				
				if ((text.charAt(position-1) == ' ') && text.charAt(position + pattern.length()) == ' '){
					//Hlight.addHighlight(position, position+pattern.length(),MyHighlightPainter);
					position += pattern.length();
				}else if ((text.charAt(position-1) == '\n') && (position + 1 == (text.length()-1))) {
					//Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
					position += pattern.length();
				}else if ((((text.charAt(position + pattern.length()) == ' ' || text.charAt(position + pattern.length()) == '\n') && (text.charAt(position-1) == ' ' || text.charAt(position-1) == '\n')))){
					//Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
					position += pattern.length();
				}
				else {
					position += pattern.length();
				}
			}
		} catch (Exception e) {}
	}
}
