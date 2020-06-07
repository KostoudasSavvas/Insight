package serviceMenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import GenerateGui.GuiFactory;

public class ServiceMenuWindowCreator {
	static JFrame newWindow;
	private GuiFactory GFactory2;  // important Factory object in order to create the window elements
	
	public ServiceMenuWindowCreator() {
		GFactory2 = new GuiFactory();
		newWindow = GFactory2.createFrame("Help and Service",400, 150, 800, 740);
		newWindow.setResizable(false);
		newWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
		
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("/manual-book.png");
        ImageIcon icon2 = createImageIcon("/ask.png");
        ImageIcon icon4 = createImageIcon("/shortcut.png");
        
        JPanel panel1 = GFactory2.createPanelServiceComplete(1);
        panel1.setLayout(null);
        tabbedPane.addTab("User Manual Pt.1", icon, panel1,"");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JLabel labelText = new JLabel("User Manual -- All About Insight Application and More");
        labelText.setHorizontalAlignment(JLabel.CENTER);
        labelText.setVerticalAlignment(JLabel.CENTER);
        labelText.setBounds(0, 0, 900, 28);
        JLabel labelIntro = new JLabel("<html><br/><br/>Insight Application is an application that processes the log error files that are created by Hecate Tool."
        		+ " The reason that this program was<br/> created was initially to possess a more fluent and beautiful form of showing the errors rather than a simple editor "
        		+ "to show there errors.<br/ Along the way, more complicated features were added in order to separate the major errors"
        		+ " from the minor errors such as syntax errors,<br/> grouping by error or by schema file capability"
        		+ " ,edit each file capability and printing the log file to physical printer. "
        		+ "Furthermore, an editor<br/> was created in order to edit the file that possesses the presented errors in real time side by side"
        		+ " the Insight Application.<br/> Finally, finding feature was added in order to easily find any keyword or phrase inside the log file.</html>");
        labelIntro.setBounds(0,0,900,130);
        panel1.add(labelText);
        panel1.add(labelIntro);
        
        JLabel labelFile = new JLabel("<html><br/>1.1 --All about File Functions-- <br/> About file functions there are available three "
        		+ "different functions:<br/>1)Load File: This function's purpose is to load a file from anywhere in the system in the Insight"
        		+ "in order to show it's contents.Keep in mind<br/> that contents of the log file will split to major ones at the left and minor ones to the right.<br/>"
        		+ "2) Edit: This function allows any file of the schema to be editted in a separate window side by side Insight.<br/>"
        		+ "3) Print: This function prints the contents of the left text panel into a physical printer or if a printer is not existent"
        		+ " then it exports it into a<br/> PDF file.</html>");
        labelFile.setBounds(0, 130, 900, 150);
        panel1.add(labelFile);
        
        JLabel labelCommands = new JLabel("<html>1.2 --All about Printing--<br/>As the title of the menu says Printing this menu"
        		+ "will be unlocked and free to after a log file gets loaded.This function can either export the<br/> log file contents into a PDF file"
        		+ "if a printer is not available or print the contents to a physical printer which is connected.</html");
        labelCommands.setBounds(0,300,900,90);
        panel1.add(labelCommands);
        
        // ----------------------------------------------------------------------------------------------
        JComponent panel2 = GFactory2.createPanelServiceComplete(2);
        panel2.setLayout(null);
        tabbedPane.addTab("User Manual Pt.2", icon, panel2,"This tab contains information about the application.");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JLabel labelText2 = new JLabel("User Manual -- All About Insight Application and More");
        labelText2.setHorizontalAlignment(JLabel.CENTER);
        labelText2.setVerticalAlignment(JLabel.CENTER);
        labelText2.setBounds(0, 0, 900, 28);
        panel2.add(labelText2);
        
        JLabel labelSave = new JLabel("<html>2.1 --All about Saving File--<br/> Of course your files can be stored permanently.In order to achieve that"
        		+ " the checkbox Volatile Storage must be selected. Although this<br> function is only available is the Insight Editor.</html>");
        labelSave.setBounds(0, 30, 900, 50);
        panel2.add(labelSave);
        
        JLabel labelStorageOptions = new JLabel("<html>2.2 --All about Storage Options--<br/> When it comes to storage of your files there is only one way to store: 1)"
        		+ "Volatile Storage: This type of storage means<br/> that when you select ths checkbox and save,the contents of the editor get "
        		+ "saved permanently in the file and saved<br/> temporarily in the editor in order to rollback to previous versions."
        		+ "Keep in mind when you exit the editor all<br/> temporary previous versions are lost,and only the last version is saved in the file.</html>");
        labelStorageOptions.setBounds(0,90,900,170);
        panel2.add(labelStorageOptions);
        
        
        JLabel labelTools = new JLabel("<html>2.3 --All about Tools--<br/>In the Insight Editor there are three major tools:<br/>1)Clear File: This tool is capable"
        		+ " of wiping clear all the contents of the file.Don't worry a check message will show<br/> asking for your confirmation on the clear.<br/>"
        		+ "2)Rollback: This tool is essential in order to rollback to previous saved version of your current file if you want.When<br/> pressed, this function will"
        		+ "replace all the current data of the file with the contents of the previous saved version of<br/> the file.<br/>"
        		+ "3) Exit Icon: This menu if pressed will terminate the editor and you will return to Insight. Don't worry about saving<br/>"
        		+ "your data of the file because when press exit your data will be automatically saved permanently in the file.</html>");
        labelTools.setBounds(0,260,900,140);
        panel2.add(labelTools);
        
        
        JLabel labelFind = new JLabel("<html>2.4 --All about Find/Replace Functions--<br/> Find function is supported in Insight in order to find"
        		+ " anything word or letter fast and effective.There are four<br/> accociated functions that are handy along find function.These are:<br>"
        		+ "1) Find, which used to find any word or letter.2) Replace,which is used to replace the word or letter that has been<br/> found with a "
        		+ "word or letter that you like.3) Replace all,which is used to replace all the instances of the word with<br/> the selected word or letter and"
        		+ " finally 4)delete all which is used to delete all the instances of the find word.</html>");
        labelFind.setBounds(0, 400, 900, 120);
        panel2.add(labelFind);
        
        // -----------------------------------------------------------------------------------------------
        JComponent panel5 = GFactory2.createPanelServiceComplete(3);
        panel5.setLayout(null);
        tabbedPane.addTab("User Manual Pt.3", icon, panel5,"This tab contains information about the application.");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        JLabel labelText3 = new JLabel("User Manual -- All About SK Editor Function and More");
        labelText3.setHorizontalAlignment(JLabel.CENTER);
        labelText3.setVerticalAlignment(JLabel.CENTER);
        labelText3.setBounds(0, 0, 900, 28);
        panel5.add(labelText3);
        
        JLabel labelPeriod = new JLabel("<html>3.1 --All about Group By Error--<br/>The options offers the ability of grouping by Error for each file. This means"
        		+ " that if an error appers multiple times inside a file then this<br/> error will be grouped with all the other appearances of it. The result of this grouping"
        		+ " is Filename and then the line numbers and then the<br/> error message.</html>");
        labelPeriod.setBounds(0,35,900,90);
        panel5.add(labelPeriod);
        
        JLabel labelService = new JLabel("<html>3.2 --All about Group By File--<br/>This option offers the ability of grouping by file. This means that all the errors"
        		+ " that belong to a file will present one after the other and<br/> then will be presented errors from the next file.</html>");
        labelService.setBounds(0, 120, 900, 70);
        panel5.add(labelService);
        
        JLabel labelLanguage =  new JLabel("<html>3.3 --All about Spellchecking--<br/>In the Insight Editor all the writen text is getting checked by a spellchecking tool"
        		+ " that will offer English Language validity. If a word is<br/> spelled incorrectly, then it gets red underline an then the offered options are two: a) <u>a list of"
        		+ " similar words</u> is given if you want to replace<br/> the misspelled word or b) <u>add this word</u> to the dictionary.</html>");
        labelLanguage.setBounds(0,190,900,90);
        panel5.add(labelLanguage);
        
        JLabel labelCount = new JLabel("<html>3.4 --All about Editing Sql Files--<br/>In Insight there are two available ways is order to open an Sql File to edit. The first way"
        		+ " is through the small list at the very right of the<br/> Insight which you can right click in any of the Sql file and click the Edit option. The second way to"
        		+ " accomplish this is by clicking the pencil<br/> icon or typing the combination Ctrl + e at the keyboard again the list of Sql Files will be shown and by right clicking"
        		+ " the selected file will be<br/> opened up in the Insight Editor.</html>");
        labelCount.setBounds(0,280,900,90);
        panel5.add(labelCount);
        
                
        // -----------------------------------------------------------------------------------------------
        JComponent panel8 = GFactory2.createPanelServiceComplete(7);
        panel8.setLayout(null);
        tabbedPane.addTab("User Manual Pt.4 - Find Function", icon, panel8,"This tab contains the user manual of the application.");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        JLabel labelText8 = new JLabel("User Manual -- All About SK Editor Function and More");
        labelText8.setHorizontalAlignment(JLabel.CENTER);
        labelText8.setVerticalAlignment(JLabel.CENTER);
        labelText8.setBounds(0, 0, 900, 28);
        panel8.add(labelText8);
        
        JLabel labelFindFunction = new JLabel("<html>4.1 --Basic Find Function--<br/>This function is triggered by the blue find button"
        		+ ".In order to find anything you desire you must first type the keyword in the first text field<br/> and then press the find button"
        		+ " in order to initiate the find function. You must also select the scope or the direction in order to search your<br/> file.</html>");
        labelFindFunction.setBounds(0, 35, 900, 95);
        panel8.add(labelFindFunction);
        
        JLabel optionsList = new JLabel("<html>4.2 --Find Options Choises--<br/>Below there is a list with all the options about the find"
        		+ " function:<br/>1)<u>Scope -> All:</u> If you select this option the find function will search the whole file for the typed keyword.<br>"
        		+ "2)<u>Direction -> Forward:</u> If you select this option the find function will search your file from the middle of the contents all the way to the end.<br/>"
        		+ "3)<u>Direction -> Backward:</u>  If you select this option the find function will search your file from the start until the middle of the file.<br/>"
        		+ "4)<u>Options -> Whole Word:</u> This option can be selected at anytime and the find function will search the whole file for the keyword as whole<br/> word and not part of another word or phrase.<br/>"
        		+ "5)<u>Options -> Case Sensitive:</u> This option provides to search the keyword amongst both uppercase and lowercase options.</html>");
        optionsList.setBounds(0,130,900,160);
        panel8.add(optionsList);
        
        JLabel functionsList = new JLabel("<html>4.3 --Replace and Delete Functions--<br>"
        		+ "After you typed and searched for a keyword you can do the following actions with it:<br/>"
        		+ "1)<u>Replace:</u> This function allows to replace one finding of the keyword at a time with the"
        		+ " typed keyword that in the Replace With textfield.<br/>"
        		+ "2)<u>Replace All:</u> This function allows to replace all the findings of the keyword at once with"
        		+ " the typed keyword in the Replace With text field.<br/>"
        		+ "3)<u>Delete All:</u> This function allow to delete All the findings of the keyword at once of the entire file"
        		+ " .Saving your file before Delete All is<br/> encouraged.</html>");
        functionsList.setBounds(0, 290, 900, 200);
        panel8.add(functionsList);
        
        // -----------------------------------------------------------------------------------------------
        JComponent panel3 = GFactory2.createPanelServiceComplete(6);
        tabbedPane.addTab("App Info", icon2, panel3,"This tab contains information about the application.");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
        
        BufferedImage image3;
		try {
			image3 = ImageIO.read(new File("./Icons/pngLoading.png"));
		    JLabel labelIm = new JLabel(new ImageIcon(image3));
		    panel3.add(labelIm);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		 JLabel labelIntro2 = new JLabel("<html><br/><br/>Insight Application is an application that processes the log error files that are created by Hecate Tool."
	        		+ " The reason that this program was<br/> created was initially to possess a more fluent and beautiful form of showing the errors rather than a simple editor "
	        		+ "to show there errors.<br/ Along the way, more complicated features were added in order to separate the major errors"
	        		+ " from the minor errors such as syntax errors,<br/> grouping by error or by schema file capability"
	        		+ " ,edit each file capability and printing the log file to physical printer. "
	        		+ "Furthermore, an editor<br/> was created in order to edit the file that possesses the presented errors in real time side by side"
	        		+ " the Insight Application.<br/> Finally, finding feature was added in order to easily find any keyword or phrase inside the log file.</html>");
	     labelIntro2.setBounds(0,300,900,130);
	     panel3.add(labelIntro2);
	     
	     JLabel labelCreate = new JLabel("<html><br/><br/>Insight Created By: Savvas Kostoudas<br/>"
	     		+ "Date: 2020-6</html>");
	     labelCreate.setBounds(0,530,900,100);
	     panel3.add(labelCreate);
        
        // -----------------------------------------------------------------------------------------------
        
        JComponent panel6 = GFactory2.createPanelServiceComplete(5);
        panel2.setLayout(null);
        tabbedPane.addTab("Keyboard Shortcuts", icon4, panel6,"This tab contains the keyboard shortcuts of the Insight.");
        tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);
        
        JLabel labelText5 = new JLabel("Keyboard Shortcuts -- All About Insight Application and More");
        labelText5.setHorizontalAlignment(JLabel.CENTER);
        labelText5.setVerticalAlignment(JLabel.CENTER);
        labelText5.setBounds(0, 0, 900, 28);
        panel6.add(labelText5);
        
        JLabel labelShortcuts = new JLabel("<html>Below are presented all the keyboard shortcuts of tools and functions of the Insight:<br/>"
        		+"<table border=\"1\">  \n" + 
        		"<tr><th>Button Name</th><th>Key Shortcut</th></tr>  \n" + 
        		"<tr><td>Open Log File</td><td>Ctrl + o</td></tr>  \n" + 
        		"<tr><td>Edit Sql File</td><td>Ctrl + e</td></tr>  \n" + 
        		"<tr><td>Group By Error</td><td>alt + e</td></tr>  \n" + 
        		"<tr><td>Find/Replace</td><td>Ctrl + f</td></tr>  \n" +
        		"<tr><td>Print</td><td>Ctrl + p</td></tr>  \n" +
        		"<tr><td>Exit</td><td> Escape </td></tr>  \n" +
        		"<tr><td>Save Sql File</td><td>Ctrl + s</td></tr>  \n" +
        		"<tr><td>Enable Saving</td><td>Ctrl + 1</td></tr>  \n" +
        		"<tr><td>Undo Typing</td><td>Ctrl + z</td></tr>  \n" +
        		"<tr><td>Copy Text</td><td>Ctrl + c</td></tr>  \n" +
        		"<tr><td>Help Menu</td><td>alt + h</td></tr>  \n" +
        		"<tr><td>Paste Text</td><td>Ctrl + v</td></tr>  \n" +
        		"<tr><td>Cut Text</td><td>Ctrl + x</td></tr>  \n" +
        		"<tr><td>Select All Text</td><td>Ctrl + a</td></tr>  \n" +
        		"</table></html>");
        labelShortcuts.setBounds(0, 80, 300, 600);
        panel6.add(labelShortcuts);

        BufferedImage image;
		try {
			image = ImageIO.read(new File("./Icons/pngLoadingFixed.png"));
		    JLabel labelIm = new JLabel(new ImageIcon(image));
		    panel6.add(labelIm);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
        JLabel labelExp = new JLabel("<html><br/><br/><br/><br/><br/><br/>The table to the left explains the key combination that</br> is needed in order to"
        		+ " activate the various functions and tools<br/> of the Insight or Insight Editor.</html>");
        panel6.add(labelExp,BorderLayout.EAST);
        
        // ------------------------------------------------------------------------------------------------
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(600,550,70, 30);
        newWindow.getContentPane().add(btnCancel, BorderLayout.SOUTH);
        
        //The following line enables to use scrolling tabs.
        newWindow.getContentPane().add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        eventHandlerCancel(btnCancel);
	}
	
	// event handler method for the cancel button
	public static void eventHandlerCancel(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newWindow.setVisible(false);
				newWindow.dispose();
			}
		});
	}
	/** Returns an ImageIcon, or null if the path was invalid. */
    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ServiceMenuWindowCreator.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	public void makeVisible() {
		newWindow.setVisible(true);
	}
}