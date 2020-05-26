package eventHandling;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.*;

import highLighter.MyHighlighter;

public class WordFinderHighLighter {
	
	
	Highlighter.HighlightPainter MyHighlightPainter = new MyHighlighter(Color.RED); 
	
	public void highLight(JTextComponent textArea,String pattern,ArrayList<JRadioButton> buttonList) {
		// first remove any previous highlights
		removeHighlights(textArea);
		
		try {
			Highlighter Hlight = textArea.getHighlighter();
			Document doc = textArea.getDocument();
			String text = doc.getText(0, doc.getLength());
			String contents = textArea.getText();
			String textUpperCase = text;
			String patternUpperCase = pattern;
			int position;
			
			// this if statement checks if case sensitive radio button is selected
			// and if is selected then the find function finds all instances of the word
			// both lower case and upper case
			
			
			if (buttonList.get(4).isSelected()) {
				textUpperCase = text.toUpperCase();
				patternUpperCase = pattern.toUpperCase();
			}else {
				textUpperCase = text;
				patternUpperCase = pattern;
			}
			
			if (buttonList.get(3).isSelected()) {
				
				if (buttonList.get(0).isSelected()) {
					position = (contents.length())/2;
					while ((position = textUpperCase.indexOf(patternUpperCase, position)) >= 0) {
						Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
						position += patternUpperCase.length();
					}
				}else if (buttonList.get(1).isSelected()){
					position = 0;
					int flag  = contents.length()/2;
					while ((position = textUpperCase.indexOf(patternUpperCase, position)) >= 0) {
						Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
						position += patternUpperCase.length();
						
						if (position >= flag) {
							break;
						}
					}
				}else {
					position = 0;
					while ((position = textUpperCase.indexOf(patternUpperCase, position)) >= 0) {
						
						if ((textUpperCase.charAt(position-1) == ' ') && textUpperCase.charAt(position + patternUpperCase.length()) == ' '){
							Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
							position += patternUpperCase.length();
						}else if ((textUpperCase.charAt(position-1) == '\n') && (position + 1 == (textUpperCase.length()-1))) {
							Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
							position += patternUpperCase.length();
						}else if ((((textUpperCase.charAt(position + patternUpperCase.length()) == ' ' || textUpperCase.charAt(position + patternUpperCase.length()) == '\n') && (textUpperCase.charAt(position-1) == ' ' || textUpperCase.charAt(position-1) == '\n')))){
							Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
							position += patternUpperCase.length();
						}
						else {
							position += patternUpperCase.length();
						}
					}
				}
			}else {
				if (buttonList.get(0).isSelected()) {
					position = (contents.length())/2;
					while ((position = textUpperCase.indexOf(patternUpperCase, position)) >= 0) {
						Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
						position += patternUpperCase.length();
					}
				}else if (buttonList.get(1).isSelected()){
					position = 0;
					int flag  = contents.length()/2;
					while ((position = textUpperCase.indexOf(patternUpperCase, position)) >= 0) {
						Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
						position += patternUpperCase.length();
						
						if (position >= flag) {
							break;
						}
					}
				}else if (buttonList.get(0).isSelected() && buttonList.get(1).isSelected()) {
					JOptionPane.showMessageDialog(null, "","Find Function Wrong Input Options", JOptionPane.INFORMATION_MESSAGE);
				}else if (buttonList.get(2).isSelected()){
					position = 0;
					if (buttonList.get(3).isSelected()) {
						position = 0;
						while ((position = textUpperCase.indexOf(patternUpperCase, position)) >= 0) {
							
							if ((textUpperCase.charAt(position-1) == ' ') && textUpperCase.charAt(position + patternUpperCase.length()) == ' '){
								Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
								position += patternUpperCase.length();
							}else if ((textUpperCase.charAt(position-1) == '\n') && (position + 1 == (textUpperCase.length()-1))) {
								Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
								position += patternUpperCase.length();
							}else if ((((textUpperCase.charAt(position + patternUpperCase.length()) == ' ' || textUpperCase.charAt(position + patternUpperCase.length()) == '\n') && (textUpperCase.charAt(position-1) == ' ' || textUpperCase.charAt(position-1) == '\n')))){
								Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
								position += patternUpperCase.length();
							}
							else {
								position += patternUpperCase.length();
							}
						}
					}else {
						while ((position = textUpperCase.indexOf(patternUpperCase, position)) >= 0) {
							Hlight.addHighlight(position, position+patternUpperCase.length(),MyHighlightPainter);
							position += patternUpperCase.length();
						}
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "You must select either Scope All option or any of the Direction choises in order to search.","Find Function None Input Options", JOptionPane.INFORMATION_MESSAGE);
				}
			}
				
			
		}catch (Exception e) {}
	}
	
	public void removeHighlights(JTextComponent Tcomp) {
		Highlighter hilite = Tcomp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();
		
		for (int i=0;i<hilites.length;i++) {
			if (hilites[i].getPainter() instanceof MyHighlighter) {
				hilite.removeHighlight(hilites[i]);
			}
		}
	}
	
	public void deleteAllHighlights(JTextComponent Tcomp,JTextField field) {
		
		Highlighter hilite = Tcomp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();
		String contents = Tcomp.getText();
		String result="";
		
		for (int i=0;i<hilites.length;i++) {
			if (hilites[i].getPainter() instanceof MyHighlighter) {
				if (contents.contains(field.getText())) {
					result = contents.replace(field.getText(), "");
				}		
			}
		}
		Tcomp.setText(result);
	}
	
	public void replaceHighlight(JTextComponent Tcomp,JTextField fieldFind,JTextField fieldReplace) {
		String contents = Tcomp.getText();
		String result="";
		if (!(fieldReplace.getText().equals(null))) {
			result = contents.replaceFirst(fieldFind.getText(), fieldReplace.getText());
			Tcomp.setText(result);
		}else {
			JOptionPane.showMessageDialog(null, "Insert the replace word.","Empty Replace Field", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void replaceAllHighlights(JTextComponent Tcomp,JTextField fieldFind,JTextField fieldReplace) {
		
		Highlighter hilite = Tcomp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();
		String contents = Tcomp.getText();
		String result="";

		for (int i=0;i<hilites.length;i++) {
			if (hilites[i].getPainter() instanceof MyHighlighter) {
				if (contents.contains(fieldFind.getText())) {
					result = contents.replace(fieldFind.getText(), fieldReplace.getText());
				}		
			}
		}
		Tcomp.setText(result);
	}
}