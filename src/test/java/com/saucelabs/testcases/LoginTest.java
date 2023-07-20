package com.saucelabs.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucelabs.driverMgmt.BaseTest;

public class LoginTest extends BaseTest {
	By username = By.id("user-name");
	By Password = By.id("password");
	By loginbutton = By.id("login-button");
	By HomePageHeader = By.xpath("//div[text()='Swag Labs']");

	@Test
	public void testlogin() throws IOException {
		driver.get("https://www.saucedemo.com/");
		capture();
		driver.findElement(username).sendKeys("standard_user");
		driver.findElement(Password).sendKeys("secret_sauce");
		driver.findElement(loginbutton).click();
		Boolean status = driver.findElement(HomePageHeader).isDisplayed();
		Assert.assertTrue(status);

	}

}
