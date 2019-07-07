package app1.main;

import cbfx.ui.selenium.WebUIDriver;
import cbfx.ui.utils.DataRow;

public class Launch {
  public static void perform(WebUIDriver driver, DataRow input) {
    driver.launchApplication(input.get("URL"));
  }
}
