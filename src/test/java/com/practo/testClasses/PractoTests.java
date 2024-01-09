package com.practo.testClasses;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.practo.baseClasses.PageBaseClass;
import com.practo.baseClasses.TestBaseClass;
import com.practo.pageClasses.DiagonisticsPage;
import com.practo.pageClasses.EmployeeHealthPage;
import com.practo.pageClasses.HospitalsInCity;
import com.practo.pageClasses.LandingPage;
import com.practo.utilities.TestDataProvider;

public class PractoTests extends TestBaseClass {
	
	LandingPage landingPage;
	HospitalsInCity hospitals;
	DiagonisticsPage diagonisticsPage;
	EmployeeHealthPage EHPage;
	
	//@Ignore
	@Test(priority = 0)
	public void listOfhospitals() {
		logger = report.createTest("List of hospitals");
		
		readConfig();
		invokeBrowser(prop.getProperty("browser"));
		
		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication(prop.getProperty("baseURL"));
		waitForPageLoad();
		hospitals = landingPage.clickSearchHospital();
		hospitals.selectCity(prop.getProperty("city"));
		hospitals.getHospitals();
		
	}
	
	
	//@Ignore
	@Test(priority = 1)
	public void getTopCities() {
		
        logger = report.createTest("getTopCities");
		
		readConfig();
		invokeBrowser(prop.getProperty("browser"));
		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication(prop.getProperty("baseURL"));
		//waitForPageLoad();
		diagonisticsPage = landingPage.clickDiagonistics();
		diagonisticsPage.printTopCities();
		
	}
	
	//@Ignore
	@Test(priority = 2,dataProvider = "getData")
	public void fillForm(Hashtable<String, String> testData) {
        logger = report.createTest("Fill form with : " + testData.get("Comment"));
		
		readConfig();
		invokeBrowser(prop.getProperty("browser"));
		PageBaseClass pageBase = new PageBaseClass(driver,logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication(prop.getProperty("baseURL"));
		waitForPageLoad();
		EHPage = landingPage.openEmployeeHealthPage();
		EHPage.fillName(testData.get("Name"));
		EHPage.fillORGName(testData.get("Organisation_name"));
		EHPage.fillEmail(testData.get("Email_id"));
		EHPage.fillNumber(testData.get("Contact_no"));
		EHPage.selectValue(testData.get("Organisation_size"));
		EHPage.clickDemo();
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return TestDataProvider.getTestData("data.xlsx", "Sheet1", "FillForm");
	}
	

}
