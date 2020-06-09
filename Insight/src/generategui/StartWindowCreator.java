package generategui;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 
public class StartWindowCreator{
	static JProgressBar b; 
	
	public void initialize() throws IOException {
		
	    JPanel panel = new JPanel();
	    JLabel label = new JLabel();
	    try {
			   Image img = ImageIO.read(getClass().getResource("/pngLoading.png"));
			   label.setIcon(new ImageIcon(img));
			 } catch (Exception ex) {
			   System.out.println(ex);
			 }
	    b = new JProgressBar();     
        // set initial value 
        b.setValue(0);
        b.setStringPainted(true); 
        b.setForeground(Color.BLACK);
        b.setBackground(Color.blue);
        b.setPreferredSize( new Dimension (200,30));
	    panel.add(label);
	    panel.add(b);
	    JFrame frame = new JFrame("SK Editor");

	    // add the Jpanel to the main window
	    frame.add(panel); 
	    frame.setBounds(500,250,360,325);
	    frame.setUndecorated(true);
	    frame.setVisible(true);
	    fill(); 

	    final JDialog dialog = new JDialog(frame, "Test", true);

	    //Must schedule the close before the dialog becomes visible
	    ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();     
	    s.schedule(new Runnable() {
	        public void run() {
	            dialog.setVisible(false); //should be invoked on the EDT
	            frame.setVisible(false);
	            dialog.dispose();
	            frame.dispose();
	        }
	    }, 0, TimeUnit.SECONDS);

	     dialog.setVisible(true); // if modal, application will pause here
	}
	
	public static void fill() { 
        int i = 0; 
        try { 
            while (i <= 120) { 
                // set text accoring to the level to which the bar is filled 
                if (i > 30 && i < 70) 
                    b.setString("Wait Sor Sometime"); 
                else if (i > 70) 
                    b.setString("Almost Finished Loading"); 
                else
                    b.setString("Loading Started"); 
  
                // fill the menu bar 
                b.setValue(i + 10); 
  
                // delay the thread 
                Thread.sleep(1000); 
                i += 50; 
            } 
        } 
        catch (Exception e) { 
        } 
    } 	
}