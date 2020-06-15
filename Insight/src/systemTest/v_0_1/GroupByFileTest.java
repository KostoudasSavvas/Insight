package systemTest.v_0_1;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import groupby.GroupByFileHandler;

public class GroupByFileTest {

	public static String logFilePath = "resources/HaliteChallenge__Halite-II/results/outputErrors.txt";

	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Tests starts for Group by File \n");
		File logFile = new File(logFilePath);
		System.out.println("File found at: " + logFile.getPath());
	}

	/**
	 * Tests the grouping by file ability of Insight
	 * @throws IOException
	 */
	
	@Test
	public void test() throws IOException{
		
		File logFile = new File(logFilePath);
		String initialLogFileContents = "";
		
		
	    try
        {
            initialLogFileContents = new String (Files.readAllBytes(logFile.toPath()));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
				
		
		GroupByFileHandler gpErrorHandler = new GroupByFileHandler();
		ArrayList<String> outputResult = gpErrorHandler.handleGroupError(initialLogFileContents);
		
		assertNotNull(outputResult);
	}

}
