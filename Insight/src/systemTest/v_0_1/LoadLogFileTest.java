package systemTest.v_0_1;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
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
		FileLoaderController ldFileLoad = new FileLoaderController();
		String contents = ldFileLoad.load();
		ArrayList<String> minorContents = ldFileLoad.getMinorContents(); 
		
				
		for (String line: minorContents) {
			contents += line + "\n";
		}
		
		System.out.println("Loader log file contents\n" + contents);
		assertNotNull(contents);
	}
}
