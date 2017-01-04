package com.TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pages.CreateAnAccountPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.MyAccountPage;
import com.pages.MyPersonalInfoPage;
import com.utilites.Screenshot;
import com.utilites.Xls_Reader;

public class TestCase3 {
    String homeurl; 
    String browserType;
    WebDriver driver ;
    String imagepath3;
    String imagepath4;
    String imagepath5;
    String imagepath6;
    String imagepath7;
    String chromeDriverPath; 
    String firefoxDriverPath;
    String ieDriverPAth;
    String excelfilepath;
    String actualmsg;
   	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ahmed aboukoura\\Desktop\\project\\theproject\\src\\com\\utilites\\TestCase3("+timeStamp+").html");
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
   	        test.log(Status.INFO, "opening chrome browser");
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
    //it method to validate a account creation page displaying when typing a correct email address
	@Test(priority = 0)
		public void TestCase31() throws IOException{
		extent.attachReporter(htmlReporter);
        ExtentTest test = extent.createTest("Create New Account with valid new Email Address-part one-");
        LoginPage objloginpage = new LoginPage(driver);
        HomePage objhomepage = new HomePage(driver);
        CreateAnAccountPage objAccountPage= new CreateAnAccountPage(driver); 
			try {
				objhomepage.SignIn();
				test.log(Status.INFO, "clicking sign in button  that is in the uper left corner");
				objloginpage.writeEmailAdress(timeStamp + "ahmed@gmail.com");
				test.log(Status.INFO, "typing random email address");
				objloginpage.ClickSubmit();
				test.log(Status.INFO, "clicking submit");
				String argHeadText = objAccountPage.pageHeadingText();
				System.out.println(argHeadText);
				if(argHeadText.equalsIgnoreCase("AUTHENTICATION")){
				test.log(Status.PASS, "New Account Creation Page displayed successfuly");
				}else{
					test.log(Status.FAIL, "New Account Creation Page didn't display successfuly");
					String ImagePath = sc.CaptureScreen(driver,imagepath3);
			       	test.addScreenCaptureFromPath("image3.jpg");
			       	Assert.assertFalse(true);
				}
			} catch (Exception e)  {
				e.printStackTrace();
				test.log(Status.FATAL, "there is a code error please revise the code");
			}
	}
	//method to to validate  User should Navigated to My Account Page.
	//also to Validate Your account has been created message 
	//also to validate First Name & Last Name in the top left corner of the page
	@Test (priority = 1)
	public void TestCase32() throws IOException{
		try {
			extent.attachReporter(htmlReporter);
			ExtentTest test = extent.createTest("Create New Account with valid new Email Address-part two-");
			RandomStringUtils s = new RandomStringUtils();
			String stamp = s.randomAlphabetic(2);
			CreateAnAccountPage objAccountPage= new CreateAnAccountPage(driver); 
			MyAccountPage objMyPage = new MyAccountPage(driver);
			objAccountPage.chooseGenderMale();
			test.log(Status.INFO, "choosing gender as a male.");
			objAccountPage.typeFirstName("ahmed" + stamp);
			test.log(Status.INFO, "typing a randomly generated first name.");
			objAccountPage.typelastName("koura"+stamp);
			test.log(Status.INFO, "typing a randomly generated last name.");
			objAccountPage.typePassword("12345678");
			test.log(Status.INFO, "typing a password as '12345678'.");
			objAccountPage.dateOfBirth("10", "11", "1993");
			test.log(Status.INFO, "selecting the date of birth from the list.");
			objAccountPage.clickSubmit();
			test.log(Status.INFO, "clicking register.");
			String argHeadText = objMyPage.pageHeadingText();
			System.out.println(argHeadText);
			if(argHeadText.contains("MY ACCOUNT")){
				test.log(Status.PASS, "User  Navigated to My Account Page successfuly.");
			}else{
				test.log(Status.FAIL, "user didnt navigate to my accoutn page.");
				String ImagePath = sc.CaptureScreen(driver,imagepath4);
			   	test.addScreenCaptureFromPath("image4.jpg");
			}
			String argMSg = objMyPage.accountCreatedMessage();
			System.out.println(argMSg);
			if(argMSg.contains("Your account has been created.")){
				test.log(Status.PASS, "Validation of Your account has been created message passed !.");
			}else{
				test.log(Status.FAIL, " there is no ' Your account has been created message !'displayed.");
				String ImagePath = sc.CaptureScreen(driver,imagepath5);
			   	test.addScreenCaptureFromPath("image5.jpg");
			}
			String cornerText = objMyPage.topLeftCornerText();
			System.out.println(cornerText);
			if(cornerText.contains("ahmed")&&cornerText.contains("koura")){
				test.log(Status.PASS, "top left corner contain first and last name.");
			}else{
				test.log(Status.FAIL, "top left corner doesnt contain neither first name nor last name.");
				String ImagePath = sc.CaptureScreen(driver,imagepath6);
			   	test.addScreenCaptureFromPath("image6.jpg");
			}
			objMyPage.clickMyPersonaInfo();
		} catch (Exception e) {
			extent.attachReporter(htmlReporter);
			ExtentTest test = extent.createTest("very important notice");
			test.log(Status.FATAL, "there is a code error please revise the code");
			e.printStackTrace();
		}	
	}
	//we open the personal information page, and then doing a changes on last name and writing the same old password
	//then validate that the last name will update in the top of the page after saving the changes
	@Test(priority = 2)
	public void TestCase33() throws IOException{
		try {
			extent.attachReporter(htmlReporter);
			ExtentTest test = extent.createTest("Create New Account with valid new Email Address-part three-");
			MyPersonalInfoPage objInfo = new MyPersonalInfoPage(driver);
			objInfo.UpdateLasttName("john");
			test.log(Status.INFO, "updating the last name.");
			objInfo.UpdatePassword("12345678");
			test.log(Status.INFO, "typing the password.");
			objInfo.ClickSave();
			test.log(Status.INFO, "saving the changes.");
			String argName = objInfo.topLeftCornerText();
			if(argName.contains("john")){
				test.log(Status.PASS, "last name updated succesfuly in top corner.");
			}else{
				test.log(Status.FAIL, "last name didn't update succesfuly.");
				String ImagePath = sc.CaptureScreen(driver,imagepath7);
			   	test.addScreenCaptureFromPath("image7.jpg");
			}
		} catch (Exception e) {
			extent.attachReporter(htmlReporter);
			ExtentTest test = extent.createTest("very important notice.");
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
		      imagepath3=prop.getProperty("imagepath3");
		      imagepath4=prop.getProperty("imagepath4");
		      imagepath5=prop.getProperty("imagepath5");
		      imagepath6=prop.getProperty("imagepath6");
		      imagepath7=prop.getProperty("imagepath7");

	      }catch(Exception e){
	      }
	 }
}
