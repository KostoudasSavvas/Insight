package systemTest.v_0_1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import harddrivemanager.FileSavingController;

class SaveSqlFileTest {

	// this par contains the relative path of the sql file
	public static String sqlFilePath = "resources/HaliteChallenge__Halite-II/schemata/1508275975.sql";
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File sqlFile = new File(sqlFilePath);
		System.out.println("File found at: " + sqlFile.getPath());
	}
	
	/**
	 * Tests the saving ability of the Insight application
	 * by adding current date and author at the end of the tested file
	 */

	@Test
	void test() {
		File sqlFile = new File(sqlFilePath);
		String sqlFileContents = "";
		
	    try
        {
            sqlFileContents = new String (Files.readAllBytes(sqlFile.toPath()));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
		
	    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	    Date date = new Date(System.currentTimeMillis());
	    
	    // add a line in the end of the sql file author and date for instance
	    sqlFileContents += "\n" + "Author: savaf" + " " + "Date: " + formatter.format(date);
	    
	    FileSavingController fileSavingManager = new FileSavingController();
	    fileSavingManager.save(sqlFilePath, sqlFileContents);
	    
	    try
        {
            sqlFileContents = new String (Files.readAllBytes(sqlFile.toPath()));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	    
	    System.out.println(sqlFileContents);
	}

}
