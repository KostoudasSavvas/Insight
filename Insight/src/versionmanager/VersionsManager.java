package versionmanager;

public class VersionsManager {
	private Boolean Enabled;
	private VersionsStrategy VS;
	
	public VersionsManager(VersionsStrategy VS){
		this.VS = VS;
	}
}