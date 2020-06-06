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
        
        JLabel labelPeriod = new JLabel("<html>1.8 --All about Period of Automatic Storage--<br/>The period of autotatic storage can be set in any"
        		+ " of the availiable options.The span of the options is from 10 seconds<br/> all the way up to 1 hour. The default value of the period"
        		+ "is set to 10 seconds.You can change it accordingly<br/> in a value that you like.</html>");
        labelPeriod.setBounds(0,35,900,90);
        panel5.add(labelPeriod);
        
        JLabel labelService = new JLabel("<html>1.9 --All about Service Menu--<br/>This menu contains valuable information about the software SK Editor"
        		+ " as it contains the user manual but it also<br/> contains information about the editor and the production crew.</html>");
        labelService.setBounds(0, 120, 900, 70);
        panel5.add(labelService);
        
        JLabel labelLanguage =  new JLabel("<html>2.0 --All about Language Set--<br/>This menu's options are three languages that can change all"
        		+ " the text of the items of the editor buttons,menuitems<br/>,etc according to the user's language.The offered languages are three: Greek,"
        		+ " English and French.The default<br> language is English.</html>");
        labelLanguage.setBounds(0,190,900,90);
        panel5.add(labelLanguage);
        
        JLabel labelCount = new JLabel("<html>2.1 --All about Words and Characters Count--<br/>Those two labels are changing accordingly on how"
        		+ " many words and characters you type and exist in the file at any<br/> moment.Of course, if there is none words or characters typed"
        		+ "in the editor then both counts values are zero.</html>");
        labelCount.setBounds(0,280,900,90);
        panel5.add(labelCount);
        
        JLabel labelSizeFontColor = new JLabel("<html>2.2 --All about Size Box, Font Box and Color Box<br/>Those three selection boxes are"
        		+ " responsible of changing the size of the letters of the editor,the font style<br/> and lastly the color of the text."
        		+ "To use the size box you just press the little arrow on top of it and you select the size.<br/>Then all the contents of the editor"
        		+ "are adjusted to the chosen size.Second, same as before in order to change the<br/> style of the text you just choose the desirable style text"
        		+ "and automatically all the contents of the editor<br> are adjusted to the selected style.Lastly, in order to use the color box you must"
        		+ "first select the desired data that<br/> you want to change color and then you choose the desirable color. </html>");
        labelSizeFontColor.setBounds(0,370,900,140);
        panel5.add(labelSizeFontColor);
        // -----------------------------------------------------------------------------------------------
        JComponent panel7 = GFactory2.createPanelServiceComplete(4);
        panel7.setLayout(null);
        tabbedPane.addTab("User Manual Pt.4", icon, panel7,"This tab contains the user manual of the application.");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        JLabel labelText4 = new JLabel("User Manual -- All About SK Editor Function and More");
        labelText4.setHorizontalAlignment(JLabel.CENTER);
        labelText4.setVerticalAlignment(JLabel.CENTER);
        labelText4.setBounds(0, 0, 900, 28);
        panel7.add(labelText4);
        
        JLabel labelExit = new JLabel("<html>2.3 --All about exit icon--<br/>This icon to the far right of the software offers a shortcut to exit"
        		+ " for the editor.Of course a confirmation window<br/> will pop up for safety.After you select yes to exit your file is saved "
        		+ " automatically.</html>");
        labelExit.setBounds(0, 35, 900, 80);
        panel7.add(labelExit);
        
        JLabel labelSideBar = new JLabel("<html>2.4 --All about Side Vertical Bar Tools--<br/>The side vertical bar possess some useful tools"
        		+ " of functions such as print file,make bold text and convert to pdf.In<br/>order to use these three tools you must do the "
        		+ " following:<br/>1)Print File: This tool prints to physical print the entire file.In order to print your file you only have to"
        		+ " choose the<br/> print icon and then set your preferences about the print and that's it.<br/>2)Make Bold Text: This tool"
        		+ " is capable of making the selected text bold text.In order to activate you select some data<br/> for the file and then click the"
        		+ " Make Bold Icon.<br/>3)Convert To Pdf: This tool can be used to convert your current file to pdf file.Keep in mind that only"
        		+ " txt files and<br/>odt files can be converted to pdf not tex files.</html>");
        labelSideBar.setBounds(0, 115, 900, 140);
        panel7.add(labelSideBar);
                
        // -----------------------------------------------------------------------------------------------
        JComponent panel8 = GFactory2.createPanelServiceComplete(7);
        panel8.setLayout(null);
        tabbedPane.addTab("User Manual Pt.5 - Find Function", icon, panel8,"This tab contains the user manual of the application.");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
        
        JLabel labelText8 = new JLabel("User Manual -- All About SK Editor Function and More");
        labelText8.setHorizontalAlignment(JLabel.CENTER);
        labelText8.setVerticalAlignment(JLabel.CENTER);
        labelText8.setBounds(0, 0, 900, 28);
        panel8.add(labelText8);
        
        JLabel labelFindFunction = new JLabel("<html>2.5 --All about basic Find Function--<br/>This function is triggered by the blue find button"
        		+ ".In order to find anything you desire you must first type the<br/> keyword in the first text field and then press the find button"
        		+ " in order to initiate the find function. You must also<br/> select the scope or the direction in order to search your file.</html>");
        labelFindFunction.setBounds(0, 35, 900, 95);
        panel8.add(labelFindFunction);
        
        JLabel optionsList = new JLabel("<html>2.6 --All about Find Options Choises--<br/>Below there is a list with all the options about the find"
        		+ " function:<br/>1) Scope -> All: If you select this option the find function will search the whole file for the typed keyword.<br>"
        		+ "2)Direction -> Forward: If you select this option the find function will search your file from the middle of the <br>contents all the way to the end.<br/>"
        		+ "3)Direction -> Backward:  If you select this option the find function will search your file from the start of the <br/>contents until the middle of the file.<br/"
        		+ "4)Options -> Whole Word: This option can be selected at anytime and the find function will search the whole file<br/> for the keyword as a whole word and not part of another word or phrase.<br/>"
        		+ "5)Options -> Case Sensitive: This option provides to search the keyword amongst both uppercase and lowercase<br/> options.</html>");
        optionsList.setBounds(0,130,900,160);
        panel8.add(optionsList);
        
        JLabel functionsList = new JLabel("<html>2.7 --All about Replace and Delete Functions--<br>"
        		+ "After you typed and searched for a keyword you can do the following actions with it:<br/>"
        		+ "1) Replace: This function allows to replace one finding of the keyword at a time with the"
        		+ " typed keyword that in the<br/> Replace With textfield.<br/>"
        		+ "2) Replace All: This function allows to replace all the findings of the keyword at once with"
        		+ " the typed keyword in the<br/> Replace With text field.<br/>"
        		+ "3) Delete All: This function allow to delete All the findings of the keyword at once of the entire file"
        		+ ".<br/> Saving your file before Delete All is encouraged.</html>");
        functionsList.setBounds(0, 290, 900, 200);
        panel8.add(functionsList);
        
        // -----------------------------------------------------------------------------------------------
        JComponent panel3 = GFactory2.createPanelServiceComplete(6);
        tabbedPane.addTab("App Info", icon2, panel3,"This tab contains information about the application.");
        tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);
        
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
        tabbedPane.setMnemonicAt(6, KeyEvent.VK_7);
        
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