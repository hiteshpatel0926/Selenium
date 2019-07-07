package cbf.eggplant;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class EggDriver extends RpcConnector {

	public Double imageSearchTimeout = null;
	public Map<String, Object> textSearchOptions = null;
	public Double pinchDuration = null;
	public Integer pinchDistance = null;

	private SenseTalkFormatter formatter = new SenseTalkFormatter();

	// region Connection
	// ********************************************************************************

	public void connect(String serverID) throws EggDriveException {
		connect(serverID, null, null, null, null, null, null, null, null, null);
	}

	public void connect(String serverID, Integer portNum)
			throws EggDriveException {
		connect(serverID, portNum, null, null, null, null, null, null, null,
				null);
	}

	public void connect(String serverID, Integer portNum, String type)
			throws EggDriveException {
		connect(serverID, portNum, type, null, null, null, null, null, null,
				null);
	}

	public void connect(String serverID, Integer portNum, String type,
			String username, String password) throws EggDriveException {
		connect(serverID, portNum, type, username, password, null, null, null,
				null, null);
	}

	public void connect(String serverID, Integer portNum, String type,
			String sshHost, String sshUser, String sshPassword)
			throws EggDriveException {
		connect(serverID, portNum, type, null, null, sshHost, sshUser,
				sshPassword, null, null);
	}

	public void connect(String serverID, Integer portNum, String type,
			String username, String password, String sshHost, String sshUser,
			String sshPassword) throws EggDriveException {
		connect(serverID, portNum, type, username, password, sshHost, sshUser,
				sshPassword, null, null);
	}

	public void connect(String serverID, Integer portNum, String type,
			String username, String password, String sshHost, String sshUser,
			String sshPassword, String visible, String colorDepth)
			throws EggDriveException {
		executeString(formatter.reset()
				.addQuotedPListParameter("ServerID", serverID)
				.addPListParameter("PortNum", portNum)
				.addPListParameter("Type", type)
				.addQuotedPListParameter("Username", username)
				.addQuotedPListParameter("Password", password)
				.addQuotedPListParameter("sshHost", sshHost)
				.addQuotedPListParameter("sshUser", sshUser)
				.addQuotedPListParameter("sshPassword", sshPassword)
				.addPListParameter("Visible", visible)
				.addPListParameter("ColorDepth", colorDepth)
				.asCommand("Connect"));
	}

	public static Object[] convertToObjectArray(Object array) {
		Class ofArray = array.getClass().getComponentType();
		if (ofArray.isPrimitive()) {
			List ar = new ArrayList();
			int length = Array.getLength(array);
			for (int i = 0; i < length; i++) {
				ar.add(Array.get(array, i));
			}
			return ar.toArray();
		} else {
			return (Object[]) array;
		}
	}

	public void disconnect() throws EggDriveException {
		executeString(formatter.reset().asCommand("Disconnect"));
	}

	public Map<String, Object> connectionInfo() throws EggDriveException {
		return connectionInfo(null);
	}

	public Map<String, Object> connectionInfo(String connectionName)
			throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(connectionName)
				.asFunction("ConnectionInfo"));
		return getLastDriveResult().getReturnValueAsPList();
	}

	public Dimension remoteScreenSize() throws EggDriveException {
		executeString(formatter.reset().asFunction("RemoteScreenSize"));
		return getLastDriveResult().getReturnValueAsDimension();
	}

	// endregion

	// region Logs
	// ********************************************************************************

	public void log(String log) throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(log)
				.asCommand("Log"));
	}

	public void logExpression(String log) throws EggDriveException {
		executeString(formatter.reset().addParameter(log).asCommand("Log"));
	}

	public void logError(String error) throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(error)
				.asCommand("LogError"));
	}

	public void logErrorExpression(String error) throws EggDriveException {
		executeString(formatter.reset().addParameter(error)
				.asCommand("LogError"));
	}

	// endregion

	// region Pointer Events
	// ********************************************************************************

	public void click(Point p) throws EggDriveException {	
		commandAtPoint("Click", p);		
	}

	public void click(String image) throws EggDriveException {
		commandAtImage("Click", image, imageSearchTimeout);
	}
	
	public void clickImage(String image) throws EggDriveException {
		executeString(formatter.reset().addParameter(image)			
				.asCommand("Click"));
	}

	public void clickText(String text) throws EggDriveException {
		commandAtText("Click", text, textSearchOptions);
	}

	public void doubleClick(Point p) throws EggDriveException {
		commandAtPoint("DoubleClick", p);

	}

	public void doubleClick(String image) throws EggDriveException {
		commandAtImage("DoubleClick", image, imageSearchTimeout);
	}

	public void doubleClickText(String text) throws EggDriveException{		
			commandAtText("DoubleClick", text, textSearchOptions);		
	}

	public void rightClick(Point p) throws EggDriveException {
		commandAtPoint("RightClick", p);
	}

	public void rightClick(String image) throws EggDriveException {
		commandAtImage("RightClick", image, imageSearchTimeout);
	}

	public void rightClickText(String text) throws EggDriveException {
		commandAtText("RightClick", text, textSearchOptions);
	}

	public void moveTo(Point p) throws EggDriveException {
		commandAtPoint("MoveTo", p);
	}

	public void moveTo(String image) throws EggDriveException {
		commandAtImage("MoveTo", image, imageSearchTimeout);
	}

	public void moveToText(String text) throws EggDriveException {
		commandAtText("MoveTo", text, textSearchOptions);
	}

	// endregion

	// region Mobile Gestures
	// ********************************************************************************

	public void tap(Point p) throws EggDriveException {
		commandAtPoint("Tap", p);
	}

	public void tap(String image) throws EggDriveException {
		commandAtImage("Tap", image, imageSearchTimeout);
	}

	public void tapText(String text) throws EggDriveException {
		commandAtText("Tap", text, textSearchOptions);
	}

	public void swipeLeft() throws EggDriveException {
		executeString(formatter.reset().asCommand("SwipeLeft"));
	}

	public void swipeLeft(Point p) throws EggDriveException {
		commandAtPoint("SwipeLeft", p);
	}

	public void swipeLeft(String image) throws EggDriveException {
		commandAtImage("SwipeLeft", image, null);
	}

	public void swipeRight() throws EggDriveException {
		executeString(formatter.reset().asCommand("SwipeRight"));
	}

	public void swipeRight(Point p) throws EggDriveException {
		commandAtPoint("SwipeRight", p);
	}

	public void swipeRight(String image) throws EggDriveException {
		commandAtImage("SwipeRight", image, null);
	}

	public void swipeDown() throws EggDriveException {
		executeString(formatter.reset().asCommand("SwipeDown"));
	}

	public void swipeDown(Point p) throws EggDriveException {
		commandAtPoint("SwipeDown", p);
	}

	public void swipeDown(String image) throws EggDriveException {
		commandAtImage("SwipeDown", image, null);
	}

	public void swipeUp() throws EggDriveException {
		executeString(formatter.reset().asCommand("SwipeUp"));
	}

	public void swipeUp(Point p) throws EggDriveException {
		commandAtPoint("SwipeUp", p);
	}

	public void swipeUp(String image) throws EggDriveException {
		commandAtImage("SwipeUp", image, null);
	}

	public void pinchOut() throws EggDriveException {
		pinch(false, false, null, null, null);
	}

	public void pinchOut(Point atPoint) throws EggDriveException {
		pinch(false, false, atPoint, null, null);
	}

	public void pinchOut(Point atPoint, Point toPoint) throws EggDriveException {
		pinch(false, false, atPoint, null, toPoint);
	}

	public void pinchOut(String atImage) throws EggDriveException {
		pinch(false, true, atImage, null, null);
	}

	public void pinchOut(String atImage, String toImage)
			throws EggDriveException {
		pinch(false, true, atImage, null, toImage);
	}

	public void pinchIn() throws EggDriveException {
		pinch(true, false, null, null, null);
	}

	public void pinchIn(Point atPoint) throws EggDriveException {
		pinch(true, false, atPoint, null, null);
	}

	public void pinchIn(Point atPoint, Point fromPoint)
			throws EggDriveException {
		pinch(true, false, atPoint, fromPoint, null);
	}

	public void pinchIn(String atImage) throws EggDriveException {
		pinch(true, true, atImage, null, null);
	}

	public void pinchIn(String atImage, String fromImage)
			throws EggDriveException {
		pinch(true, true, atImage, fromImage, null);
	}

	// endregion

	// region Mobile Device Control
	// ********************************************************************************

	public void launchApp(String appName) throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(appName)
				.asCommand("LaunchApp"));
	}

	public void launchApp(String deviceName, String appName)
			throws EggDriveException {
		executeString(formatter.reset()
				.addQuotedParameter(deviceName + " : " + appName)
				.asCommand("LaunchApp"));
	}

	// endregion

	// region Image Searching
	// ********************************************************************************

	public List<Map<String, Object>> imageInfo(String imageName)
			throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(imageName)
				.asFunction("ImageInfo"));
		return getLastDriveResult().getReturnValueAsPLists();
	}

	public void waitFor(int i, String imageName) throws EggDriveException {
		executeString(formatter.reset().addParameter(i).addParameter(imageName)
				.asCommand("WaitFor"));
	}

	public void wait(int i) throws EggDriveException {
		executeString(formatter.reset().addParameter(i).asCommand("Wait"));
	}

	public void waitForText(String imageName, Object text)
			throws EggDriveException {
		executeString(formatter.reset().addParameter(imageSearchTimeout)
				.addQuotedParameter(text).asCommand("WaitFor_Text"));
	}

	public Boolean imageFound(String imageName) throws EggDriveException {
		executeString(formatter.reset().addParameter(imageSearchTimeout)
				.addQuotedParameter(imageName).asFunction("ImageFound"));
		return (Boolean) getLastDriveResult().getReturnValue();
	}

	public List<Point> everyImageLocation(String imageName)
			throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(imageName)
				.asFunction("EveryImageLocation"));

		List<Point> iloc = getLastDriveResult().getReturnValueAsPoints();

		Double x, y;
		for (Point point : iloc) {
			x = point.getX();
			y = point.getY();

		}

		return getLastDriveResult().getReturnValueAsPoints();
	}

	public List<Point> hotSpot(String imageName) throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(imageName)
				.asFunction("HotSpot"));
		return getLastDriveResult().getReturnValueAsPoints();
	}

	public Rectangle imageRectangle(String imageName) throws EggDriveException {		

		Rectangle r=new Rectangle();
		Double[] db=new Double[4];
		int i=0;
		executeString(formatter.reset().addParameter(imageName)
				.asFunction("ImageRectangle"));
		Object irect = (Object) getLastDriveResult().getReturnValue();

		Object[] objects = (Object[]) irect;
		for (Object obj : objects) {
			db[i]=Double.parseDouble(obj.toString());
			i++;
		}
		r.x= db[0].intValue();
		r.y=db[1].intValue();
		r.width= db[2].intValue();
		r.height= db[3].intValue();
		
		return r;	
		
	}

	public List<Point> imageSize(String imageName) throws EggDriveException {
		executeString(formatter.reset().addParameter(imageName)
				.asFunction("imageSize"));
		List<Point> isize = getLastDriveResult().getReturnValueAsPoints();

		Double x, y;
		for (Point point : isize) {
			x = point.getX();
			y = point.getY();

		}

		return getLastDriveResult().getReturnValueAsPoints();
	}

	// ********************************************************************************

	public void typeText(String text) throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(text)
				.asCommand("TypeText"));
	}

	public void typeExpression(Object expression) throws EggDriveException {
		executeString(formatter.reset().addParameter(expression)
				.asCommand("TypeText"));
	}

	public String readText(Point p) throws EggDriveException {
		executeString(formatter.reset().addParameter(p)
				.addPListParameters(textSearchOptions).asFunction("ReadText"));
		return (String) getLastDriveResult().getReturnValue();
	}

	public String readText(Rectangle r) throws EggDriveException {
		executeString(formatter.reset().addParameter(r)
				.addPListParameters(textSearchOptions).asFunction("ReadText"));
		return (String) getLastDriveResult().getReturnValue();
	}

	public String readText(String imageName) throws EggDriveException {
		executeString(formatter.reset().addParameter(imageName)
				.addPListParameters(textSearchOptions).asFunction("ReadText"));
		return (String) getLastDriveResult().getReturnValue();
	}

	public String readText(String imageName1, String imageName2)
			throws EggDriveException {
		executeString(formatter.reset().addParameter(imageName1)
				.addParameter(imageName2).addPListParameters(textSearchOptions)
				.asFunction("ReadText"));
		return (String) getLastDriveResult().getReturnValue();
	}
	

	public void CaptureScreen(String imageName1) throws EggDriveException {
		executeString(formatter.reset().addParameter(imageName1)
				.asCommand("CaptureScreen"));
	}

	public void CaptureScreen(String imageName1, String increment)
			throws EggDriveException {
		executeString(formatter.reset().addParameter(imageName1)
				.addParameter(increment).asCommand("CaptureScreen"));
	}

	public void CaptureScreen(String imageName1, String increment,
			String rectangle) throws EggDriveException {
		executeString(formatter.reset().addParameter(imageName1)
				.addParameter(increment).addParameter(rectangle)
				.asCommand("CaptureScreen"));
	}

	public void CaptureScreen(String imageName, String rectangle,
			String increment, String imageInfo) throws EggDriveException {
		executeString(formatter.reset().addParameter(imageName)
				.addParameter(rectangle).addParameter(increment)
				.addParameter(imageInfo).asCommand("CaptureScreen"));
	}

	public String remoteClipboard() throws EggDriveException {
		return remoteClipboard(null);
	}

	public String remoteClipboard(Double timeout) throws EggDriveException {
		executeString(formatter.reset().addParameter(timeout)
				.asFunction("RemoteClipboard"));
		return (String) getLastDriveResult().getReturnValue();
	}

	// endregion

	// region Private Functions
	// ********************************************************************************
	private void commandAtPoint(String command, Point p)
			throws EggDriveException {
		executeString(formatter.reset().addParameter(p).asCommand(command));
	}

	private void commandAtImage(String command, String image,
			Double searchTimeout) throws EggDriveException {
		executeString(formatter.reset().addQuotedPListParameter("Image", image)
				.addQuotedPListParameter("WaitFor", searchTimeout)
				.asCommand(command));
	}

	private void commandAtText(String command, String text,
			Map<String, Object> options) throws EggDriveException {
		executeString(formatter.reset().addQuotedPListParameter("Text", text)
				.addPListParameters(options).asCommand(command));
	}

	private void pinch(boolean in, boolean images, Object at, Object from,
			Object to) throws EggDriveException {
		if (images)
			executeString(formatter.reset().addQuotedPListParameter("At", at)
					.addQuotedPListParameter("From", from)
					.addQuotedPListParameter("To", to)
					.addPListParameter("Distance", pinchDistance)
					.addPListParameter("Duration", pinchDuration)
					.asCommand(in ? "PinchIn" : "PinchOut"));
		else
			executeString(formatter.reset().addPListParameter("At", at)
					.addPListParameter("From", from)
					.addPListParameter("To", to)
					.addPListParameter("Distance", pinchDistance)
					.addPListParameter("Duration", pinchDuration)
					.asCommand(in ? "PinchIn" : "PinchOut"));
	}

	// endregion

	// Click MultilineText Functions

	public void setValue(String variableName, String value)
			throws EggDriveException {

		executeString(formatter.reset().asCommand(
				"set " + variableName + " to " + value + ""));
	}

	public void putValue(String variableName, String value)
			throws EggDriveException {

		executeString(formatter.reset().asCommand(
				"put " + variableName + " into " + value + ""));
	}

	public List<Point> ImageHotSpot(String imageName) throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(imageName)
				.asFunction("ImageHotSpot"));
		return getLastDriveResult().getReturnValueAsPoints();
	}

	public List<Point> center(Rectangle r) throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(r)
				.asFunction("center"));
		return getLastDriveResult().getReturnValueAsPoints();
	}

	public Object searchRectangle(Rectangle r) throws EggDriveException {
		executeString(formatter.reset().addQuotedParameter(r)
				.asFunction("searchRectangle"));
		return getLastDriveResult().getReturnValue();
	}

	public Object HighlightRectangle(Rectangle r, String Duration, String Color)
			throws EggDriveException {
		executeString(formatter.reset().addParameter(r).addParameter(Duration)
				.addParameter(Color).asFunction("HighlightRectangle"));
		return getLastDriveResult().getReturnValue();
	}

	public List<Point> moveto(String imageName, String SearchType, double Scale)
			throws EggDriveException {
		executeString(formatter.reset().addParameter(imageName)
				.addParameter(SearchType).addParameter("1")
				.asFunction("moveto"));
		return getLastDriveResult().getReturnValueAsPoints();

	}

	public Rectangle imageRectangle(String text, String rectangle,
			String Contrast,String color) throws EggDriveException {
		Rectangle r=new Rectangle();
		Double[] db=new Double[4];
		int i=0;
		executeString(formatter.reset().addParameter(text)
				.addParameter(rectangle).addParameter(Contrast).addParameter(color)
				.asFunction("ImageRectangle"));
		Object irect = (Object) getLastDriveResult().getReturnValue();

		Object[] objects = (Object[]) irect;
		for (Object obj : objects) {
			db[i]=Double.parseDouble(obj.toString());
			i++;
		}
		r.x= db[0].intValue();
		r.y=db[1].intValue();
		r.width= db[2].intValue();
		r.height= db[3].intValue();
		
		return r;	

	}

	public String readText(Rectangle r, String ValidWords)
			throws EggDriveException {
		executeString(formatter.reset().addParameter(r)
				.addParameter(ValidWords).addPListParameters(textSearchOptions)
				.asFunction("ReadText"));
		return (String) getLastDriveResult().getReturnValue();
	}

}
