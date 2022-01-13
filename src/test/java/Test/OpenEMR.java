package Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenEMR {

	@Test(priority=1)
	public void createpatient() throws InterruptedException
	{
		//setup- download the driver file for execution
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Actions act=new Actions(driver);
		
		//LogIn Using Credentials
		driver.get("https://demo.openemr.io/a/openemr");
		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/div[1]/div[4]/button")).click();
		
		//mouse over
		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"mainMenu\"]/div/div[5]/div/div"))).perform();
		
		driver.findElement(By.xpath("//*[@id=\"mainMenu\"]/div/div[5]/div/ul/li[2]/div")).click();
		
		
		driver.switchTo().frame(driver.findElement(By.name("pat")));
		Select salu=new Select(driver.findElement(By.id("form_title")));
		salu.selectByValue("Mrs.");
		driver.findElement(By.id("form_fname")).sendKeys("abcd");
		driver.findElement(By.id("form_lname")).sendKeys("efgh");
		
		driver.findElement(By.id("form_DOB")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/table/tbody/tr[3]/td[3]/div")).click();
		
		//driver.findElement(By.id("form_DOB")).sendKeys("2022-01-11");
		
		Select  gender= new Select(driver.findElement(By.id("form_sex")));
		gender.selectByVisibleText("Female");
		
		driver.findElement(By.id("create")).click();
		
		driver.switchTo().defaultContent();//come out of previous frame
		Thread.sleep(1000);
		driver.switchTo().frame(driver.findElement(By.id("modalframe")));
		driver.findElement(By.xpath("//*[@id=\"searchResultsHeader\"]/center/input")).click();
		driver.switchTo().defaultContent();
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		
		/*Select text= new Select((WebElement) driver.switchTo().frame(driver.findElement(By.id("modalframe"))));
		System.out.println(text);*/
		
	
		
		
		
		
	}
	
	@Test(priority=5)
	public void register()  
	{
		
	}
}
