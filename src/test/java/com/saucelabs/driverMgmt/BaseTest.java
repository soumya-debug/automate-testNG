package com.saucelabs.driverMgmt;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	public WebDriver driver;

	@BeforeMethod
	public void initDriver() {

		String browser = "chrome";
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();

		} else {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() throws IOException, InterruptedException {
		Thread.sleep(Duration.ofSeconds(5));
		capture();
		driver.quit();
	}

	public String capture() throws IOException {
		// UUID uuid = UUID.randomUUID();
		if (driver == null)
			System.out.println("driver was null");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ "TestProof_Screenshot_" + getDateAndTime() + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

		return dest;
	}

	public String getDateAndTime() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E_yyyy-MM-dd_hh_mm_ss_a_zzz");
		return ft.format(dNow).toString();
	}
}
