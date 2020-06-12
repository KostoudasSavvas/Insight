package guimainconsole;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class ServiceMenuHandler {
	
	public void eventListenerService(JMenu serviceMenu) {
		serviceMenu.addMenuListener(new MenuListener(){
			 public void menuSelected(MenuEvent e) {
				 ServiceMenuWindowCreator serviceWindow = new ServiceMenuWindowCreator();
				 serviceWindow.makeVisible();
			 }

			 public void menuDeselected(MenuEvent e) {
			 }

			 public void menuCanceled(MenuEvent e) {
			 }
		});
	}
}
