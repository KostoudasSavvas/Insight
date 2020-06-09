package eventhandling;

public class EventHandlerSplit {
	// just a helper class that splits the inputs in necessary parts
	
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