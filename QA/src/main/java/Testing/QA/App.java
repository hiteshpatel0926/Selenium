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
        double D = 6.666;
        boolean E = true;
        
        System.out.println(A+B+C+D+E);
        
        
        int[] array1 = new int[5];
        
        int[] array2 = {1,2,3,4,5};
        
        for(int i =0;i<array2.length;i++)
        {
        	System.out.println(array2[i]);
        }
        
        
        
        
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        System.out.println("Chrome invoked Successfully.!");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
