package Testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({

        UserTest.class,
        ThreadTest.class,
        ChatMessageTest.class,
        //ChatGroupTester.class,
        //ChatLogtester.class,
        //NetworkMessagetester.class,


})
public class TestSuite {
}
