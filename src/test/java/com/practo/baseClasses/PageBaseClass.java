package com.practo.baseClasses;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practo.pageClasses.LandingPage;
import com.practo.utilities.DateUtil;

public class PageBaseClass extends TestBaseClass {
	
	public ExtentTest logger;

	public PageBaseClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	/****************** OpenApplication ***********************/
	public LandingPage OpenApplication(String url) {
		logger.log(Status.INFO, "Opening the WebSite :" + url );
		System.out.println("Opening the WebSite :" + url);
		driver.get(url);
		logger.log(Status.PASS, "Successfully Opened the " + url);
		LandingPage landingPage = new LandingPage(driver, logger);
		PageFactory.initElements(driver, landingPage);
		return landingPage;
	}
	
	/****************** Get Page Title ***********************/
	public void getTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	
	
	/****************** Accept Java Script Alert ***********************/
	public void acceptAlert(){
		try {
			logger.log(Status.INFO, "Handling Alert");
			logger.log(Status.INFO, "Waiting for alert to appear");
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			logger.log(Status.PASS, "Alert is present");
			Alert pop_up = driver.switchTo().alert();
			logger.log(Status.INFO, "Getting Alet Message");
			String alertMessage= pop_up.getText();
			logger.log(Status.PASS, "Alert Message is :" + alertMessage);
			System.out.println("Alert message : " + alertMessage);
			pop_up.accept();
			logger.log(Status.PASS, "Page Alert Accepted");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	/****************** Wait till visibility of element to click***********************/
	
	public void visibilityWaitWithClick(WebElement element, int seconds) {
		
		WebDriverWait wait = new WebDriverWait(driver,seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		
	}
	

	
	/****************** Cancel Java Script Alert ***********************/
	public void cancelAlert(){
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();;
			logger.log(Status.PASS, "Page Alert Rejected");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	/****************** Select value From DropDown ***********************/
	public void selectDropDownValue(WebElement webElement, String value){
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			logger.log(Status.PASS, "Selectd the Value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	
	/****************** Scroll Down till Element ***********************/
	
	public void scrollDownTillElement(WebElement element) {
		try {
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", element);
			reportPass("Scrolled down to : " + element);
		} catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void scrollInView() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, 100000)");
	}
	
	/****************** Handle window ***********************/
	
	public void handleWindow() {
		try {
		Set<String> windowHandles =driver.getWindowHandles();
		for(String win : windowHandles) {
			driver.switchTo().window(win);
		}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	
	

	
	/****************** Get All Elements of DropDown ***********************/
	public void getAllElementsOfDropDown(WebElement webElement, String value){
		try {
			Select select = new Select(webElement);
			select.selectByValue(value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	

	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "//screenshots//" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "//screenshots//" + DateUtil.getTimeStamp() + ".png");

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
