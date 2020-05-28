package GenerateGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GuiFactory {
	
	// method create for the JFrame main
	public JFrame createFrame(String name,int x1,int x2,int y1, int y2){
		JFrame LPMainWindow = new JFrame(name);
		LPMainWindow.setResizable(true);
		LPMainWindow.setBounds(x1,x2,y1,y2);
		LPMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LPMainWindow.setVisible(true);
		return LPMainWindow;
	}
	
	public JFrame createFrameEditor(String name,int x1,int x2,int y1, int y2){
		JFrame Window = new JFrame(name);
		Window.setResizable(true);
		Window.setBounds(x1,x2,y1,y2);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Window.setExtendedState(JFrame.MAXIMIZED_BOTH);   if we want full screen the editor remove the comments from the commands
		//Window.setUndecorated(true);
		return Window;
	}
	
	
	public JLabel createLabelBar(JMenuBar bar,String text){
		JLabel label = new JLabel(text);
		bar.add(label);
		return label;
	}
	
	
	// method to create the main JPanel
	public JPanel createMainPanel(JFrame window,int Number){
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(700, 700, 900, 900));
		panel.setSize(700, 900);
		
		if (Number == 1){
			panel.setBackground(Color.LIGHT_GRAY);
		}else if (Number == 2){
			panel.setBackground(Color.DARK_GRAY);
		}else{
			panel.setBackground(Color.WHITE);
		}
		
		window.getContentPane().add(panel);
		panel.setLayout(null);
		return panel;
	}
	
	// method to create the secondary JPanel elements in the window
	public JPanel createSecondaryPanel(JPanel mainPanel,int x1,int x2,int y1, int y2){
		JPanel panel  = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(x1, x2, y1, y2);
		mainPanel.add(panel);
		return panel;
	}
	
	public JTextField createField(JPanel panel,int x1,int x2,int y1, int y2,int columns){
		JTextField field = new JTextField();
		field.setBounds(x1, x2, y1, y2);
		field.setColumns(columns);
		panel.add(field);
		return field;
	}
	
	public JMenuBar createBar(JPanel mainPanel){
		JMenuBar bar = new JMenuBar();
		bar.setBounds(0,0, 795, 23);
		bar.setSize(795, 23);
		mainPanel.add(bar);
		return bar;
	}
	
	
	public JMenu createMenu(JMenuBar bar,String text,String ImageName){
		JMenu menu = new JMenu();
		menu.setToolTipText(text);
		setImage(menu,ImageName);                    
		bar.add(menu);
		return menu;
	}
	
	public JRadioButton createJCheckButton(JPanel panel,String name,int x1,int x2,int y1,int y2){
		JRadioButton item = new JRadioButton(name);
		item.setBounds(x1,x2,y1,y2);
		panel.add(item);
		return item;
	}
	
	public JLabel createLabelFind(JPanel panel,String name,int x1, int y1, int x2, int y2) {
		JLabel labelF = new JLabel(name);
		labelF.setBounds(x1, y1, x2, y2);
		labelF.setForeground(new Color(31,190,214));
		panel.add(labelF);
		return labelF;
	}
	
	public JPanel createPanelFind(JFrame mainWindow){
		JPanel panel = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(31,190,214));
                
                // Scope box implemented by four lines
                g.drawLine(60, 190, 155, 190);
                g.drawLine(155, 190, 155, 250);
                g.drawLine(155, 250, 12, 250);
                g.drawLine(12, 250, 12, 190);
                
                // Direction box implemented by four lines
                g.drawLine(267 , 190, 340, 190);
                g.drawLine(340, 190, 340, 250);
                g.drawLine(340, 250, 200, 250);
                g.drawLine(198, 250, 198, 190);
                
                // Options box implemented by four lines
                g.drawLine(70, 275, 340, 275);
                g.drawLine(340, 275, 340, 320);
                g.drawLine(340, 320, 13, 320);
                g.drawLine(13, 320, 13, 275);
            }
        };
		panel.setBounds(new Rectangle(500, 500, 500, 500));
		panel.setSize(300,400);
		
		mainWindow.getContentPane().add(panel);
		panel.setLayout(null);
		return panel;
	}
	
	public JButton createFindButton(JPanel panel,String name,int x1,int x2,int y1, int y2,String text) {
		JButton button = new JButton(name);
		button.setBounds(x1,x2,y1,y2);
		button.setFont(new Font("Calibri", Font.PLAIN, 14));
	    button.setBackground(new Color(31,190,214));
	    button.setForeground(Color.white);
		button.setToolTipText(text);
		button.setUI(new StyledButtonUI());
		panel.add(button);
		return button;
	}
	
	public void setImage(JMenu menu,String ImageName){
		try {
			   Image img = ImageIO.read(getClass().getResource(ImageName));
			   menu.setIcon(new ImageIcon(img));
			 } catch (Exception ex) {
			   System.out.println(ex);
			 }
	}
	
	public void setImageItem(JMenuItem menuItem,String ImageName) {
		try {
			   Image img = ImageIO.read(getClass().getResource(ImageName));
			   menuItem.setIcon(new ImageIcon(img));
			 } catch (Exception ex) {
			   System.out.println(ex);
			 }
	}
	
	public JMenuItem createMenuItem(JMenu menu,String name,String text){
		JMenuItem item = new JMenuItem(name);
		item.setToolTipText(text);
		menu.add(item);
		return item;
	}
	
	public JCheckBoxMenuItem createJCheckMenuItem(JMenu menu,String name,String text){
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(name);
		item.setToolTipText(text);
		menu.add(item);
		return item;
	}
	
	public JMenuItem createMenuItemWithIcon(JMenu menu,String name,String ImageName,String text) {
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setToolTipText(text);
		setImageItem(menuItem,ImageName);                    
		menu.add(menuItem);
		return menuItem;
	}
	
	public JTextPane createArea(JPanel panel){
		JTextPane area = new JTextPane();
		area.setSize(700, 800);
		area.setAutoscrolls(true);           // lines of
		area.setEditable(true);             // to change line if writing reaches the size of window
		Font f = new Font("Plain",Font.PLAIN,14);
        area.setFont(f);
		panel.add(area);
		return area;
	}
	
	public void onExit(JFrame frame, boolean dispose){

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "title" , 0) == JOptionPane.YES_OPTION){
					if (dispose){
						frame.setDefaultCloseOperation(2);
					} else {
						frame.setDefaultCloseOperation(3);
					}
				} else {
					frame.setDefaultCloseOperation(0);
				}
			}
		});
	}	
}