package cmdAnimator.GameCanvasActions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.FrameAnimatorTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class FpsCommandExecutorTests {
	
	private static final int DEFAULT_FPS = 20;
	FrameAnimator Animation;
	ICommandExecutor fce;
	
	@Before
	public void setUp(){
		Animation = GameAnimator.getInstance();
		Animation.setFPS(DEFAULT_FPS);
		fce = new FpsCommandExecutor();
	}

	@Test(expected = InvalidCommandException.class)
	public void isSetFPSParametersAreBlankThrowException() throws InvalidCommandException{
		fce.execute(new String[]{""});
	}
	
	@Test
	public void isSetFPSParametersAre25AnimationframeRateIs25() throws InvalidCommandException{
		fce.execute(new String[]{"25"});
		assertEquals(25, Animation.getFPS());
	}
	
	@Test(expected = InvalidCommandException.class)
	public void isSetFPSParametersAreNullThrowsException() throws InvalidCommandException{
		Animation.setFPS(DEFAULT_FPS);
		fce.execute(null);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void isSetFPSParameters25AndStringThrowsException() throws InvalidCommandException{
		fce.execute(new String[]{"24","hi"});
		
	}
	
	@Test(expected = InvalidCommandException.class)
	public void isSetFPSParametersIsNegativeThrowsException() throws InvalidCommandException{
		fce.execute(new String[]{"-24"});
		
		assertEquals(DEFAULT_FPS, Animation.getFPS());
	}
	
	@Test(expected = InvalidCommandException.class)
	public void isSetFPSParametersIsZeroThrowsException() throws InvalidCommandException{

		fce.execute(new String[]{"0"});
	}
	
	@Test(expected = InvalidCommandException.class)
	public void isSetFPSParametersIs3dot5DoNotSetFPSThrowsException() throws InvalidCommandException{
		fce.execute(new String[]{"3.5"});

	}
	

	public static class dummyApp extends Application {
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    }
	}

	@BeforeClass
	public static void initJFX() throws InterruptedException {
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(dummyApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	    Thread.sleep(500);
	}
}
