package cbfx.ui.mainframe;

import cbf.model.AppDriver;
import cbf.model.ScreenDumper;
import java.util.Map;

import cbf.component.Component;
import cbf.component.ComponentLibrary;
import cbf.component.ModuleLibrary;
import cbf.harness.Harness;
import cbf.model.ModuleDriver;
import cbf.utils.DataRow;
import cbf.utils.LogUtils;
import cbf.utils.StringUtils;
import cbfx.ui.objectmap.ObjectMapFactory;

public class MainFrameAppDriver implements AppDriver, ScreenDumper {

	public MainFrameAppDriver(Map params){
		this.params = params;
		mfDriver = new MainFrameUIDriver(getObjectMap(params));
		this.componentLibrary = loadComponentLibrary();
		this.moduleLibrary = loadModuleLibrary();
	}
	
	public void open(){
		// Not required for MainFrame but added here for consistency.
	}
	 public void close(){
		// Not required for MainFrame but added here for consistency.
	 }
	 
	 public void recover(){
		 close();
		 open();
	 }
	 
	 public void dumpScreen(String filePath){
		 logger.trace("DumpScren:" + filePath);
		 try{
			 mfDriver.takeScreenshot(filePath);
		 }catch(Exception e){
			 logger.handleError("exception caught while creating screen dump", filePath, e);
		 }
	 }
	 
		public void perform(String moduleCode, String componentCode, DataRow input, DataRow output){
		      if (componentLibrary != null) {
		        Component c = componentLibrary.find(moduleCode, componentCode);
		        if (c != null) { // an overridding component is defined
		          try {
					      c.perform(mfDriver, input, output, null);
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
		      return new ModuleLibrary(cparams,mfDriver );
		    }
		    
	 public String toString(){
		 return StringUtils.mapString(this, params);
	 }
	 
	 private Map params;
	 protected final MainFrameUIDriver mfDriver;
	 private ComponentLibrary componentLibrary;
	 private ModuleLibrary moduleLibrary;
	 protected static LogUtils logger = new LogUtils(MainFrameAppDriver.class);
	 
}
