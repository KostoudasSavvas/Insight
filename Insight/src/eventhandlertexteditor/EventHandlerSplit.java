package eventhandlertexteditor;

/**
 * This is a helper class which provies the functionality
 * of splitting the given text with a number of ways.
 * @author savaf
 *
 */

public class EventHandlerSplit {
	
	public String splitText(String text){
		String[] arrayS = text.split("[.]");
		return arrayS[0];
	}
	
	public String splitArrayList(String text){
		String[] arrayS = text.split("\\[");
		String[] result = arrayS[1].split("\\]");
		return result[0];
	}
}