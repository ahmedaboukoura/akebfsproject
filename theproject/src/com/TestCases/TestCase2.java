package com.TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.utilites.Screenshot;
import com.utilites.Xls_Reader;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class TestCase2 {
    String homeurl; 
    String browserType;
    WebDriver driver ;
    String imagepath2;
    String chromeDriverPath; 
    String firefoxDriverPath;
    String ieDriverPAth;
    String excelfilepath;
    String actualmsg;
   	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ahmed aboukoura\\Desktop\\project\\theproject\\src\\com\\utilites\\TestCase2("+timeStamp+").html");
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
   	     test.log(Status.INFO, "opening firefox browser.");
            driver.get(homeurl);
    	}else if(browserType.contains("ie")){
    		System.setProperty("webdriver.ie.driver",ieDriverPAth);
   	        driver = new InternetExplorerDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	     test.log(Status.INFO, "opening edge browser.");
            driver.get(homeurl);
    	}
	}
    //it's a method to validate the error msg we should got when we enter an already been registered account.
	@Test(priority = 1)
		public void TestCase21() throws IOException{
			try {
				extent.attachReporter(htmlReporter);
		        ExtentTest test = extent.createTest("Create New Account with Already Registered Email Address");
				HomePage objhomepage = new HomePage(driver);
				objhomepage.SignIn();
				test.log(Status.INFO, "clicking sign in button  that exist  in the uper left corner of the page.");
				LoginPage objloginpage = new LoginPage(driver);
				Xls_Reader excelreader = new Xls_Reader(excelfilepath);
				test.log(Status.INFO,"reading the data from the excel file.");
				String s =excelreader.getCellData("sheet1", 4, 4);
				objloginpage.writeEmailAdress(s.replaceAll("Existing Email Address: ", ""));
				test.log(Status.INFO, "typing the Existing Email Address that locate atE4 in excel file.");
				objloginpage.ClickSubmit();
				test.log(Status.INFO, "clicking submit.");
				String argErrorMsg = objloginpage.ErrorMsg();
				if(argErrorMsg.equalsIgnoreCase(actualmsg)){
				test.log(Status.PASS, "got an error msg"+actualmsg+".");
				}else{
					test.log(Status.FAIL, "fail because we didnt get error msg.");
					String ImagePath = sc.CaptureScreen(driver,imagepath2);
			       	test.addScreenCaptureFromPath("image2.jpg");
			       	Assert.assertFalse(true);
				}
			} catch (Exception e)  {
				e.printStackTrace();
				extent.attachReporter(htmlReporter);
				ExtentTest test = extent.createTest("very important notice.");
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
		      imagepath2=prop.getProperty("imagepath2");
		      actualmsg=prop.getProperty("msg");
	      }catch(Exception e){
	      }
	 }
}
				
	 
