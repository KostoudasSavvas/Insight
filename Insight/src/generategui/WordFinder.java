package generategui;

import java.util.ArrayList;
import javax.swing.*;

import eventhandling.WordFinderHighLighter;
public class WordFinder {
	
	public void find(JTextPane textArea,JTextField field,ArrayList<JRadioButton> buttonList) {
		WordFinderHighLighter wfinder = new WordFinderHighLighter();
		wfinder.highLight(textArea, field.getText(),buttonList);
	}
}
