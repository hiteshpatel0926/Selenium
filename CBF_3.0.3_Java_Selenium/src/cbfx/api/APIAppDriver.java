package cbfx.api;

import cbf.model.AppDriver;
import cbf.model.ScreenDumper;
import java.util.Map;
import cbf.component.Component;
import cbf.component.ComponentLibrary;
import cbf.component.ModuleLibrary;
import cbf.model.ModuleDriver;
import cbf.utils.DataRow;
import cbf.utils.LogUtils;
import cbf.utils.StringUtils;


public class APIAppDriver implements AppDriver, ScreenDumper {

	public APIAppDriver(Map params){
		this.params = params;
		raDriver = new RestAPIDriver();
		this.componentLibrary = loadComponentLibrary();
		this.moduleLibrary = loadModuleLibrary();
	}
	
	public void open(){
		// Not required for API but added here for consistency.
	}
	 public void close(){
		// Not required for API but added here for consistency.
	 }
	 
	 public void recover(){
		 close();
		 open();
	 }
	 
	 public void dumpScreen(String filePath){
		 logger.trace("DumpScren:" + filePath);
		///Screenshots not required/available for API 

	 }
	 
		public void perform(String moduleCode, String componentCode, DataRow input, DataRow output){
		      if (componentLibrary != null) {
		        Component c = componentLibrary.find(moduleCode, componentCode);
		        if (c != null) { // an overridding component is defined
		          try {
					      c.perform(raDriver, input, output, null);
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
		      return new ModuleLibrary(cparams,raDriver );
		    }

	 public String toString(){
		 return StringUtils.mapString(this, params);
	 }
	 
	 private Map params;
	 protected final RestAPIDriver raDriver;
	 private ComponentLibrary componentLibrary;
	 private ModuleLibrary moduleLibrary;
	 protected static LogUtils logger = new LogUtils(APIAppDriver.class);
	 
}
