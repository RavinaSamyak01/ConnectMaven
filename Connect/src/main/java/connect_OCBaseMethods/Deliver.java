package connect_OCBaseMethods;

import java.time.LocalDateTime;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Deliver extends ServiceDetail {
	
	@Test
	public static void confirmDelivery() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		
			String svc = driver.findElement(By.id("lblServiceID")).getText();
						
			if(svc.equals("LOC") || svc.equals("DRV")|| svc.equals("SDC")|| svc.equals("FRG"))
				{	
				driver.findElement(By.id("txtActualDeliveryTime")).sendKeys(rdytime);
				Thread.sleep(3000);
				driver.findElement(By.id("txtDeliverySignature")).sendKeys("asharma");
				Thread.sleep(3000);
				driver.findElement(By.id("GreenTickDropped")).click();
				Thread.sleep(3000);
				boolean dlpop = driver.getPageSource().contains("NetLink Global Logistics");			
				
						if(dlpop == true)
						{
							driver.findElement(By.id("btnOk")).click();				
						}
					
						Thread.sleep(4000);
						}
				
			if(svc.equals("SD") || svc.equals("PA")|| svc.equals("FRA"))
			{	
				String tzone = driver.findElement(By.id("lblActualDeliveryTimeSZone")).getText();
				String rectime = getTime(tzone);
				driver.findElement(By.id("txtActualDeliveryTime")).sendKeys(rectime);
				driver.findElement(By.id("txtActualDeliveryTime")).sendKeys(Keys.TAB);
				Thread.sleep(9000);
				driver.findElement(By.id("txtDeliverySignature")).sendKeys("A. Sharma");
				Thread.sleep(2000);
				WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnGreenTickDropped\"]")));
				jse.executeScript("arguments[0].click();",btn);
				Thread.sleep(4000);
			}
			
			if(svc.equals("AIR"))
			{
				String tzone = driver.findElement(By.id("spnActualDel")).getText();
				String rectime = getTime(tzone);
				driver.findElement(By.id("txtOnHandActualDeliveryTime")).sendKeys(rectime);
				driver.findElement(By.id("txtOnHandActualDeliveryTime")).sendKeys(Keys.TAB);
				driver.findElement(By.id("txtSignature")).sendKeys("A. Sharma");
				driver.findElement(By.id("btnHAAOnHandDeliveryStages")).click();
				Thread.sleep(4000);
			}
			
		}
	
	public static String getTime(String timeZone)
	{
		
		LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone(timeZone).toZoneId());
		//DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;     
		String text = Integer.toString(localNow.getHour());
		String text1 = "";
	    if(localNow.getMinute()<10) {
	    	text1 = "0".concat(Integer.toString(localNow.getMinute()));
	    }
			    else {
			    	text1 = Integer.toString(localNow.getMinute());
			    }
		text = text.concat(text1);
		return text;
	}
}
