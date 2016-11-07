package cmdAnimator.GameCanvasActionsTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddCommandExecutorTests.class, CanvasImageTests.class, CanvasTextTests.class,
		CommandFileRunnerTests.class, CommandParserTests.class, FpsCommandExecutorTests.class, FrameAnimatorTests.class,
		GoToCommandExecutorTests.class, HelpCommandExecutorTests.class, RemoveCommandExecutorTests.class })
public class AllTests {

}
