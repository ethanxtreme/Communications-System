package Testing;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	
	UserTester.class, 
	//ChatGroupTester.class,
	//ChatLogtester.class,
	ChatMessagetester.class, 
	NetworkMessagetester.class, 
	//Threadtester.class
	
})
public class TestSuite {
}
