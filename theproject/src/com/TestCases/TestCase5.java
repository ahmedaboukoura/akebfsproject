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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pages.BlouseCheckOutPage;
import com.pages.HomePage;
import com.utilites.Screenshot;

public class TestCase5 {
    String homeurl; 
    String browserType;
    WebDriver driver ;
    String imagepath11;
    String chromeDriverPath; 
    String firefoxDriverPath;
    String ieDriverPAth;
    String excelfilepath;
   	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ahmed aboukoura\\Desktop\\project\\theproject\\src\\com\\utilites\\TestCase5("+timeStamp+").html");
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
            driver.manage().window().maximize();
    	}else if(browserType.contains("firefox")){
    		System.setProperty("webdriver.gecko.driver",firefoxDriverPath);
   	        driver = new FirefoxDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	        driver.manage().window().maximize();
   	     test.log(Status.INFO, "opening firefox browser.");
            driver.get(homeurl);
    	}else if(browserType.contains("ie")){
    		System.setProperty("webdriver.ie.driver",ieDriverPAth);
   	        driver = new InternetExplorerDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	        driver.manage().window().maximize();
   	     test.log(Status.INFO, "opening edge browser.");
            driver.get(homeurl);
            test.log(Status.INFO, "navigating to the home page.");
    	}
	}
    //first we hover over mouse to a blouse then clicking the quick view button
    //then changing the size and the quantity and then clicking add to card and then checkout buttons 
    //and finally we validate that the total price equal double the unit price 
    @Test
    public void testCase51(){
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	extent.attachReporter(htmlReporter);
		WebDriverWait wait = new WebDriverWait(driver , 10);
		ExtentTest test = extent.createTest("Add to cart validation");
    	try {
			HomePage objHomePage = new HomePage(driver);
			BlouseCheckOutPage objCheckout = new BlouseCheckOutPage(driver);
			objHomePage.hoverOverMouseBlose();
			test.log(Status.INFO, " Hover over mouse on blouse.");
			WebElement quickView=driver.findElement(By.xpath(".//*[@id='homefeatured']/li[2]/div/div[1]/div/a[2]"));
			wait.until(ExpectedConditions.elementToBeClickable(quickView));
			objHomePage.ClickBloseQuickView();
			test.log(Status.INFO, "clikcing Quick View Button.");
			objHomePage.clickPlus();
			test.log(Status.INFO, "clicking plus icon to add one more product.");
			objHomePage.SelectSize("l");
			test.log(Status.INFO, "selecting size to large.");
			objHomePage.ClickSubmit();
			test.log(Status.INFO , "clicking add to cart.");
			objHomePage.clickProccedToCheckOut();
			test.log(Status.INFO, "clicking procced to checkout.");
			float f1 = objCheckout.unitPrice();
			float f2 = objCheckout.totalPrice();
			System.out.print(f1+f2 );
			if(f2==(f1*2)){
				test.log(Status.PASS , "Validation of  Unit Price & total Price based on quantity is passed.");	
			}else{
				test.log(Status.FAIL, "Validation of  Unit Price & total Price based on quantity didn't pass.");
				String ImagePath = sc.CaptureScreen(driver,imagepath11);
			   	test.addScreenCaptureFromPath("image11.jpg");
			}
		} catch (IOException e) {
			test.log(Status.FATAL, "there is an error on the code please revise your code dude , just do it , go ahead !.");
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
		    imagepath11=prop.getProperty("imagepath11");
	      }catch(Exception e){
	      }
	 }	

}

