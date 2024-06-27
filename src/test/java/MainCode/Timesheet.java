package MainCode;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Timesheet {
	
	public static void main(String args[]) throws InterruptedException
	{
		WebDriver driver=new EdgeDriver();
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		try {
			
            driver.get("https://be.cognizant.com");
            
            driver.manage().window().maximize();

           // Wait for the "OneCognizant" element to be visible
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           
           //user information
           
           Actions ac=new Actions(driver);
           
//           WebElement profile=driver.findElement(By.xpath("//div[@id='O365_UniversalMeContainer']"));
//           
//           ac.moveToElement(profile);
//           
//           ac.click().perform();
//           Thread.sleep(2000);
//           ac.doubleClick().perform();
//           Thread.sleep(3000);
//       
//          String userName=driver.findElement(By.id("mectrl_currentAccount_primary")).getText();
//          
//          System.out.println("User Name: "+userName);
//          
//          String mailId=driver.findElement(By.id("mectrl_currentAccount_secondary")).getText();
//          
//          System.out.println("Email_Id: "+mailId);
//          Thread.sleep(3000);
           //Scroll the page down 
//           WebElement scroll=wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//strong[text()='Around Cognizant']"))));
//           js.executeScript("arguments[0].scrollIntoView();",scroll);
           
          Actions action=new Actions(driver);
  		WebElement hover=driver.findElement(By.xpath("//*[@id=\"spPageCanvasContent\"]/div/div/div/div/div/div/div[3]/div/div/div"));
  		action.moveToElement(hover).perform();
           Thread.sleep(5000);
           
           
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'OneCognizant')]"))).click();
           
            Set<String> windows = driver.getWindowHandles();
    		List<String> windowsList = new ArrayList<String>(windows);
    	
    		
    		driver.switchTo().window(windowsList.get(1));
    		//driver.findElement(By.className("searchTopBar")).click();
    		
            //WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("oneCSearchTop")));
            driver.manage().window().minimize();
            WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='searchBasedTopBar']//li")));
            ele.click();
            Thread.sleep(3000);
            
          //input[@id='oneCSearchTop']
            WebElement sr=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='oneCSearchTop']")));
            //sr.sendKeys("Timesheet");
            sr.sendKeys("Timesheet",Keys.ENTER);
            Thread.sleep(2000);
            driver.manage().window().maximize();
            
            WebElement tSheet=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='quickActionsResultText' and text()='View Timesheet']")));
            tSheet.click();
            
            Thread.sleep(2000);
            Set<String> windows1 = driver.getWindowHandles();
    		List<String> windowsList2 = new ArrayList<String>(windows1);
    		Thread.sleep(2000);
    		driver.switchTo().window(windowsList2.get(2));
    		
    		List<WebElement> dates=driver.findElements(By.xpath("//a[contains(@id,'CTS_TS_LAND_PER_DESCR')]"));
    		Thread.sleep(2000);
    		for(int i=1;i<=3;i++)
    		{
    			WebElement date=dates.get(i);
    			
    			String dt=date.getText();
    			System.out.println("Date"+i+" :"+dt);
    		}
    		
    		
    		
        } catch (Exception e) {
            e.printStackTrace();
        } 
		
	}

}
