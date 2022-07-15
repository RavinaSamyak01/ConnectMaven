package connect_OCBaseMethods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class WaitForArrival extends ServiceDetail  {

	@Test
	public static void waitForArr() throws Exception
	{
		
		try {
			Thread.sleep(10000);
			String stg = driver.findElement(By.id("lblStages")).getText();
				if(stg.contains("WAIT FOR ARRIVAL"))
					{
					Thread.sleep(6000);
					
					
//					String tzone = driver.findElement(By.id("lblEditArrivalTimeSZone")).getText();
//					String arrtime = getTime(tzone);
//					driver.findElement(By.id("txtArrivalTime")).sendKeys(arrtime);
//					driver.findElement(By.id("txtArrivalTime")).sendKeys(Keys.TAB);
					Thread.sleep(6000);
					driver.findElement(By.id("btnWaitGreyTick")).click();	
					}
		}
		catch (Exception e) {
			System.out.println("Wait for Arrival processed!!");
			}
		
		
	}
	
	public static String getTime(String timeZone)
	{
		
		LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone(timeZone).toZoneId());
	System.out.println(localNow);
	DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;     
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
