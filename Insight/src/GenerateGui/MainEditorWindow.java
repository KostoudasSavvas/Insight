package GenerateGui;

import javax.swing.*;
import eventHandling.EventHandlerEditor;
import eventHandling.EventHandlerInsight;
//import eventhand.MainEditorWindowHelper;
import eventHandling.SpellCheckInitializer;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.Element;

 

public class MainEditorWindow{
	static JFrame EditorWindow;
	private GuiFactory GFactory4;          // important GuiFactory object to create and initialize window elements
	private SpellCheckInitializer spellChecker = new SpellCheckInitializer();
	private String fileName;          // the name of the file that will be edited
	private String schemaPath;
	
	public MainEditorWindow(String fileName,String schemaPath) {
		this.fileName = fileName;
		this.schemaPath = schemaPath;
 		GFactory4 = new GuiFactory();
		
		EditorWindow = GFactory4.createFrameEditor("Insight Editor",400, 150, 1000, 720);
		GFactory4.onExit(EditorWindow, false);
		
		// -----------------------------------  the panel of the editor
		JPanel panel = GFactory4.createMainPanel(EditorWindow,3);
		
		// -------------- menu bar and menus in menu bar
		JMenuBar menuBar = GFactory4.createBar(panel);
		JMenu SaveMenu = GFactory4.createMenu(menuBar,"Click this menu to save the version or entire file.","/arrows.png");
		JMenu Storage =  GFactory4.createMenu(menuBar,"Click this menu to open the storage menu.","/warehouse.png");
		JMenu ToolsMenu = GFactory4.createMenu(menuBar,"Click this menu to open the tools menu.","/tools.png");
		JMenu printerMenu = GFactory4.createMenu(menuBar, "Click this icon to print the file you want.", "/printer.png");
		JMenu findMenu = GFactory4.createMenu(menuBar, "Click this icon to find a word or replace.", "/lens.png");
		JMenu exitMenu = GFactory4.createMenu(menuBar,"Click this menu to exit the application.","/exit.png");

		EditorWindow.setJMenuBar(menuBar);

		JLabel labelN = GFactory4.createLabelBar(menuBar, "                                                                                                               Editing Now: "); 

		// -------------- menu items
		
		JCheckBoxMenuItem btnVolatileStorage = GFactory4.createJCheckMenuItem(Storage,"Volatile","Click This Button to have volatile type of storage");
		
		JMenuItem btnClear = GFactory4.createMenuItem(ToolsMenu, "Clear Doc","Click this button to clear the entire doc.");
		JMenuItem btnRollBack = GFactory4.createMenuItem(ToolsMenu, "Undo Typing","Click This Button to Roll Back to previous version of the document.");
		JMenuItem btnCancel = GFactory4.createMenuItem(exitMenu, "Cancel Edit","Click this button to return to main menu.");
		JMenuItem btnSave = GFactory4.createMenuItem(SaveMenu, "Save File","Click This Button to save the document.");
		JMenuItem btnFind = GFactory4.createMenuItemWithIcon(findMenu, "Find/Replace","/lens.png","Click This Button to find/replace words.");

		//----------------- key Shortcuts on Menu Items
		KeyStroke keyStrokeToRollback = KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK);
		btnRollBack.setAccelerator(keyStrokeToRollback);
		KeyStroke keyStrokeToSave = KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
		btnSave.setAccelerator(keyStrokeToSave);
		KeyStroke keyStrokeEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
		btnCancel.setAccelerator(keyStrokeEscape);
		KeyStroke keyStrokeToVolatile = KeyStroke.getKeyStroke(KeyEvent.VK_1,KeyEvent.CTRL_DOWN_MASK);
		btnVolatileStorage.setAccelerator(keyStrokeToVolatile);
		KeyStroke keyStrokeToFind = KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_DOWN_MASK);
		btnFind.setAccelerator(keyStrokeToFind);
		
		// -------------- the text area of the editor
		JTextPane textArea = GFactory4.createArea(panel);
		textArea.setText("");
		JScrollPane scroll = new JScrollPane(textArea);
		EditorWindow.add(scroll,BorderLayout.CENTER);
		spellChecker.initializeSpellCheck(textArea);
		 
		/* Set up line numbers */
		JTextArea linesArea = new JTextArea("1");
		linesArea.setBackground(Color.BLUE);
		linesArea.setForeground(Color.WHITE);
		linesArea.setEditable(false);
		scroll.setRowHeaderView(linesArea);
		
		textArea.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				
				int caretPosition = textArea.getDocument().getLength();
				Element root = textArea.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			
			public void changedUpdate(DocumentEvent de) {
				linesArea.setText(getText());
			}
 
			public void insertUpdate(DocumentEvent de) {
				linesArea.setText(getText());
			}
 
			public void removeUpdate(DocumentEvent de) {
				linesArea.setText(getText());
			}
 
		});
		
		
		// -------------------------------------------------  event Listeners
		EventHandlerEditor eventHandler = new EventHandlerEditor();
		eventHandler.handleEdit(schemaPath,fileName,EditorWindow,btnVolatileStorage,btnClear,btnRollBack,btnSave,btnCancel,textArea,linesArea,labelN,printerMenu,btnFind);		
		makeVisible();
	}
	
	
	public static void makeVisible() {
		EditorWindow.setVisible(true);
	}
}