package com.igate.actions;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TestMainFunction {
	static EggDriver eggDriver=new EggDriver();
	
	public static void main(String[] args) throws InterruptedException{
	
		
		try {
			eggDriver.disconnect();
//			ed.connect("10.220.24.24", 5900, null, null, null);
			TestMainFunction.connect("10.220.31.103","5900" , "","C:\\Users\\pathakur\\Desktop\\Demo1.suite");
//			ed.connect("10.220.24.24", 5900, "VNC Automatic");
//			eggDriver.doubleClick("niksComputer");
			//System.out.println(eggDriver.readText("niksComputer"));
			//System.out.println(eggDriver.readText("RecycleBin"));
//			System.out.println(eggDriver.readText("BigMac","Breakfast_Btn"));
			/*eggDriver.CaptureScreen("Name: \"C:\\Users\\pathakur\\Desktop\\Images\"");
			eggDriver.waitFor(5, "media");
			
			
				eggDriver.click("media");
				eggDriver.wait(5);
				try{
			
			eggDriver.CaptureScreen("Name: \"C:\\Users\\pathakur\\Desktop\\Images_N\"");
				}catch (Exception e) {
			System.out.println("exec");
				}*/
			//eggDriver.everyImageLocation("media");
		 
			
			
			
			
			/*DriveResult driveResult=new DriveResult("",0.0,null,null);
			
			
			Object obj=new Object();
			obj=(Object) eggDriver.imageRectangle("Img1");
			System.out.println("rct:"+obj);*/
			
			//eggDriver.readText("niksComputer","RecycleBin");
			
			eggDriver.disconnect();
		} catch (EggDriveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void connect(String url, String port, String password,String suitePath) {
		eggDriver.verboseLogging = true;

		eggDriver.connectDrive("127.0.0.1", 5400);
		//eggDriver.connectDrive("10.150.1.235", 5400);

		eggDriver.overridePreviousSession = true;

		eggDriver.startDriveSession(suitePath);

		try {
			eggDriver.connect(url, Integer.valueOf(port),null,null,password);
		} catch (EggDriveException e) {
			e.printStackTrace();
		}

	}

	
	

}
