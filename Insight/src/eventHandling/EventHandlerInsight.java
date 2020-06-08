package eventHandling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.text.StyledDocument;

import GenerateGui.EditWindow;
import GenerateGui.FindFunctionInsight;
import GenerateGui.MainEditorWindow;
import HardDriveManager.FileLoaderController;
import groupBy.GroupByFileHandler;
import groupBy.GroupErrorHandler;
import groupBy.GroupFactory;


public class EventHandlerInsight implements ActionListener{
	private JFrame InsightWindow;
	private JMenuItem btnPrint,btnFind,groupError,groupFile,btnEdit,btnRestore;
	private JList<String> schemaList;
	private JTextPane mainTextArea,secondaryTextArea;
	private String openDocPath="";
	private static String initialVersion = "";    // the contents of the log file that first gets loaded
	private int number;
	private HashMap<Integer,String> schemaNames;  // this hashMap contains the names of the sql files in the schema
	
	public EventHandlerInsight(JFrame InsightWindow,int number,JMenuItem btnPrint,JTextPane mainTArea,JTextPane secondaryArea,JList<String> schemaList,JMenuItem btnFind,JMenuItem groupError,JMenuItem groupFile,JMenuItem btnEdit,JMenuItem btnRestore){
		this.InsightWindow = InsightWindow;
		this.btnPrint = btnPrint;
		this.mainTextArea = mainTArea;
		this.secondaryTextArea = secondaryArea;
		this.schemaList = schemaList;
		this.btnFind = btnFind;
		this.groupError = groupError;
		this.groupFile = groupFile;
		this.number = number;
		this.btnEdit = btnEdit;
		this.btnRestore = btnRestore;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (number == 2){      // Event handling for the cancel button
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the Insight application?", "Exit Insight" , 0) == JOptionPane.YES_OPTION){
				InsightWindow.setVisible(false);
				System.exit(0);
			}
		}else if (number == 1){     // Event handling for the load button
						
			// -------------------------------------------------------------------------------------------------------------------------------- this code loads the file
			schemaNames = new HashMap<Integer, String>();
			FileLoaderController ldFile = new FileLoaderController();
			
			// necessary object for creating grouping handlers
			GroupFactory groupFactory = new GroupFactory();
			GroupByFileHandler groupByFileHandler = (GroupByFileHandler) groupFactory.createGroupHandlers("File");
			GroupErrorHandler groupErrorHandler =  (GroupErrorHandler) groupFactory.createGroupHandlers("Error");
			
			mainTextArea.setText(ldFile.load());
			openDocPath = ldFile.getName();  // this variable contains the path of the chosen log file
			initialVersion = mainTextArea.getText();   // contents of initial version after load
			
			// filling schema Names into a JList -----------------------------------------------------------------------------s
			BufferedReader bufReader = new BufferedReader(new StringReader(mainTextArea.getText()));
			String line;
			int schNumber = 1;  // counting the schema sql files
			ArrayList<String> minorContents = new ArrayList<String>();
			minorContents = ldFile.getMinorContents();
			
			
			try {
				while( (line = bufReader.readLine()) != null )
				{
						
					int fileIndex  = line.indexOf("File:");
					int lineIndex  = line.indexOf("Line:");
					
					if (fileIndex != -1 && lineIndex != -1) {
						String schemaName = line.substring(fileIndex + ("File:").length() + 1, lineIndex);
						
						boolean flag = false;
						for (int name: schemaNames.keySet()){
				            String value = schemaNames.get(name);
				            if (value.equals(schemaName)) {
				            	flag = true;
				            	break;
				            }
						}
						
						if (!flag) {
							schemaNames.put(schNumber, schemaName);
		    				schNumber ++;
						}
						
						flag = false;
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			DefaultListModel<String> listModel = new DefaultListModel<>();
			
			for (int name: schemaNames.keySet()){
	            String value = schemaNames.get(name);
	            listModel.addElement(value);
			}
			
			schemaList.setModel(listModel);
			// -------------------------------------------------------------------------------------------------------------------	Right Text Pane Load With Minor Errors	
			
			StyledDocument styledDoc = secondaryTextArea.getStyledDocument();
			
			for (String lineMinor : minorContents) {
				try {
					styledDoc.insertString(styledDoc.getLength(),lineMinor + "\n", null);
					styledDoc.insertString(styledDoc.getLength(),"----------------------------------" + "\n", null);
				}catch (Exception ex) {
					System.out.println(ex);
				}
			}	
			
			// ---------------------------------------------------------------------------------------------------------------------------------------- end of load code
			
			
			// handle right click on a file in the schema list, show pop up menu
	        schemaList.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                 if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {   // right click on any file and the editor opens
	                	 
	                	 // create the pop up menu
	                	 JPopupMenu popupMenu = new JPopupMenu();
	                	 JMenuItem btnEditItem = new JMenuItem("Edit File");
	                	 JMenuItem btnExitItem = new JMenuItem("Cancel");
	                	 popupMenu.add(btnEditItem);
	                	 popupMenu.addSeparator();
	                	 popupMenu.add(btnExitItem);
	                	 popupMenu.show(e.getComponent(), e.getX(), e.getY());
	                	 String selected = schemaList.getSelectedValue();
	                	 
	                	 // if this gets selected then the editor opens up with the selected file loaded
	                	 btnEditItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
			                	 MainEditorWindow editor = new MainEditorWindow(selected,openDocPath);
							}
						});
	                }
	            }
	        });
			
			
	        // handle edit button by showing list with the schema files
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditWindow editWindow = new EditWindow(schemaNames,openDocPath);
					editWindow.makeVisible();
				}
			});
			
			
			
			
			// grouping by Error
			groupError.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					groupErrorHandler.writeGroupOutput(mainTextArea, groupErrorHandler.handleGroupError(initialVersion));
				}
			});
			
			// group by file
			groupFile.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					groupByFileHandler.writeGroupOutput(mainTextArea, groupByFileHandler.handleGroupError(initialVersion));
				}
			});
			
			
			// restore initial log file contents to text panel
			btnRestore.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					mainTextArea.setText(initialVersion);
				}
			});
			
			
			// printing the main text pane
			btnPrint.addActionListener(new ActionListener() {				
		        public void actionPerformed(ActionEvent arg0) {
		            try {

		                boolean done = mainTextArea.print();
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
			});
			
			
			// finding action
			btnFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FindFunctionInsight ffw = new FindFunctionInsight(mainTextArea);
					ffw.makeVisible();
				}
			});
		}else{
			JOptionPane.showMessageDialog(null, "This action is false.Check the number k :)");
		}
	}
}