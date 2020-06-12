package guimainconsole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import guicommon.GuiFactory;
import guitexteditor.EditWindow;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Font;


public class MainInsightWindow{
	static JFrame InsightWindow;
	
	public static void main(String args[]){
		
		
		InsightWindow = new JFrame();
		InsightWindow.setTitle("Insight");
		InsightWindow.setResizable(false);
		InsightWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		InsightWindow.setBounds(100, 100, 1001, 726);
		GuiFactory guiFactory = new GuiFactory();
		
		JMenuBar menuBar = new JMenuBar();
		InsightWindow.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("");
		guiFactory.setImage(mnNewMenu, "/folder.png");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Load Log File");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_6 = new JMenu("");
		guiFactory.setImage(mnNewMenu_6, "/pencil.png");
		menuBar.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Edit Sql File");
		mntmNewMenuItem_6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnNewMenu_6.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_1 = new JMenu("");
		guiFactory.setImage(mnNewMenu_1, "/settings.png");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Group By Error");
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_2 = new JMenu("");
		guiFactory.setImage(mnNewMenu_2, "/connection.png");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Group By File");
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("");
		guiFactory.setImage(mnNewMenu_3, "/lens.png");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Find/Search");
		mntmNewMenuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_4 = new JMenu("");
		guiFactory.setImage(mnNewMenu_4, "/printer.png");
		menuBar.add(mnNewMenu_4);
		
		JMenu mnNewMenu_8 = new JMenu("");
		guiFactory.setImage(mnNewMenu_8, "/customer-service.png");
		mnNewMenu_8.setMnemonic(KeyEvent.VK_H);	// key stroke alt+H to guide user to the help/service menu.
		menuBar.add(mnNewMenu_8);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Print Log File");
		mntmNewMenuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnNewMenu_4.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Restore Initial Log File Contents");
		mntmNewMenuItem_8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_5 = new JMenu("");
		guiFactory.setImage(mnNewMenu_5, "/exit.png");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Exit Program");
		mntmNewMenuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		mnNewMenu_5.add(mntmNewMenuItem_5);
		
		menuBar.add(Box.createHorizontalStrut(580)); // necessary gap in order the app icon to be at the end of the menu bar
		
		JMenu mnNewMenu_7 = new JMenu("");
		guiFactory.setImage(mnNewMenu_7, "/32x32.png");
		mnNewMenu_7.setToolTipText("Insight Application 2020-05");
		menuBar.add(mnNewMenu_7);
		
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		InsightWindow.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(0, 0, 699, 643);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		textPane.setToolTipText("This text panel will show the major errors that Hecate Found");
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("                Sql Schema Files");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		lblNewLabel.setToolTipText("Here are the list of the Sql Schema Files");
		lblNewLabel.setBounds(702, 391, 282, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(704, 416, 280, 227);
		contentPane.add(scrollPane_2);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		scrollPane_2.setViewportView(list);
		list.setToolTipText("All the Sql Files Of the Schema");
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(704, 416, 280, 227);
		
		JLabel lblNewLabel_1 = new JLabel("            Minor Syntax Errors");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(704, 0, 280, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setBounds(702, 25, 282, 359);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setToolTipText("Minor Syntax Errors");
		textPane_1.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		scrollPane_1.setViewportView(textPane_1);
		textPane_1.setEditable(false);
		
		// construct the find window and the edit window
		FindFunctionInsight ffw = new FindFunctionInsight(textPane);
		HashMap<Integer, String> schemaNames = new HashMap<Integer, String>();
		EditWindow edw = new EditWindow(schemaNames,"");
		
		// show the intro window
		StartWindowCreator startWindow = new StartWindowCreator();
		try {
			startWindow.initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
		eventListenerGenerate(InsightWindow,ffw.getFindWindow(),edw,mntmNewMenuItem_5,mntmNewMenuItem,mntmNewMenuItem_4,textPane,textPane_1,list,mntmNewMenuItem_3,mntmNewMenuItem_1,mntmNewMenuItem_2,mntmNewMenuItem_6,mnNewMenu_8,mntmNewMenuItem_8);
		makeVisible();
	}
	
	
	public static void eventListenerGenerate(JFrame InsightWindow,JFrame ffw,EditWindow edw,JMenuItem btnCancel,JMenuItem btnLoad,JMenuItem btnPrint,JTextPane mainTArea,JTextPane secondaryArea,JList<String> schemaList,JMenuItem btnFind,JMenuItem groupError,JMenuItem groupFile,JMenuItem btnEdit,JMenu serviceMenu,JMenuItem restoreItem){
		
		EventHandlerInsight eventHInsight = new EventHandlerInsight();
		eventHInsight.handleInsight(InsightWindow,ffw,edw,btnCancel,btnLoad,btnPrint,mainTArea,secondaryArea,schemaList,btnFind,groupError,groupFile,btnEdit,serviceMenu,restoreItem);
		
		ServiceMenuHandler serviceMenuHandling = new ServiceMenuHandler();   // this opens and handles the service menu
		serviceMenuHandling.eventListenerService(serviceMenu);
		
		InsightWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				if (JOptionPane.showConfirmDialog(InsightWindow, "Are you sure you want to exit from Insight?", "Exit Application",0) == JOptionPane.YES_OPTION){
					System.exit(0);
			    }
			}
		});
	}
	
	
	public static void makeVisible() {
		InsightWindow.setVisible(true);
	}
}
