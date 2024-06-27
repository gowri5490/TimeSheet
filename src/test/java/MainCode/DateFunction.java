package MainCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DateFunction {
    public static void main(String[] args) throws InterruptedException, ParseException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://compass.esa.cognizant.com/psc/ESA89PRD/EMPLOYEE/ERP/c/ADMINISTER_EXPENSE_FUNCTIONS.CTS_TS_LAND_COMP.GBL?Action=A&local_date=2024-05-28");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(3000);

        List<WebElement> dates = driver.findElements(By.xpath("//a[contains(@id,'CTS_TS_LAND_PER_DESCR')]"));
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        
        Thread.sleep(5000);
        for (int i = 1; i <= 3; i++) {
            WebElement dateElement = dates.get(i);
            String dt = dateElement.getText();
            String[] fulldt = dt.split(" To ");
            String stdt = fulldt[0];
            String enddt = fulldt[1];

            System.out.println("Date " + i + ": " + dt);
            System.out.println("Start Date: " + stdt);
            System.out.println("End Date: " + enddt);

            
            Date startDate = (Date)df.parse(stdt);
            Date endDate = (Date)df.parse(enddt);
            
            Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);
			
			//Check if the start Date is Saturday
			if(startCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
			{
				System.out.println("Starting date is Saturday");
			}
			else
			{
				System.out.println("Starting date is not a Saturday");
			}
			
			//Check if the end date is Friday
			Calendar endCalendar=Calendar.getInstance();
			endCalendar.setTime(endDate);
			if(endCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
			{
				System.out.println("Ending date is Friday");
			}
			else
			{
				System.out.println("Ending date is not a Friday");
			}
			
			//Check if the Start and End date both are matched
			
			if(startCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY && endCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
			{
				System.out.println("Both dates are matched");
			}
			else
			{
				System.out.println("Both dates are misMatched");
			}

            
        }
        Select sl=new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']")));
		
		sl.selectByVisibleText("Date");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_DATE$prompt']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='curdate']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
		
		
		WebElement today=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='CTS_TS_LAND_PER_DESCR30$0']")));
		
		String Details=today.getText();
        
		System.out.println(Details);
		
		String[] currentDate=Details.split(" To ");
		
		
		Date startCurrentDate=(Date)df.parse(currentDate[0]);
		Date endCurrentDate=(Date)df.parse(currentDate[1]);
		
		Calendar startCalendar1 = Calendar.getInstance();
		startCalendar1.setTime(startCurrentDate);
		
		//Check if the current week start date correct or not
		if(startCalendar1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
		{
			System.out.println("Current week start day is matched");
		}
		else
		{
			System.out.println("Current week start day is misMatched");
		}
		
		
		//Check if the current week end date correct or not
		Calendar endCalendar1 = Calendar.getInstance();
		endCalendar1.setTime(endCurrentDate);
		
		if(endCalendar1.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
		{
			System.out.println("Current week end day is matched");
		}
		else
		{
			System.out.println("Current week end day is misMatched");
		}
		
		//Check if the current week start and end date both are matched or not
		
		if(startCalendar1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY && endCalendar1.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
		{
			System.out.println("Both days are matched");
		}
		else
		{
			System.out.println("Both days are not matched");
		}
		Thread.sleep(3000);
		//Test case 3
		Select search=new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']")));
		
		search.selectByVisibleText("Status");
		
		Thread.sleep(2000);
		Select  status1=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']"))));
		
		status1.selectByVisibleText("Approved");
		
		WebElement srch=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")));
		srch.click();
		Thread.sleep(3000);
		////div[@id='win0divCTS_TS_LAND_PER_CTS_TS_STATUS_LAND$0']
		List<WebElement> allStatus=driver.findElements(By.xpath("//div[@class='ps_box-scrollarea-row']/div[2]/div[2]/div[2]/span"));
		
		Thread.sleep(3000);
		String expectedStatus="Approved";
		
		boolean result=false;

		for(int i=0;i<allStatus.size();i++)
		{
			String actualStatus=allStatus.get(i).getText();
			
			if(actualStatus.equals(expectedStatus))
			{
				result=true;
			}
			else
			{
				//result=false;
				System.out.println("Status are validated");
				break;
			}
			
		}
		if(result==true)
		{
			System.out.println("Status are valid");
		}
		else
		{
			System.out.println("Status are not valid");
		}
		
		//Test case: Overdue(status)
		Select search2=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']"))));
	
		Thread.sleep(5000);
		search2.selectByVisibleText("Status");
		
		Select  status2=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']"))));
		status2.selectByVisibleText("Partially Approved");
		
		WebElement srch2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")));
		srch2.click();
		
		Thread.sleep(3000);
		WebElement msg1=driver.findElement(By.xpath("//div[@id='shortmsg']"));
		
		String alertMsg1=msg1.getText();
		
		System.out.println(alertMsg1);
		
		WebElement ok=driver.findElement(By.xpath("//a[@id='#ICOK']"));
		ok.click();
		
    }
}
