package com.web.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.web.pages.LoginPage;
import com.web.utils.TestDataProvider;

import CommonLibImplimentation.CommonDriver;

public class LoginPageTests {
	CommonDriver cmnDriver;
	String url = "http://demo.guru99.com/V4";
	LoginPage loginpage;
	
	@BeforeMethod
	public void setup() throws Exception {
		cmnDriver = new CommonDriver("chrome");
		cmnDriver.setPageloadTimeout(60);
		cmnDriver.navigateToUrl(url);
		loginpage = new LoginPage(cmnDriver.getDriver());
		
	}
	
	@Test(dataProvider = "getDataFromExcel", dataProviderClass = TestDataProvider.class)
	public void verifyLogin(String sUserName, String sPassword) throws Exception {
		loginpage.loginToApplication(sUserName, sPassword);
		Thread.sleep(2000);
		String actualTitle = cmnDriver.getTitle();
		String expectedTitle = "Guru99 Bank Manager HomePage";
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	
	@AfterMethod
	public void cleaUp() {
		cmnDriver.closeAllBrowser();
	}

}
