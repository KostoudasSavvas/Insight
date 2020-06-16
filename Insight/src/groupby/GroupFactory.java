package groupby;

/**
 * This class is a factory class. The sole purpose of this class is to generate the
 * necessary objects for grouping by actions.
 * @author savaf
 *
 */
public class GroupFactory {
	
	/**
	 * This method creates the two group by objects. If the handlerType is equal
	 * to 'Error' then a GroupErrorHandler is created or if the handlerType is 
	 * equal to 'File' then a GroupFileHandler is created.
	 * @param handlerType
	 * @return
	 */
	
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
