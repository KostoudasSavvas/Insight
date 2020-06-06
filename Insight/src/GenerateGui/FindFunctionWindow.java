package GenerateGui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;
import eventHandling.WordFinderHighLighter;

public class FindFunctionWindow{
	private GuiFactory GFactory;    // important Factory object in order to create the window elements
	static JFrame findWindow;
	private JTextPane textArea;
	
	public FindFunctionWindow(JTextPane textArea) {
		GFactory  = new GuiFactory();
		findWindow = GFactory.createFrame("Find/Replace", 600, 150, 370, 458);
		findWindow.setResizable(false);
		GFactory.onExit(findWindow, false);
		this.textArea = textArea;
		//------------------------------------------------- panel,buttons and menu items of the find window.
		
	    UIManager.put("TextField.border", BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(4,4,4,4))));
	    
		JPanel panel = GFactory.createPanelFind(findWindow);
		JButton btnCancel = GFactory.createFindButton(panel,"Cancel",230,390,115,25,"Click this button to return to main menu.");
		JButton btnFind = GFactory.createFindButton(panel,"Find",110,330,115,25,"Click this button to find the selected words.");
		JButton btnReplace = GFactory.createFindButton(panel,"Replace",110,360,115,25,"Click this button to replace the selected words.");
		JButton btnReplaceAll = GFactory.createFindButton(panel,"Replace All",230,330,115,25,"Click this button to replace all selected words.");
		JButton btnDeleteAll = GFactory.createFindButton(panel,"Delete All",230,360,115,25,"Click this button to delete all the selected words.");
		JTextField textField_1 = GFactory.createField(panel,130,50,200,30,10);
		JLabel labelFind = GFactory.createLabelFind(panel, "Find: ",15 , 50, 50, 30);
		JTextField textField_2 = GFactory.createField(panel,130,100,200,30,10);
		JLabel labelReplace = GFactory.createLabelFind(panel, "Replace with: ",15 , 100, 110, 30);
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

		initializeSpellCheck(textArea,textField_1,textField_2);
		
		panel.setLayout(null);
		findWindow.setVisible(true);
		eventListenerGenerate(findWindow,textArea,btnCancel,btnFind,btnReplace,btnReplaceAll,btnDeleteAll,textField_1,textField_2,buttonList);
	}
	
	public static void eventListenerGenerate(JFrame findWindow,JTextPane textArea,JButton btnCancel,JButton btnFind,JButton btnReplace,JButton btnReplaceAll,JButton btnDeleteAll,JTextField field,JTextField field2,ArrayList<JRadioButton> buttonList) {
		
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
		
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordFinderHighLighter finder = new WordFinderHighLighter();
				finder.deleteAllHighlights(textArea,field);
			}
		});
		
		btnReplaceAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordFinderHighLighter finder = new WordFinderHighLighter();
				finder.replaceAllHighlights(textArea,field,field2);
			}
		});
		
		btnReplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordFinderHighLighter finder = new WordFinderHighLighter();
				finder.replaceHighlight(textArea,field,field2);
			}
		});
	}
	
	public static void initializeSpellCheck(JTextPane textArea,JTextField textField_1,JTextField textField_2) {
		SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
        SpellChecker.registerDictionaries(MainEditorWindow.class.getResource("/dictionary"), "en");
        SpellChecker.register(textArea);
        SpellChecker.register(textField_1);
        SpellChecker.register(textField_2);
        SpellCheckerOptions sco=new SpellCheckerOptions();
        sco.setCaseSensitive(false);
        sco.setSuggestionsLimitMenu(15);

        JPopupMenu popup = SpellChecker.createCheckerPopup(sco);
        textArea.setComponentPopupMenu(popup);
	}
	
	public void makeVisible(){
		findWindow.setVisible(true);
	}
}