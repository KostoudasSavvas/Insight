package guitexteditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * this class constructs the Edit window which is the window that contains
 * all the sql file that exist in the schemata folder.
 * @author savaf
 *
 */


public class EditWindow {

	private JFrame editWindow;
	private JPanel contentPane;
	private HashMap<Integer, String> schemaNames;
	private String schemaPath;
	private JList<String> list_1;
	private JButton btnNewButton_1;
	
	/**
	 * This method constructs the EditWindow which later on will be filled with the schema
	 * sql files.
	 * @param schemaNames
	 * @param schemaPath
	 */
	
	public EditWindow(HashMap<Integer, String> schemaNames,String schemaPath) {
		this.schemaNames = schemaNames;
		this.schemaPath = schemaPath;
		
		editWindow = new JFrame("Insight Edit File Selector");
		editWindow.setResizable(false);
		editWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		

		editWindow.setBounds(100, 100, 351, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		editWindow.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(43, 69, 244, 283);
		contentPane.add(scrollPane);
		
		
		list_1 = new JList<String>();
		list_1.setBorder(new LineBorder(SystemColor.textHighlight));
		list_1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(list_1);
		
		JLabel lblNewLabel = new JLabel("Select any of the following sql files to edit or press Cancel");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 21, 315, 40);
		contentPane.add(lblNewLabel);
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		btnNewButton_1.setToolTipText("Select this button to return to Insight application");
		btnNewButton_1.setBounds(161, 381, 89, 23);
		contentPane.add(btnNewButton_1);
		
		eventListenerGenerate(editWindow,schemaPath,list_1,btnNewButton_1);
		
	}
	
	/**
	 * This method handles the right click on each schema sql file to open the editor
	 * to edit each file.s
	 * @param editWindow
	 * @param schemaPath
	 * @param schemaNames
	 * @param btnCancel
	 */
	
	public void eventListenerGenerate(JFrame editWindow,String schemaPath,JList<String> schemaNames,JButton btnCancel) {
		// handle right click on a file in the schema list, show pop up menu
        schemaNames.addMouseListener(new MouseAdapter() {
            
            public void mousePressed(MouseEvent e) {
                 if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {   // right click on any file and the editor opens
                	 
                	 // create the pop up menu
                	 JPopupMenu popupMenu = new JPopupMenu();
                	 JMenuItem btnEditItem = new JMenuItem("Edit File");
                	 JMenuItem btnExitItem = new JMenuItem("Cancel");
                	 popupMenu.add(btnEditItem);
                	 popupMenu.addSeparator();
                	 popupMenu.add(btnExitItem);
                	 popupMenu.show(e.getComponent(), e.getX(), e.getY());
                	 String selected = schemaNames.getSelectedValue();
                	 
                	 // if this gets selected then the editor opens up with the selected file loaded
                	 btnEditItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
		                	 MainEditorWindow editor = new MainEditorWindow(selected,schemaPath);
						}
					});
                }
            }
        });
		
        // handle cancel button and exiting
        btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editWindow.setVisible(false);
			}
		});
	}
	
	/**
	 * This method fills the JList of the EditWindow
	 * with the schema sql file names which the errors exist to.
	 * @param schemaNames
	 */
	
	public void setSchemaNames(HashMap<Integer, String> schemaNames) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		// fill the list model  with the schema names
		for (int name: schemaNames.keySet()){
            String value = schemaNames.get(name);
            listModel.addElement(value);
		}
		
		list_1.setModel(listModel);
	}
	
	public void setSchemaPath(String schemaP) {
		this.schemaPath = schemaP;
	}
	
	
	public JFrame getEditWindow() {
		return editWindow;
	}
	
	public JList<String> getNames(){
		return list_1;
	}
	
	public JButton getCancelB() {
		return btnNewButton_1;
	}
	
	
	public void makeVisible() {
		editWindow.setVisible(true);
	}
}
