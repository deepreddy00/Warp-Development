package wrasoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class wrapdevelopment {
	public WebDriver driver;

	
	@Parameters({"url"})
	@BeforeTest
	public  void init(String url)
	{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\manideep\\workspace\\Warp\\src\\main\\java\\wrasoo\\chromedriver_win32\\chromedriver.exe");
    driver= new ChromeDriver();
    driver.get(url);
	driver.manage().window().maximize();
		
		
	}
	
	@Test
	// Verifying url 
	public void Veifyurl()
	{
	String url =driver.getCurrentUrl();
	Assert.assertEquals(url, "http://recruitment.warpdevelopment.co.za/");
	}
		
		
		@Test
		// Verifying page Title Warp Development
		public void VerifyTitile()
		{
			String actualtitle = "Warp Development";
			Assert.assertEquals(driver.getTitle(), actualtitle);	
		}
		
		
		@Test (dataProvider="getData")
		public void LoginDetails(String username,String password)
		{
		WebElement user=driver.findElement(By.name("Email"));
		user.clear();
		user.sendKeys(username);
		WebElement pass = driver.findElement(By.id("Password"));
		pass.clear();
		pass.sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		}
		
		@Test
		//verifing Unpublished product 
		public void productCheck()
		{
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("unpublished product");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[@href='/Product/Order/1673']")).click();
		
		if(driver.getPageSource().contains("Unpublished Product"))
		{
			driver.findElement(By.xpath("//input[@type='number']")).sendKeys("4");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			System.out.println("Product placed");
		}
		else
		{
			System.out.println("Not present");
		}
		
		
		
		if(driver.getPageSource().contains("Product ordered."))
		{
			System.out.println("order placed successfully");
		}
		else
		{
			System.out.println("order not placed");
		}
		
		
	}
		
		
		@DataProvider(name="getData")
		public Object[][] getData(){
			//Object [][] data = new Object [rowCount][colCount];
			Object [][] data = new Object [2][2];
			
			data [0][0] = "test@warpdevelopment.com";
			data [0][1] = "123";
			
			data[1][0] = "second";
			data[1][1] = "msdjnrfn";
			
			return data;
			
		}

		
		
		
		
}
