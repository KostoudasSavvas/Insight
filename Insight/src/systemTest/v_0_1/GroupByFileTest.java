package systemTest.v_0_1;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import groupby.GroupByFileHandler;

class GroupByFileTest {

	public static String logFilePath = "resources/HaliteChallenge__Halite-II/results/outputErrors.txt";

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File logFile = new File(logFilePath);
		System.out.println("File found at: " + logFile.getPath());
	}

	/**
	 * Tests the grouping by file ability of Insight
	 * @throws IOException
	 */
	
	@Test
	void test() throws IOException{
		
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
		
		
		System.out.println("Initial log file contents\n" + initialLogFileContents);
		
		
		GroupByFileHandler gpErrorHandler = new GroupByFileHandler();
		ArrayList<String> outputResult = gpErrorHandler.handleGroupError(initialLogFileContents);

		System.out.println("Output grouped by file result\n");
		for (String line : outputResult) {
			System.out.println(line);
		}		
		
	}

}
