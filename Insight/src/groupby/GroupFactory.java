package groupby;

// this class is responsible of creating the objects for Grouping By

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
