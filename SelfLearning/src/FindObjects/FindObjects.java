package FindObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindObjects {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get("https://www.toolsqa.com/automation-practice-form/");
	    System.out.println("Chrome invoked Successfully.!");
	    System.out.println(driver.getTitle());
	    driver.findElement(By.linkText("Link Test")).click();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.navigate().back();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.findElement(By.partialLinkText("Partial")).click();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    driver.findElement(By.name("firstname")).sendKeys("HITESH");
	    driver.findElement(By.name("lastname")).sendKeys("Patel");
	    
	    
List<WebElement> rdBtn_Sex = driver.findElements(By.name("sex"));
	    
	    boolean bValue = false;
	    bValue = rdBtn_Sex.get(0).isSelected();
	    
	    if(bValue == true){
	    	 // This will select Second radio button, if the first radio button is selected by default
	    	 rdBtn_Sex.get(1).click();
	    	 }else{
	    	 // If the first radio button is not selected by default, the first will be selected
	    	 rdBtn_Sex.get(0).click();
	    	 }
	    
	    //Step 4: Select the Last radio button for category 'Years of Exp' (Use Id attribute to select Radio button)
	    WebElement rdBtn_Exp = driver.findElement(By.id("exp-6"));
	    rdBtn_Exp.click();
	    
	    List<WebElement> chkBx_Profession = driver.findElements(By.name("profession"));
	    
	    // This will tell you the number of Check Boxes are present
	    int iSize = chkBx_Profession.size();
	    
	    // Start the loop from first Check Box to last Check Boxe
	    for(int i=0; i < iSize ; i++ ){
	    // Store the Check Box name to the string variable, using 'Value' attribute
	    String sValue = chkBx_Profession.get(i).getAttribute("value");
	    
	    // Select the Check Box it the value of the Check Box is same what you are looking for
	    if (sValue.equalsIgnoreCase("Automation Tester")){
	    chkBx_Profession.get(i).click();
	    // This will take the execution out of for loop
	    break;
	    }
	    }
	    // Step 6: Check the Check Box 'Selenium IDE' for category 'Automation Tool' (Use cssSelector)
	    WebElement oCheckBox = driver.findElement(By.cssSelector("input[value='Selenium IDE']"));
	    oCheckBox.click();
	    
	    // Kill the browser
	    //driver.quit();
	      driver.close();
		
		
	}

}
