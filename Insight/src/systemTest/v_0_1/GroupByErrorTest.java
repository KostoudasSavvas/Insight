package systemTest.v_0_1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import groupby.GroupErrorHandler;

public class GroupByErrorTest {
	
	// this par contains the relative path of the log file
	public static String logFilePath = "resources/HaliteChallenge__Halite-II/results/outputErrors.txt";

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Test starts for Group by Error \n");
		File logFile = new File(logFilePath);
		System.out.println("File found at: " + logFile.getPath());
	}

	/**
	 * Tests the Grouping by Error ability of Insight
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
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
		
		GroupErrorHandler gpErrorHandler = new GroupErrorHandler();
		ArrayList<String> outputResult = gpErrorHandler.handleGroupError(initialLogFileContents);

		
		assertNotNull(outputResult);
	}

}
