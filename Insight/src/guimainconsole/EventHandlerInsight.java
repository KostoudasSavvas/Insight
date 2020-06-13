package guimainconsole;

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

import groupby.GroupByFileHandler;
import groupby.GroupErrorHandler;
import groupby.GroupFactory;
import guitexteditor.EditWindow;
import guitexteditor.MainEditorWindow;
import harddrivemanager.FileLoaderController;


/**
 * This class handles all the events that will happen in 
 * Insight application such as loading the contents of a log file, opening an editor
 * to edit a sql file, group by and find function.
 * @author savaf
 *
 */

public class EventHandlerInsight{
	private String openDocPath="";
	private static String initialVersion = "";    // the contents of the log file that first gets loaded
	private HashMap<Integer,String> schemaNames;  // this hashMap contains the names of the sql files in the schema
	
	
	
	// this method handles all the events for the Insight Application
	public void handleInsight(JFrame InsightWindow,JFrame ffw,EditWindow edw,JMenuItem btnCancel,JMenuItem btnLoad,JMenuItem btnPrint,JTextPane mainTArea,JTextPane secondaryArea,JList<String> schemaList,JMenuItem btnFind,JMenuItem groupError,JMenuItem groupFile,JMenuItem btnEdit,JMenu serviceMenu,JMenuItem restoreItem) {
		btnLoad.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				
				
				// -------------------------------------------------------------------------------------------------------------------------------- this code loads the file
				schemaNames = new HashMap<Integer, String>();
				FileLoaderController ldFile = new FileLoaderController();
				
				// necessary object for creating grouping handlers
				GroupFactory groupFactory = new GroupFactory();
				GroupByFileHandler groupByFileHandler = (GroupByFileHandler) groupFactory.createGroupHandlers("File");
				GroupErrorHandler groupErrorHandler =  (GroupErrorHandler) groupFactory.createGroupHandlers("Error");
				
				mainTArea.setText(ldFile.load());
				openDocPath = ldFile.getName();  // this variable contains the path of the chosen log file
				initialVersion = mainTArea.getText();   // contents of initial version after load
				
				// filling schema Names into a JList -----------------------------------------------------------------------------s
				BufferedReader bufReader = new BufferedReader(new StringReader(mainTArea.getText()));
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
				
				StyledDocument styledDoc = secondaryArea.getStyledDocument();
				
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
						edw.setSchemaNames(schemaNames);
						edw.setSchemaPath(openDocPath);
						edw.makeVisible();
						edw.eventListenerGenerate(edw.getEditWindow(), openDocPath, edw.getNames(), edw.getCancelB());
					}
				});
				
				
				
				
				// grouping by Error
				groupError.addActionListener(new ActionListener() {				
					public void actionPerformed(ActionEvent e) {
						groupErrorHandler.writeGroupOutput(mainTArea, groupErrorHandler.handleGroupError(initialVersion));
					}
				});
				
				// group by file
				groupFile.addActionListener(new ActionListener() {				
					public void actionPerformed(ActionEvent e) {
						groupByFileHandler.writeGroupOutput(mainTArea, groupByFileHandler.handleGroupError(initialVersion));
					}
				});
				
				
				// restore initial log file contents to text panel
				restoreItem.addActionListener(new ActionListener() {				
					public void actionPerformed(ActionEvent e) {
						mainTArea.setText(initialVersion);
					}
				});
				
				
				// printing the main text pane
				btnPrint.addActionListener(new ActionListener() {				
			        public void actionPerformed(ActionEvent arg0) {
			            try {

			                boolean done = mainTArea.print();
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
						ffw.setVisible(true);
					}
				});
			}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the Insight application?", "Exit Insight" , 0) == JOptionPane.YES_OPTION){
					InsightWindow.setVisible(false);
					System.exit(0);
				}
			}
		});
	}
}