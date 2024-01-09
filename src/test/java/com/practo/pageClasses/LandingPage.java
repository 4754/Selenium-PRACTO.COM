package com.practo.pageClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practo.baseClasses.PageBaseClass;

public class LandingPage extends PageBaseClass {
	
	@FindBy(xpath = "//span[contains(text(),'Search for hospitals')]")
	public WebElement searchHospital;
	
	//@FindBy( xpath = "//div[@class = 'product-tab__title' and contains(text(),'Diagnostics')]")
	@FindBy(xpath = "//div[text()='Diagnostics']")
	public WebElement diagonistics;
	
	@FindBy( xpath = "//span[contains(text(),'For Providers')]")
	public WebElement provider;
	
	@FindBy( xpath = "//a[contains(text(),'Corporate wellness')]")
	public WebElement corporateWellness;

	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
	
	public HospitalsInCity clickSearchHospital() {
		logger.log(Status.INFO, "scrolling down to element : " + "searchHospital");
		scrollDownTillElement(searchHospital);
		logger.log(Status.PASS ,"Scrolled down successfully to element : " + "searchHospital");
		logger.log(Status.INFO, "Clicking link : " + searchHospital);
		//searchHospital.click();
		visibilityWaitWithClick(searchHospital, 5);
		logger.log(Status.PASS, "Clicked successfully : " + searchHospital);
		HospitalsInCity hospitalInCity = new HospitalsInCity(driver,logger);
		PageFactory.initElements(driver, hospitalInCity);
		return hospitalInCity;
	}
	
	public DiagonisticsPage clickDiagonistics() {
		logger.log(Status.INFO, "About to click : " + "diagonistics");
		//diagonistics.click();
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click(); ",diagonistics );
		logger.log(Status.PASS, "Cliced diagonistics link");
		DiagonisticsPage dPage = new DiagonisticsPage(driver,logger);
		PageFactory.initElements(driver, dPage);
		return dPage;
	}
	
	public EmployeeHealthPage openEmployeeHealthPage() {
		
		logger.log(Status.INFO, "Abot to click : " + "provider");
		//provider.click();
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click(); ",provider );
		logger.log(Status.PASS, "Clicked successfully");
		//corporateWellness.click();
		js.executeScript("arguments[0].click(); ",corporateWellness );
		handleWindow();
		EmployeeHealthPage EHPage = new EmployeeHealthPage(driver,logger);
		PageFactory.initElements(driver, EHPage);
		return EHPage;
	}
	

}
