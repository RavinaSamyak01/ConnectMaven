package connect_OCBaseMethods;

import java.time.LocalDateTime;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class OnHandAtDestination extends BaseInit{

	@Test
	public static void onHandDst() throws Exception
	{
		Thread.sleep(4000);
		
		try {
			Thread.sleep(5000);
			String stg = driver.findElement(By.id("lblStages")).getText();
				if(stg.contains("ONHAND"))
				{
					Thread.sleep(6000);
					String tzone = driver.findElement(By.id("spnOnHand")).getText();
					String onhandtime = getTime(tzone);
					driver.findElement(By.id("txtTimeOnhand")).sendKeys(onhandtime);
					driver.findElement(By.id("txtTimeOnhand")).sendKeys(Keys.TAB);
					
					driver.findElement(By.id("txtSpokeWithOnhand")).sendKeys("Sandeep Das");
					driver.findElement(By.id("txtSpokeWithOnhand")).sendKeys(Keys.TAB);
					driver.findElement(By.id("btnHAAOnHandDeliveryStages")).click();
					Thread.sleep(3000);
				}
	}
		catch (Exception e) {
			System.out.println("OnHand Stage is not displayed !!");
			}
		
	
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
