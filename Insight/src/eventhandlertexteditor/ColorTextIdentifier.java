package eventhandlertexteditor;

import java.awt.Color;
import java.lang.reflect.Field;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * This class is responsible to identify the important keywords of the
 * DDL language and writ them with different colors. 
 * @author savaf
 * 
 *
 */
public class ColorTextIdentifier {

	
	/**
	 * This method will color each important color with the corresponding color
	 * Commands : red color
	 * Table Names or Field Names: yellow color
	 * Integer Numbers: Purple color
	 * Field Types: blue color
	 * Table Names without "`": green color 
	 * @param textArea
	 * @param pattern
	 * @param color
	 */
	
	public void identifyAndColor(JTextPane textArea,String pattern,String color) {
		
		StyledDocument styledDoc = textArea.getStyledDocument();
	    Style style = textArea.addStyle("Keyword Style", null);
	    
		try {
			Document doc = textArea.getDocument();
			String text = doc.getText(0, doc.getLength());
			int position = 0;
			
			Color colorText;
			try {
			    Field field = Class.forName("java.awt.Color").getField(color);
			    colorText = (Color)field.get(null);
			} catch (Exception e) {
			    colorText = null; // Not defined
			}
			
			StyleConstants.setForeground(style,colorText);
			
			while ((position = text.indexOf(pattern, position)) >= 0) {
				
				if ((text.charAt(position-1) == ' ') && text.charAt(position + pattern.length()) == ' '){
					styledDoc.setCharacterAttributes(position, position+pattern.length() - position, style, true);	
				}else if ((text.charAt(position-1) == '\n') && (position + 1 == (text.length()-1))) {
					styledDoc.setCharacterAttributes(position, position+pattern.length() - position, style, true);	
				}else if ((((text.charAt(position + pattern.length()) == ' ' || text.charAt(position + pattern.length()) == '\n') && (text.charAt(position-1) == ' ' || text.charAt(position-1) == '\n')))){
					styledDoc.setCharacterAttributes(position, position+pattern.length() - position, style, true);	
				}else if ((text.charAt(position-1) == ' ') && (text.charAt(position + pattern.length()) == ';' || text.charAt(position + pattern.length()) == ',')) {
					styledDoc.setCharacterAttributes(position, position+pattern.length() - position, style, true);	
				}else if ((text.charAt(position-1) == ' ') && text.charAt(position + pattern.length()) == '(') {
					styledDoc.setCharacterAttributes(position, position+pattern.length() - position, style, true);	
				}else if (text.charAt(position) == '=') {
					styledDoc.setCharacterAttributes(position, position+pattern.length() - position, style, true);	
				}
			
				position += pattern.length();
			}
			
			StyleConstants.setForeground(style,Color.black);

		} catch (Exception e) {}
	}
}
