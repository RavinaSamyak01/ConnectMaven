package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Drop extends ServiceDetail{
	
	@Test
	public static void dropAtOrigin() throws Exception
	{
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		Thread.sleep(5000);
		WebElement dropoff = driver.findElement(By.id("txtEditActualDropoffTime"));
		dropoff.sendKeys(rdytime);
		dropoff.sendKeys(Keys.TAB);
		Thread.sleep(6000);
		WebElement btng = driver.findElement(By.id("btnGreenTickDropped"));
		jse.executeScript("arguments[0].click();",btng);

		Thread.sleep(5000);
		
		try {
			String awb = driver.findElement(By.id("errorid")).getText();
			System.out.println(awb);
			if(awb.equals("Airbill is Required."))
			{
		
				Thread.sleep(2000);
				driver.findElement(By.id("AddAribill")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("txtEditAirwayBill_0")).sendKeys("2121-2170");
				Thread.sleep(5000);
				driver.findElement(By.id("txtEditQty_0")).sendKeys("1");
				Thread.sleep(1000);
				driver.findElement(By.id("txtEdittotalWeight_0")).sendKeys("10");
				Thread.sleep(1000);
				WebElement saveairbill = driver.findElement(By.xpath("//*[@id=\"SaveAirbill_0\"]"));
				jse.executeScript("arguments[0].click();",saveairbill);
				Thread.sleep(4000);
				
				WebElement confirmdrop =  driver.findElement(By.id("btnGreenTickDropped"));
				confirmdrop.click();
				
				Thread.sleep(4000);
			}
		}
		
		catch (Exception e) {
			System.out.println("Airbill already exist !!");
		}
		
	}
}
