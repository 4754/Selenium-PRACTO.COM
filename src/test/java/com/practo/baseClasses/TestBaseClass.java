package com.practo.baseClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practo.utilities.ExtentReportManager;
public class TestBaseClass {
	
	public WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public Properties prop;

	/****************** Invoke Browser ***********************/
	public void invokeBrowser(String browserName) {

		try {

			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Mozila")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//drivers//geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Opera")) {
				System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "//drivers//operadriver.exe");
				driver = new OperaDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "//drivers//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else {
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public void readConfig() {
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "//config.properties");
				prop.load(file);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@AfterMethod
	public void flushReports() {
		report.flush();
		driver.quit();
	}
	

	/***************** Wait Functions in Framework *****************/
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
