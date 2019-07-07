package cbfx.ui.mainframe;

import autoitx4java.AutoItX;
import cbf.utils.LogUtils;

import static cbf.engine.TestResultLogger.failed;
import static cbf.engine.TestResultLogger.passed;

public class EmulatorStart {
	
	public Process launchEmulator(String path){
		Runtime runtime = Runtime.getRuntime();
		String exePath = path.split("\\|")[0];
		String wsPath = path.split("\\|")[1];
		Process process = null;
		try{
			process = runtime.exec(exePath+" "+wsPath);
			passed("Verify Login to emulator", "Should login to emulator", "Logged in to Emulator");
		}catch(Exception e){
			logger.handleError("Error Logging in to Emulator: "+ e);
			failed("Verify Login to emulator", "Should login to emulator", "Error Logging in to Emulator"+ e.getMessage());
		}
		return process;
	}
	
	public void login(String usernamepwd){
		String username = usernamepwd.split("\\|")[0];
		String pwd = usernamepwd.split("\\|")[0];
		try{
			if (sessionWindow != null ){
			sessionWindow = getSessionWindow();
			sessionWindow.winWaitActive("System i signon");
			
			sessionWindow.ControlSetText("System i signon","","[CLASS:Edit; INSTANCE:2]", username);
			sessionWindow.ControlSetText("System i signon","","[CLASS:Edit; INSTANCE:3]", pwd);
			sessionWindow.controlClick("System i signon","","[CLASS:Button; INSTANCE:1]");
			}
			Thread.sleep(1000);
			passed("Verify Login to emulator", "Should login to emulator", "Logged in to Emulator");
		}catch(Exception e){
			logger.handleError("Error Logging in to Emulator: "+ e);
			failed("Verify Login to emulator", "Should login to emulator", "Error Logging in to Emulator"+ e.getMessage());
		}
	}
	
	public void closeWindow(){
		try{
			sessionWindow = getSessionWindow();
			if (sessionWindow != null ){
			sessionWindow.winClose("Session");
			}else{
				failed("Verify emulator window closed", "Should close emulator window", "Error in closing emulator window");
			}
			passed("Verify emulator window closed", "Should close emulator window", "Emulator window closed");
		}catch(Exception e){
			logger.handleError("Error in closing emulator window: "+ e);
			failed("Verify emulator window closed", "Should close emulator window", "Error in closing emulator window"+e.getMessage());
		}
	}
	
	public AutoItX getSessionWindow(){
		try{
			if(sessionWindow == null){
				sessionWindow = new AutoItX();
				}
			return sessionWindow;
			}catch(Exception e){
				logger.handleError("Error in getting emulator session window: "+ e);
			}
		return null;
		}
	
	public String getSessionTitle(){	

			sessionWindow = getSessionWindow();
			if (sessionWindow != null ){
				return sessionWindow.winGetTitle("Session");
			}else{
				return null;
			}
		}
	public String getSessionName(){	
		String name = null;
		String title = getSessionTitle();
		if (title != null){
		name = title.split(" ")[1];
		return name;
		}else{
			return null;
		}
	}
	
	
	protected AutoItX sessionWindow;
	private LogUtils logger = new LogUtils(this);
}
