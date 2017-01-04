package com.TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pages.CreateAnAccountPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.ShirtSearchedPage;
import com.utilites.Screenshot;

import junit.framework.Assert;

public class TestCase4 {
    String homeurl; 
    String browserType;
    WebDriver driver ;
    String imagepath8;
    String imagepath9;
    String imagepath10;
    String chromeDriverPath; 
    String firefoxDriverPath;
    String ieDriverPAth;
    String excelfilepath;
   	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ahmed aboukoura\\Desktop\\project\\theproject\\src\\com\\utilites\\TestCase4("+timeStamp+").html");
   	ExtentReports extent = new ExtentReports();
   	Screenshot sc = new Screenshot();
	//method to switching between browser , simply change the "browser type" in properties file .
	//to "chrome" or "firefox or "ie" to switch between browser .
   	//and please write the drivers path in the properties file also .
    @BeforeClass
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
            test.log(Status.INFO, "navigating to the home page.");
    	}
	}
    //a test to validate that both result product is having shirt key .
    @Test(priority = 0)
    public void TestCase41() throws IOException{
		try {
			extent.attachReporter(htmlReporter);
			ExtentTest test = extent.createTest("Product Search-part one-");
			HomePage objhomepage = new HomePage(driver);
			ShirtSearchedPage objSearchPage = new ShirtSearchedPage(driver);   
			objhomepage.SearchKey("shirt");
			test.log(Status.INFO, "typing  the key word 'shirt' in search field.");
			objhomepage.clickSearch();
			test.log(Status.INFO, "clicking search button.");
			String s1 = objSearchPage.firstSearchedProduct();
			String s2 = objSearchPage.secondSearchedProduct();
			System.out.println(s1);
			System.out.println(s2);
			if(s1.contains("shirt")&&s2.contains("shirt")){
				test.log(Status.PASS, "all result product names have 'shirt' key word.");
			}else{
				test.log(Status.FAIL, "either one of the product or all of them doesn't have key word 'shirt' on it's name.");
				String ImagePath = sc.CaptureScreen(driver,imagepath8);
			   	test.addScreenCaptureFromPath("image8.jpg");
			}
		} catch (Exception e) {
			extent.attachReporter(htmlReporter);
			ExtentTest test = extent.createTest("very important notice.");
			test.log(Status.FATAL, "there is a code error please revise the code");
			e.printStackTrace();
		}
    }
    //a method to validate that there no price more than 50& on the left side and that 
    //the product that display in the left side is top seller
    @Test(priority = 1)
    public void TestCase42(){
    	try {
			extent.attachReporter(htmlReporter);
			ExtentTest test = extent.createTest("Product Search-part two-");
			ShirtSearchedPage objSearchPage = new ShirtSearchedPage(driver);
			String s1 = objSearchPage.topleftproduct();
			System.out.println(s1);
			if(s1.contains("TOP SELLERS")){
				test.log(Status.PASS, "Top Seller Product in left side.");
			}else{
				test.log(Status.FAIL, "top seller product is not on the left side.");
				String ImagePath = sc.CaptureScreen(driver,imagepath9);
			   	test.addScreenCaptureFromPath("image9.jpg");
			}
			float f1 = objSearchPage.FirstLeftPrice();
			float f2 = objSearchPage.SecondLeftPrice();
			float f3 = objSearchPage.ThirdLeftPrice();
			float f4 = objSearchPage.FourthLeftPrice();
			float  f5 = objSearchPage.FifthLeftPrice();
			float f6 = objSearchPage.SixthLeftPrice();
			if(f1<50f && f2 <50f && f3 <50f && f4<50f && f5 <50f && f6 <50){
				test.log(Status.PASS, "No product price more than 50$ should  display on the left side.");
			}else{
				test.log(Status.FAIL, "there product price more than 50$ displayed on the left side.");
				String ImagePath = sc.CaptureScreen(driver,imagepath10);
			   	test.addScreenCaptureFromPath("image10.jpg");
			}
		} catch (IOException e) {
			extent.attachReporter(htmlReporter);
			ExtentTest test = extent.createTest("very important notice");
			test.log(Status.FATAL, "there is a code error please revise the code.");
			e.printStackTrace();
		}
    }
	//after method for closing the browser and putting.flush for extent report.
	@AfterClass
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
		    imagepath8=prop.getProperty("imagepath8");
	        imagepath9=prop.getProperty("imagepath9");
	        imagepath10=prop.getProperty("imagepath10");
	      }catch(Exception e){
	      }
	 }	

}
