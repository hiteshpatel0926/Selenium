/******************************************************************************
$Id : WebAppDriver.java 12/23/2016 4:09:05 PM
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
package cbfx.ui.web;


import java.util.HashMap;
import java.util.Map;

import cbf.component.Component;
import cbf.component.ComponentLibrary;
import cbf.component.ModuleLibrary;
import cbf.harness.Harness;
import cbf.model.ModuleDriver;
import cbf.utils.DataRow;
import cbf.utils.StringUtils;
import cbfx.ui.browser.Browser;
import cbfx.ui.objectmap.ObjectMapFactory;


public class WebAppDriver extends BaseWebAppDriver{
	
	public WebAppDriver(Map params) {
		super(getObjectMap(params), getBrowser(params));
	    this.params = params;
	    this.kwDriver = new KeywordDriver(webUIDriver);
	    this.componentLibrary = loadComponentLibrary();
	    this.moduleLibrary = loadModuleLibrary();
	    setupBrowser(params);
	}
	
	private void setupBrowser(Map params) {
		if(((Map)params.get("args")).containsKey("browserName")){
	    	browserName = ((Map)params.get("args")).get("browserName").toString();
		}else{
			browserName = ((Map)params.get("args")).get("browser").toString();
		}
	}


	// Used for reporting purposes
	public String getBrowserName(){
		return browserName;
	}
	
	public void perform(String moduleCode, String componentCode, DataRow input, DataRow output){
      if (componentLibrary != null) {
        Component c = componentLibrary.find(moduleCode, componentCode);
        if (c != null) { // an overridding component is defined
          try {
			      c.perform(kwDriver, input, output, null);
		      } catch (Exception e) {
				 
			      logger.handleError("component perform", e, moduleCode, componentCode, input);
		      }
          return;
        }
      }

      if (moduleLibrary != null) {
        ModuleDriver md = moduleLibrary.find(moduleCode);
        if (md != null) {
          try {
            md.perform(componentCode, input, output);
          } catch (Exception e) {
			      logger.handleError("module driver perform", e, md, moduleCode, componentCode, input);
          }
          return;
        }
      }

      logger.handleError("No matching component", moduleCode, componentCode);
    }
	
	
    private ComponentLibrary loadComponentLibrary() {
      Map cparams = (Map)params.get("components");
      if (cparams == null)
        return null;
      return new ComponentLibrary(cparams);
    }

    private ModuleLibrary loadModuleLibrary() {
      Map cparams = (Map)params.get("modules");
      if (cparams == null)
        return null;
      return new ModuleLibrary(cparams,kwDriver );
    }

   
    private static Browser getBrowser(Map params) {
    	Map browserArgs = getBrowserConfig(params);;
		try {
			 return (Browser)Harness.pluginManager().getPlugin(browserArgs);
	    } catch(Exception e) {
	    	logger.handleError("Loading browser", browserArgs, e);
	    }
		  return null;
    }

    private static ObjectMapFactory getObjectMap(Map params) {
	    ObjectMapFactory objMap = null;
	    try {
	      objMap = (ObjectMapFactory)Harness.getPlugin("ObjectMap");
	    } catch(ClassCastException ce) {
			
	      logger.handleError("ObjectMap configured is not an object map",objMap,ce);
	    }
	    if (objMap == null)
	    	logger.handleError("ObjectMap is not configured");
	    
	    return objMap;
    }

	  private static Map getBrowserConfig(Map params) {
		Object args = params.get("args");
		if (args == null) { // get default from CONFIG
		  args = Harness.configuration().get("Browser");
		  logger.trace("Browser from global config", args, params);
		}
		if (args == null){
		  logger.handleError("Browser not configured");
		}
		
		else{
		
		try {
			Map argsMap = (Map)args;
			Map map = (Map) params.get("UIDrivers");
			
			map = (Map<String, Object>) map.get("value");
			argsMap.put("plugin", map.get("plugin"));
			
			if(map.get("plugin").toString().equalsIgnoreCase("REMOTE")){
				Map<String, Object> paramMap = new HashMap<>();
				paramMap = (Map<String, Object>) map.get("parameters");
				paramMap.put("browserName", argsMap.get("browserName").toString());
				paramMap.put("HostURL", Harness.configuration().get("HostURL"));
				argsMap.put("parameters",paramMap);
				logger.trace("browserconfig ", paramMap.get("browserName"));
			}
			argsMap.put("parameters", map.get("parameters"));
			return argsMap;
		} catch(ClassCastException ce) {
		  logger.handleError("Browser configuration error", args, ce);
		}
		return null;
	  }
		return params;
	  }
	  public String toString() {
	    return StringUtils.mapString(this, params);
	  }

// TODO: get browser name from webUIDriver.browser/webDriver
	  private Map params;
    private KeywordDriver kwDriver;
	  private ComponentLibrary componentLibrary;
	  private ModuleLibrary moduleLibrary;
	  private String browserName;
}
