package connect_OCBaseMethods;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class XerWaitForArrival extends ServiceDetail {

	@Test
	public static void xerWaitForArr() throws Exception {

		try {
			Thread.sleep(10000);
			String stg = driver.findElement(By.id("lblStages")).getText();
			if (stg.contains("XER WAIT FOR ARR")) {
				Thread.sleep(9000);
				// String tzone =
				// driver.findElement(By.id("lblEditArrivalTimeSZone")).getText();
				// String xerarrtime = getTime(tzone);

				WebElement ZoneID = driver.findElement(By.id("lblEditArrivalTimeSZone"));
				String ZOneID = ZoneID.getText();
				System.out.println("ZoneID of is==" + ZOneID);

				if (ZOneID.equalsIgnoreCase("EDT")) {
					ZOneID = "America/New_York";
				} else if (ZOneID.equalsIgnoreCase("CDT")) {
					ZOneID = "CST";
				} else if (ZOneID.equalsIgnoreCase("PDT")) {
					ZOneID = "PST";
				}

				WebElement Time = driver.findElement(By.id("txtArrivalTime"));
				Time.clear();
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));

				Time.sendKeys(dateFormat.format(date));
				Time.sendKeys(Keys.TAB);

				// driver.findElement(By.id("txtArrivalTime")).sendKeys(xerarrtime);
				// driver.findElement(By.id("txtArrivalTime")).sendKeys(Keys.TAB);
				Thread.sleep(6000);
				driver.findElement(By.id("btnWaitGreyTick")).click();
			}
		} catch (Exception e) {
			System.out.println("XER Wait for Arrival Not Exist in Flow!!");
		}

	}

	public static String getTime(String timeZone) {

		LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone(timeZone).toZoneId());
		System.out.println(localNow);
		DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
		String text = Integer.toString(localNow.getHour());
		String text1 = "";
		if (localNow.getMinute() < 10) {
			text1 = "0".concat(Integer.toString(localNow.getMinute()));
		} else {
			text1 = Integer.toString(localNow.getMinute());
		}
		text = text.concat(text1);

		System.out.println(text);
		return text;
	}
}
