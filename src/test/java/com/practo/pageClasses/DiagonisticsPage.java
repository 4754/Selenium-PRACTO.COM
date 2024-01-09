package com.practo.pageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practo.baseClasses.PageBaseClass;

public class DiagonisticsPage extends PageBaseClass {
	

	
	@FindBy(xpath = "//div[@class='u-margint--standard o-f-color--primary']")
	public List<WebElement> top_cities;

	public DiagonisticsPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
	
	public void printTopCities() {
		try {
		logger.log(Status.INFO, "Printing Top Cities to console");
		System.out.println("Top Cities are : ");
		waitForPageLoad();
		for(WebElement ele : top_cities) {
			System.out.println(ele.getText());
		}
		System.out.println("/***************************/");
		logger.log(Status.PASS, "Printed Top Cities to console");
		reportPass("Printed Top cities Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
