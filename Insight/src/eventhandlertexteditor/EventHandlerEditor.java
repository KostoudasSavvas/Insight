package eventhandlertexteditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.Element;

//import guitexteditor.FindFunctionWindow;
import versionmanager.Document;
import versionmanager.VersionsManager;
import versionmanager.VolatileVersionsStrategy;

/**
 * This class is responsible of handling all the events that will happen
 * inside the editor window. These events consist of loading the contents of the sql file,
 * printing the sql file, finding any word with find function as well as saving the contents
 * and undo typing. 
 * @author savaf
 *
 */

public class EventHandlerEditor {
	static JFrame newWindow;
	private String openDocPath="";
	private static String initialVersion = "";    // the contents of the version that first gets loaded
	private EventHandlerRollBack rb = new EventHandlerRollBack();       // important object to handle the rollback function
	private EventHandlerSave sv = new EventHandlerSave();               // important object to handle the save function
	private EventHandlerSplit splitter = new EventHandlerSplit();       // an object in order to split the text in necessary parts
	private int Number = 0;                                            // an integer needed for handling the volatile storage
	
	
	
	/**
	 * This method handles all the events of the Insight Editor.
	 * @param schemaPath
	 * @param fileName
	 * @param newWindow
	 * @param ffw
	 * @param checkBox1
	 * @param buttonClear
	 * @param buttonRol
	 * @param buttonSave
	 * @param btnCancel
	 * @param textArea
	 * @param linesArea
	 * @param labelN
	 * @param printerMenu
	 * @param btnFind
	 */
	
	public void handleEdit(String schemaPath,String fileName,JFrame newWindow,JFrame ffw,JCheckBoxMenuItem checkBox1,JMenuItem buttonClear,JMenuItem buttonRol,JMenuItem buttonSave,JMenuItem btnCancel,JTextPane textArea,JTextArea linesArea,JLabel labelN,JMenu printerMenu,JMenuItem btnFind) {
		VolatileVersionsStrategy vt1 = new VolatileVersionsStrategy();
		VersionsManager mg2 = new VersionsManager(vt1);

		// -------------------------------------------------------------------------------------------------------------------------------- this code loads the file
		
		
		if (fileName != "" && schemaPath != "") {
			
		    File f = new File(schemaPath + File.separator + "schemata" + File.separator + fileName);
			FileReader reader = null;
			try {
				reader = new FileReader(f);
			} catch (FileNotFoundException e1) {
				System.out.println("File was not found. Incorrect file Path");
				System.exit(-1);
			}
		    BufferedReader br = new BufferedReader(reader);
		  
		    try {
				textArea.read(br, null);
				
				// adding again the document listener so that the lines area to be on point
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
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		    
		    try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    textArea.requestFocus();
		    JOptionPane.showMessageDialog(null, fileName, "Directory of chosen sql document", JOptionPane.INFORMATION_MESSAGE);
			labelN.setText("                                                                                                               Editing Now: "+  fileName);		
			initialVersion = textArea.getText();   // contents of initial version after load
			openDocPath = schemaPath + File.separator + "schemata" + File.separator + fileName;
		}else {
			// something error happened if the control came here, so print error and exit safely
		    JOptionPane.showMessageDialog(null, "Filepath or Filename is invalid", "Invalid Directory", JOptionPane.ERROR_MESSAGE);
		    System.exit(-1);			
		}
			
		// -------------------------------------------------------------------------------------------------------------------- -------------------- end of load code
		    
		// event handling for the save button
		buttonSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (checkBox1.isSelected()){         // volatile storage
					ArrayList<String> list = new ArrayList<String>();
					list.add(textArea.getText());
					Document doc1 = new Document(Number,"savvas","21-5-2020",list,splitter.splitText(fileName+"Log"+Number+".txt"));
					vt1.putVersion(doc1);
					Number++;
    				sv.save(textArea, openDocPath, list);
    				JOptionPane.showMessageDialog(null, "Current document saved","Saving Action Successfull", JOptionPane.INFORMATION_MESSAGE);
				}else{
    				JOptionPane.showMessageDialog(null, "Select the volatile checkbox in order to save your file.","No storage type selected", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		    
		   
		// event handling for the roll back button in order to roll back to a previous version
		buttonRol.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	Document[] docList = vt1.getEntireHistory(splitter.splitText(fileName));    // a list with all the volatile strategy versions
		    	if (checkBox1.isSelected() && Number > 0){
			    	textArea.setText(rb.rollBack(Number,textArea, splitter.splitText(fileName), checkBox1, docList));
			    	Number--;
		    	}else if (checkBox1.isSelected() && Number == 0) {
					JOptionPane.showMessageDialog(null,"Initial contents were loaded.","Undo Manager", JOptionPane.INFORMATION_MESSAGE);
					textArea.setText(initialVersion);
		    	}
		    }
		});
		
		// event handling for clear the entire doc
		buttonClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the entire file?", "" , 0) == JOptionPane.YES_OPTION){
					textArea.setText(null);
				}
			}
		});
		
		// event Handling for find button
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ffw.setVisible(true);
			}
		});
		
		// event handling for the print button
		printerMenu.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				try {
	                boolean done = textArea.print();
	                if (done) {
	                    JOptionPane.showMessageDialog(null, "Printing is done");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Printing Cancelled");
	                }
	            } catch (Exception pex) {
	                JOptionPane.showMessageDialog(null, "Error while printing");
	                pex.printStackTrace();
	            }
			 }
			 public void menuDeselected(MenuEvent e) {
			 }
			 public void menuCanceled(MenuEvent e) {
			 }
		});
		
		// event Handling for the exit button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to return to main menu? An automatic save of the Sql file will happen.", "Close Window" , 0) == JOptionPane.YES_OPTION){
					if (checkBox1.isSelected()){         // volatile storage
						ArrayList<String> list = new ArrayList<String>();
						list.add(textArea.getText());
						Document doc1 = new Document(Number,"savvas","21-5-2020",list,splitter.splitText(fileName)+"Log"+Number+".txt");
						vt1.putVersion(doc1);
						Number++;
	    				sv.save(textArea, openDocPath, list);
	    				JOptionPane.showMessageDialog(null, "Current document saved","Saving Action Successfull", JOptionPane.INFORMATION_MESSAGE);
	    				
						// finally after succesfull save close the editor
						newWindow.setVisible(false);
					}else{
	    				JOptionPane.showMessageDialog(null, "Select the volatile checkbox in order to save your file.","No storage type selected", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
	}
}