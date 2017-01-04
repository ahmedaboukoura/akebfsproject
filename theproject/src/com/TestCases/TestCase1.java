package com.TestCases;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.utilites.Screenshot;
import com.utilites.Xls_Reader;

public class TestCase1 {
    String homeurl; 
    String browserType;
    WebDriver driver ;
    String imagepath1;
    String chromeDriverPath; 
    String firefoxDriverPath;
    String ieDriverPAth;
    String excelfilepath;
   	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ahmed aboukoura\\Desktop\\project\\theproject\\src\\com\\utilites\\TestCase1("+timeStamp+").html");
   	ExtentReports extent = new ExtentReports();
   	Screenshot sc = new Screenshot();
	//method to switching between browser , simply change the "browser type" in properties file .
	//to "chrome" or "firefox or "ie" to switch between browser .
   	//and please write the drivers path in the properties file also .
    @BeforeMethod
	public void openthebrowser(){
    	extent.attachReporter(htmlReporter);
        ExtentTest test = extent.createTest("opening the browsers");
    	if(browserType.contains("chrome")){
    	    System.setProperty("webdriver.chrome.driver",chromeDriverPath);
   	        driver = new ChromeDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	        test.log(Status.INFO, "opening the chrome browser.");
            driver.get(homeurl);
    	}else if(browserType.contains("firefox")){
    		System.setProperty("webdriver.gecko.driver",firefoxDriverPath);
   	        driver = new FirefoxDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	     test.log(Status.INFO, "opening firefox browser");
            driver.get(homeurl);
    	}else if(browserType.contains("ie")){
    		System.setProperty("webdriver.ie.driver",ieDriverPAth);
   	        driver = new InternetExplorerDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	     test.log(Status.INFO, "opening edge browser");
            driver.get(homeurl);
    	}
	}
    //the first test case simply it validate the red color of the email field after typing the invalid email address
    //i use the excel file to input the invalid email address
	@Test(priority = 1)
		public void TestCase12() throws IOException{
			try {
				extent.attachReporter(htmlReporter);
		        ExtentTest test = extent.createTest("Create New Account with Invalid Email Address.");
				HomePage objhomepage = new HomePage(driver);
				objhomepage.SignIn();
				test.log(Status.INFO, "clicking sign in button  that exist is in the uper left corner.");
				LoginPage objloginpage = new LoginPage(driver);
				Xls_Reader excelreader = new Xls_Reader(excelfilepath);
				test.log(Status.INFO,"reading the data from the excel file.");
				String s =excelreader.getCellData("sheet1", 4, 3);
				objloginpage.writeEmailAdress(s);
				test.log(Status.INFO, "typing the invalid email address that excit in location E3 in excel file.");
				objloginpage.ClickSubmit();
				test.log(Status.INFO, "clicking submit button.");
				String argColor = objloginpage.emailFieldColor();
				System.out.println(argColor);
				test.log(Status.INFO, "getting the field color after writting an invalid email.");  
				if(argColor.contains("rgba(241, 51, 64, 1)")){
				test.log(Status.PASS, "Email Address Filed is Red in color to indicate invalid Email Address.");
				}else{
					test.log(Status.FAIL, "Email Address Filed is not Red in color to indicate invalid Email Address.");
					String ImagePath = sc.CaptureScreen(driver,imagepath1);
			       	test.addScreenCaptureFromPath("image1.jpg");
			       	Assert.assertFalse(true);
				}
			} catch (Exception e)  {
				e.printStackTrace();
				extent.attachReporter(htmlReporter);
				ExtentTest test = extent.createTest("very important notice");
				test.log(Status.FATAL, "there is a code error please revise the code");
			}
	}
	//after method for closing the browser and putting.flush for extent report.
	@AfterMethod
	public void closingthebrowser(){
		extent.flush();
		driver.close();
	
	}
	//loading the properties file
	@BeforeSuite
	  public void LoadPropFile() {
	      try{
		      Properties prop = new Properties();
		      FileInputStream Fs = new FileInputStream("C:\\Users\\ahmed aboukoura\\Desktop\\project\\theproject\\src\\com\\utilites\\url&browser.properties");
		      prop.load(Fs);
		      browserType = prop.getProperty("browser");
		      firefoxDriverPath=prop.getProperty("firefoxdriverpath");
		      chromeDriverPath=prop.getProperty("chromedriverpath");
		      ieDriverPAth=(String) prop.get("iedriverpath");
		      homeurl=prop.getProperty("homeurl");
		      excelfilepath=prop.getProperty("excelfilepath");
		      imagepath1=prop.getProperty("imagepath1");
	      }catch(Exception e){
	      }
	 }
}

			
 
