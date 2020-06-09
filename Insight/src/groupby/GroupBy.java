package groupby;

import java.util.ArrayList;
import javax.swing.JTextPane;

public interface GroupBy {
	public ArrayList<String> handleGroupError(String contents);
	public void writeGroupOutput(JTextPane textArea, ArrayList<String> groupLines);
}
