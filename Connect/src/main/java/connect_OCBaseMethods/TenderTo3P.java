package connect_OCBaseMethods;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TenderTo3P extends ServiceDetail {
	
	@Test
	public static void tndrTo3P() throws Exception
	{
		Thread.sleep(4000);
		driver.findElement(By.id("hlkShiplabel")).click();
		Thread.sleep(5000);
		
		Select p3acc = new Select(driver.findElement(By.id("cmbCarrier")));
		p3acc.selectByVisibleText("1 (510087763)");
		Thread.sleep(4000);
		
		Select Contacttype = new Select(driver.findElement(By.id("cmbServiceType")));
		Contacttype.selectByVisibleText("FEDEX_GROUND");
		Thread.sleep(4000);
		
//		WebElement txtacc = driver.findElement(By.id("txtAccount"));
//		txtacc.clear();
//		txtacc.sendKeys("510087763");
//		txtacc.sendKeys(Keys.TAB);
//		Thread.sleep(2000);
		
		driver.findElement(By.id("btnSubmit")).click();
		Thread.sleep(10000);
		
		driver.findElement(By.id("idanchorclose")).click();
		Thread.sleep(4000);
		
//		String tzone = driver.findElement(By.id("Spn3pTime")).getText();
//		String tndrtime = getTime(tzone);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		SimpleDateFormat tndrtime = new SimpleDateFormat("hh:mm");
//		
//		tndrtime.setTimeZone(TimeZone.getTimeZone("EDT"));
//		//System.out.println(tndrtime.format(calendar.getTime()));    

		
		driver.findElement(By.id("txt3pTime")).sendKeys(rdytime);
		driver.findElement(By.id("txt3pTime")).sendKeys(Keys.TAB);
		Thread.sleep(3000);
		driver.findElement(By.id("GreenTick")).click();
		
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
