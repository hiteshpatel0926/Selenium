package cbfx.api;

import cbf.model.BaseModuleDriver;

public class APIModuleDriver extends BaseModuleDriver {
	protected RestAPIDriver raDriver;
	public APIModuleDriver(RestAPIDriver rapiDriver){
		raDriver = rapiDriver;		
	}
}
