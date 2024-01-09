package com.practo.pageClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practo.baseClasses.PageBaseClass;

public class EmployeeHealthPage extends PageBaseClass {

	public EmployeeHealthPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//input[@id='name']")
	public WebElement name_TB;

	@FindBy(xpath = "//input[@id='organization_name']")
	public WebElement orgName_TB;

	@FindBy(xpath = "//input[@id='official_email_id']")
	public WebElement email;

	@FindBy(xpath = "//input[@id='official_phone_no']")
	public WebElement phoneNo;

	@FindBy(xpath = "//select[@id='organization_size']")
	public WebElement dropDown;

	@FindBy(xpath = "//button[@id='button-style']")
	public WebElement demo_BTN;

	public void fillName(String name) {
		try {
			logger.log(Status.INFO, "send the data to : " + "name_TB");
			name_TB.sendKeys(name);
			logger.log(Status.PASS, "Data sent to : name_TB : " + name);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void fillORGName(String name) {
		try {
			logger.log(Status.INFO, "send the data to : " + "orgName_TB");
			orgName_TB.sendKeys(name);
			logger.log(Status.PASS, "Data sent to : orgName_TB : " + name);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void fillEmail(String email) {
		try {
			logger.log(Status.INFO, "send the data to : " + "email");
			this.email.sendKeys(email);
			logger.log(Status.PASS, "Data sent to : email : " + email);

		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void fillNumber(String num) {
		try {
			logger.log(Status.INFO, "send the data to : " + "phoneNo");
			phoneNo.sendKeys(num);
			logger.log(Status.PASS, "Data sent to : phoneNo : " + num);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void selectValue(String value) {
		try {
			logger.log(Status.INFO, "Selecting fro drop down");
			getAllElementsOfDropDown(dropDown, value);
			logger.log(Status.PASS, "Drop down selected with value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void clickDemo() {
		try {
			logger.log(Status.INFO, "Submitting the form");
			//demo_BTN.click();
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click(); ",demo_BTN );
			logger.log(Status.PASS, "Submitted the form");
			
			acceptAlert();
		} catch (Exception e) {
			reportFail(e.getMessage());
			
		} 

	}

}
