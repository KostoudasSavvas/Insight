package systemTest.v_0_1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GroupByErrorTest.class, GroupByFileTest.class, LoadLogFileTest.class, SaveSqlFileTest.class })
public class TestSuite {

}
