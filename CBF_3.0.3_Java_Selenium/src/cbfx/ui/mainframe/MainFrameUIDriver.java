package cbfx.ui.mainframe;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.Map;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import cbf.utils.LogUtils;
import cbf.engine.TestResultLogger;
import cbfx.ui.objectmap.ObjectMap;
import cbfx.ui.objectmap.ObjectMapFactory;
import static cbf.engine.TestResultLogger.failed;
import static cbf.engine.TestResultLogger.passed;

public class MainFrameUIDriver {
	public MainFrameUIDriver(ObjectMapFactory objMapFactory){
		final String jacobDllVersionToUse = "jacob-1.18-x86.dll";
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, new File("lib",jacobDllVersionToUse).getAbsolutePath());
		this.objMapFactory = objMapFactory;
	}
	
	public void open(){
		// Not required for MainFrame but added here for consistency with UiDriver.
	}
    public void close(){
    	// Not required for MainFrame but added here for consistency with Uidriver.
    }
    private ActiveXComponent getScreen(){
    	try{
    		if(screen == null){
    			screen = new ActiveXComponent("PCOMM.autECLPS");
    		}
    		return screen;
    	}catch(Exception se){
    		
    		logger.handleError("Error while connecting to screen", se);
    	}
    	return null;
    }
    
    public boolean setScreenConnection(){
    	screen = getScreen();
    	try{
    		if(screen != null){
    		screen.invoke("SetConnectionByName", getSessionName());
    		
    			if(screen.getProperty("APIEnabled") != null){
    			passed("Connect to Screen", "Should connect to Screen", "Successfully connect to Screen");
    			return true;
    			}
    		}else{
    			failed("Connect to Session", "Should connect to Session", "Eror in connecting to Session");
    		}    		
    	}catch(Exception sce){
    		failed("Connect to Screen", "Should connect to Screen", "Eror in connecting to Screen");
    		logger.handleError("Error while connecting to screen", sce);
    	}
    	return false;
    }
    
    public String getText(String ctrlName, int len) {
		screen = getScreen();
		Variant text = null;
		Map getProperty = getControl(getElement(ctrlName));
		int row = Integer.parseInt((String) getProperty.get("row"));
		int col = Integer.parseInt((String) getProperty.get("col"));
		try {
		text = Dispatch.call(screen, "GetText",row, col, len);
		passed("Read text", "Should read  text" , "Successfully Read text from screen: " + text.getString());
		return text.getString();
		}catch(Exception e) {
			failed("Read text", "Should read  text" , "Problem Reading text from position: "+ row +"," + col);
			logger.handleError("Error while reading text at: " + ctrlName, e);
		}

		return null;
	}
    
    public String getScreenText() {
		screen = getScreen();
		Variant text = Dispatch.call(screen, "GetText");
		return text.getString();
	}
    
    public Map getControl(String childName) {
		return objectMap().properties(childName);
	}
    
    public ObjectMap objectMap() {
	    String moduleCode = TestResultLogger.getCurrentModuleCode();
	    return objMapFactory.getObjectMap(moduleCode);
  }
    
    public String getSessionName(){
    	EmulatorStart emulator = new EmulatorStart();
    	return emulator.getSessionName();
    }
    
    public String getElement(String element) {

		component = element.split("\\.")[0];
		return element.split("\\.")[1];

	}
    
    public void setText(String ctrlName, String text) {
		screen = getScreen();
		Map getProperty = getControl(getElement(ctrlName));
		int row = Integer.parseInt((String) getProperty.get("row"));
		int col = Integer.parseInt((String) getProperty.get("col"));
		try {
		Dispatch.call(screen, "SetText", text, row, col);
		
		passed("Write text", "Should write text" , "Successfully written text: "+text+" on screen");
		
		}catch(Exception e) {
			failed("Write text", "Should write text", "Problem Writing text :" +text);
			logger.handleError("Error while writing text: " + text, e);
		}

	}
    
    public void pressSpecialKey(String key) {
		screen = getScreen();
		switch(key){
		
		case "Enter":
			Dispatch.call(screen, "SendKeys", "[enter]");
			break;
		
		case "Tab":
			Dispatch.call(screen, "SendKeys", "[tab]");
			break;
		
		case "F1":
			Dispatch.call(screen, "SendKeys", "[PF1]");
			break;
		
		case "F2":
			Dispatch.call(screen, "SendKeys", "[PF2]");
			break;
			
		case "F3":
			Dispatch.call(screen, "SendKeys", "[PF3]");
			break;
			
		case "F4":
			Dispatch.call(screen, "SendKeys", "[PF4]");
			break;
			
		case "PageDown":
			Dispatch.call(screen, "SendKeys", "[pagedn]");
			break;
			
		case "PageUp":
			Dispatch.call(screen, "SendKeys", "[pageup]");
			break;
		
		default:
			Dispatch.call(screen, "SendKeys", "["+key+"]");
			logger.handleError("Could not perfomrm Press action for the key: " + key);
		}	
		
	}
    
    public void setCursorPos(String ctrlName) {
		screen = getScreen();
		Map getProperty = getControl(getElement(ctrlName));
		int row = Integer.parseInt((String) getProperty.get("row"));
		int col = Integer.parseInt((String) getProperty.get("col"));
		try {
		Dispatch.call(screen, "SetCursorPos",row, col);
		passed("Move cursor", "Should move the cursor to given position" , "Successfully moved cursor");
		}catch(Exception e) {
			failed("Move cursor", "Should move the cursor to given position" , "Could not move cursor");
			logger.handleError("Could not perfomrm set Position action: ", e);
		}

	}
    
    public void clearText() {
		screen = getScreen();
		try {
			Dispatch.call(screen, "SendKeys", "[eraseeof]");
			passed("Clear text", "Should Clear text till end of the field" , "Successfully cleared the text");
			}catch(Exception e) {
				failed("Clear text", "Should Clear text till end of the field" , "Could not clear text");
				logger.handleError("Could not clear text", e);
			}		
	}
    
    public void searchText(String text) {
		screen = getScreen();
		Variant searchResult = null;
		try {
		searchResult = Dispatch.call(screen, "SearchText", text, 1);
		if(searchResult.getBoolean()){
		passed("Search text", "Should Find the  text" , "Successfully found text: " + text +  "on screen");
		}else{
			failed("Search text", "Should Find the  text" , "Could not find text: " + text + " on screen");
		}
		}catch(Exception e) {
			failed("Search text", "Should Find the  text" , "Could not find text: " + text + " on screen");
			logger.handleError("Could not find the text: "+text , e);
		}

		
	}
    
    public File takeScreenshot(String filePath){
    	try{
    		File source = null;
    		Robot robot = new Robot();
    		Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    		source = new File(filePath);
    		BufferedImage image = robot.createScreenCapture(rect);
    		try{
    			ImageIO.write(image, "png", source);
    		}catch(IOException ie){
    			logger.handleError("error in writing screenshot image", filePath, ie);
    		}
    		return source;
    	}catch(Exception e){
    		logger.handleError("take screenshot", e);
    	}
    	return null;
    }
    
    public void wait(int ms) {
		screen = getScreen();
		Dispatch.call(screen, "Wait", ms);		
	}
    
    private ObjectMapFactory objMapFactory;
    private String component;
    private ActiveXComponent screen;
    private LogUtils logger = new LogUtils(this);
}
