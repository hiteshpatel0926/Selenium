package Testing.QA;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        int A=10;
        String B ="HITESH";
        float C = (float) 1.2;
        double D = 9.9999;
        boolean E = true;
        
        System.out.println(A+B+C+D+E);
        
        
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        System.out.println("Chrome invoked Successfully.!");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
