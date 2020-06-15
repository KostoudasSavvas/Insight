package systemTest.v_0_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import harddrivemanager.FileLoaderController;

public class LoadLogFileTest {
	
	// this par contains the relative path of the log file
	public static String logFilePath = "resources/HaliteChallenge__Halite-II/results/outputErrors.txt";


	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		File logFile = new File(logFilePath);
		System.out.println("File found at: " + logFile.getPath());
	}

	
	/**
	 * Tests the loading of a log file
	 * @throws IOException
	 */
	
	@Test
	public void test() throws IOException{
		File logFile = new File(logFilePath);
		FileLoaderController ldFileLoad = new FileLoaderController();
		String logContents = ldFileLoad.load();
		
		String logFileContentsWithTestLoad = "";
		
	    try
        {
	    	logFileContentsWithTestLoad = new String (Files.readAllBytes(logFile.toPath()));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }		
				
		
		assertEquals(logContents,logFileContentsWithTestLoad);
	}
}
