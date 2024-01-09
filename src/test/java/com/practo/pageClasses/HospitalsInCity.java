package com.practo.pageClasses;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practo.baseClasses.PageBaseClass;

public class HospitalsInCity extends PageBaseClass {

	public HospitalsInCity(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		
	}
	
	@FindBy(xpath = "//div[@id='container']//input[1]")
	public WebElement search_city_TB;
	
	
	
	@FindBy(xpath = "//i[@class= 'icon-ic_cross_solid']")
	public WebElement search_cancel;
	
	
	
	//@FindBy(xpath = "//div[contains(text(),'Search in entire bangalore')]")
	@ FindBy( xpath = "//div[@class='c-omni-suggestion-item__content__title' and contains(text(),'Bangalore')]")
	public WebElement entire_Bangalore;
	
	@FindBy(xpath = "//span[@class= 'u-color--green']/span//ancestor::div[@class = 'u-cushion u-white-fill u-normal-text o-card o-card--separated c-list-card']")
	public List<WebElement> elements;
	

	

	
	public void selectCity(String city) {
		
		try {
			if(!search_city_TB.getAttribute("value").equalsIgnoreCase("Bangalore")) {
			
				logger.log(Status.INFO, "Entering city in : " + "search_city");
				//search_city_TB.click();
				visibilityWaitWithClick(search_city_TB, 5);
				//search_cancel.click();
				visibilityWaitWithClick(search_cancel, 5);
				search_city_TB.sendKeys(city);
				//Thread.sleep(1000);
				//entire_Bangalore.click();
				visibilityWaitWithClick(entire_Bangalore, 5);
				Thread.sleep(5000);
				logger.log(Status.PASS, "Entered city successfully");
				waitForPageLoad();
				scrollInView();
				waitForPageLoad();
				scrollInView();
				reportPass("Entered city successfully and scrolled down");
			} else {
				waitForPageLoad();
				scrollInView();
				waitForPageLoad();
				scrollInView();
			}
			
		} catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void getHospitals() {
		try {
			logger.log(Status.INFO, "About to print name of hospitals");
			System.out.println("Hospitals details with rating more than 3.5 and open 24*7 :");
			for(int i = 0 ; i < elements.size();i++) {
				System.out.println("/**************************/");
				System.out.println(elements.get(i).getText());
				System.out.println("/**************************/");
			}
			System.out.println();
			logger.log(Status.PASS, "Printed to console name of hosptals");
			reportPass("Printed Hospitals details successfully");
			
		} catch(Exception e) {
			reportFail(e.getMessage());
		}
	}


}
