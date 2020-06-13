package versionmanager;

/**
 * This class implements the VersionsStrategy interface and handles 
 * all the actions about the Documents.
 * @author savaf
 *
 */

public class VolatileVersionsStrategy implements VersionsStrategy{

	private Document[] DocList = new Document[300];         // the list of versions in history
	private int NumberOfVersions = 0;
		
	public void putVersion(Document version) {
		DocList[NumberOfVersions] = version;
		NumberOfVersions++;
	}
	
	public Document getVersion() {
		return DocList[NumberOfVersions - 1];
	}
	
	public void removeVersion() {
		if (NumberOfVersions == 0){
			System.out.println("Initial empty version of the document has reached");
		}else{
			DocList[NumberOfVersions] = null;
			NumberOfVersions--;
			System.out.println("Current version has removed from history");
		}
	}

	public Document[] getEntireHistory(String name) {
		return DocList;
	}
	
	public int getNumberVersions(){
		return NumberOfVersions;
	}
}