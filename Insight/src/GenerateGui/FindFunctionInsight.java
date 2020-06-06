package GenerateGui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import eventHandling.WordFinderHighLighter;

public class FindFunctionInsight{
	private GuiFactory GFactory;    // important Factory object in order to create the window elements
	static JFrame findWindow;
	private JTextPane textArea;
	
	public FindFunctionInsight(JTextPane textArea) {
		GFactory  = new GuiFactory();
		findWindow = GFactory.createFrame("Find/Replace", 600, 150, 370, 420);
		findWindow.setResizable(false);
		findWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		

		
		this.textArea = textArea;
		//------------------------------------------------- panel,buttons and menu items of the find window.
		
	    UIManager.put("TextField.border", BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(4,4,4,4))));
	    
		JPanel panel = GFactory.createPanelFind(findWindow);
		JButton btnCancel = GFactory.createFindButton(panel,"Cancel",230,330,115,25,"Click this button to return to main menu.");
		JButton btnFind = GFactory.createFindButton(panel,"Find",110,330,115,25,"Click this button to find the selected words.");
		JTextField textField_1 = GFactory.createField(panel,130,50,200,30,10);
		JLabel labelFind = GFactory.createLabelFind(panel, "Find: ",15 , 50, 50, 30);
		JLabel scope = GFactory.createLabelFind(panel, "Scope ",15 , 175, 70, 30);
		JLabel direction = GFactory.createLabelFind(panel, "Direction ",200 , 175, 70, 30);
		JLabel options = GFactory.createLabelFind(panel, "Options",15 , 260, 70, 30);
		
		//------------------------------------------------ radio buttons for the scope,direction of the search and options
		JRadioButton rdForward = GFactory.createJCheckButton(panel, "Forward",200,200,120,30);
		JRadioButton rdBackward = GFactory.createJCheckButton(panel, "Backward",200,220,120,30);
		JRadioButton rdScopeAll = GFactory.createJCheckButton(panel, "All",15,200,120,30);
		
		JRadioButton optionsCaseSensitive = GFactory.createJCheckButton(panel, "Whole word",15,280,120,30);
		JRadioButton optionsWholeWord = GFactory.createJCheckButton(panel, "Case sensitive",200,280,130,30);
		
		ArrayList<JRadioButton> buttonList = new ArrayList<JRadioButton>();
		buttonList.add(rdForward);buttonList.add(rdBackward);buttonList.add(rdScopeAll);
		buttonList.add(optionsCaseSensitive);buttonList.add(optionsWholeWord);
		//------------------------------------------------

		
		panel.setLayout(null);
		findWindow.setVisible(true);
		eventListenerGenerate(findWindow,textArea,btnCancel,btnFind,textField_1,buttonList);
	}
	
	public static void eventListenerGenerate(JFrame findWindow,JTextPane textArea,JButton btnCancel,JButton btnFind,JTextField field,ArrayList<JRadioButton> buttonList) {
		
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordFinder finder = new WordFinder();
				finder.find(textArea, field,buttonList);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordFinderHighLighter finder = new WordFinderHighLighter();
				finder.removeHighlights(textArea);
				findWindow.setVisible(false);
			}
		});
	}
	
	
	public void makeVisible(){
		findWindow.setVisible(true);
	}
}