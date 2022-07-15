package connect_OCBaseMethods;

import java.time.LocalDateTime;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class Recover extends ServiceDetail {

	@Test
	public static void recoverAtDestination() throws Exception
	{
//		String tzone = driver.findElement(By.id("lblActualRecoverTimeSZone0")).getText();//lblActualRecoverTimeSZone0
//		String rectime = getTime(tzone);
		driver.findElement(By.id("txtDeliveRecoverTime")).sendKeys(rdytime);
		driver.findElement(By.id("txtDeliveRecoverTime")).sendKeys(Keys.TAB);
		driver.findElement(By.id("txtDeliveRecoverDate")).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"btnGreenTickDropped\"]")).click();
		Thread.sleep(5000);
		}
	
	public static String getTime(String timeZone)
	{
		
		LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone(timeZone).toZoneId());
	System.out.println(localNow);
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
	
	System.out.println(text);
	return text;
	}
}
