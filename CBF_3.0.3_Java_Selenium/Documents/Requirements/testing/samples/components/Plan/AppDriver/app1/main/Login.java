package app1.main;

import cbfx.ui.selenium.WebUIDriver;
import cbfx.ui.utils.DataRow;

public class Login {
  public static void perform(WebUIDriver driver, DataRow input) {
    driver.setValues("login", input);
    driver.click("Login");
  }
}
