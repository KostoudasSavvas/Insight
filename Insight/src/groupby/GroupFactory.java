package groupby;

/**
 * This class is a factory class. The sole purpose of this class is to generate the
 * necessary objects for grouping by actions.
 * @author savaf
 *
 */
public class GroupFactory {
	
	public GroupBy createGroupHandlers(String handlerType) {
		
		if (handlerType.equals("Error")) {
			return new GroupErrorHandler();
		}else if (handlerType.equals("File")){
			return new GroupByFileHandler();
		}else {   // this is is something went wrong
			return null;
		}
	}
}
