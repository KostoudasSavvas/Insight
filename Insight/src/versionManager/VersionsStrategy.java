package versionManager;

public interface VersionsStrategy {
	void putVersion(Document doc);
	Document getVersion();
	Document[] getEntireHistory(String name);
	void removeVersion();	
}