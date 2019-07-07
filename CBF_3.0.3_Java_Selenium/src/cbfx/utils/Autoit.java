/******************************************************************************
$Id : Autoit.java 12/23/2016 4:09:05 PM
Copyright © 2016 Capgemini Group of companies. All rights reserved
(Subject to Limited Distribution and Restricted Disclosure Only.)
THIS SOURCE FILE MAY CONTAIN INFORMATION WHICH IS THE PROPRIETARY
INFORMATION OF CAPGEMINI GROUP OF COMPANIES AND IS INTENDED FOR USE
ONLY BY THE ENTITY WHO IS ENTITLED TO AND MAY CONTAIN
INFORMATION THAT IS PRIVILEGED, CONFIDENTIAL, OR EXEMPT FROM
DISCLOSURE UNDER APPLICABLE LAW.
YOUR ACCESS TO THIS SOURCE FILE IS GOVERNED BY THE TERMS AND
CONDITIONS OF AN AGREEMENT BETWEEN YOU AND CAPGEMINI GROUP OF COMPANIES.
The USE, DISCLOSURE REPRODUCTION OR TRANSFER OF THIS PROGRAM IS
RESTRICTED AS SET FORTH THEREIN.
******************************************************************************/

package cbfx.utils;

import java.io.File;

import com.jacob.com.LibraryLoader;

import autoitx4java.AutoItX;

public class Autoit {
	private Autoit(){}
	private static AutoItX getAutoit(){
		if(autoItDriver == null){
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, 
					new File("lib/jacob-1.15-M4/jacob-1.15-M4", "jacob-1.15-M4-x86.dll").getAbsolutePath());
			
			autoItDriver = new AutoItX();
		}
		return autoItDriver;
	}
	
	public static void fileUpload(String winName, String imgPath){
		autoItDriver = getAutoit();		
		autoItDriver.winWaitActive(winName);
		autoItDriver.ControlSetText(winName, "", "[CLASS:Edit; INSTANCE:1]", imgPath);
		autoItDriver.controlClick(winName, "", "[CLASS:Button; INSTANCE:1]");
		
	}	
	
	private static AutoItX autoItDriver = null;
}
