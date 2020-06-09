package versionmanager;

import java.util.ArrayList;

public class Document {
	private int VersionId;
	private String author;
	private String date;                     // exact date of the last modification date
	private ArrayList<String> contents;            // arraylist with all the content of the version
	private String copyright;
	
	public Document(int VersionId,String author,String date,ArrayList<String> contents,String copyright){
		this.VersionId = VersionId;
		this.author = author;
		this.date = date;
		this.contents = contents;
		this.copyright = copyright;
	}
		
	public void setContents(String text){
		contents.add(text);
	}
	
	public String getContents(){
		return contents.toString();
	}
	
	public int getVersionId(){
		return VersionId;
	}
	
	public String getCopyRight(){
		return copyright;
	}
}