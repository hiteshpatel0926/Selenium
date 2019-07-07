package cbfx.ui.mainframe;

import cbf.model.BaseModuleDriver;

public class MainFrameModuleDriver extends BaseModuleDriver {
	protected MainFrameUIDriver mfuiDriver;
	protected EmulatorStart mfLogin;
	public MainFrameModuleDriver(MainFrameUIDriver mfUiDriver){
		mfuiDriver = mfUiDriver;
		mfLogin = new EmulatorStart();
	}
}
