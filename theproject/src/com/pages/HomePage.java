package com.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	String browserType;
    String homeurl;
	//following the factory page object model
	WebDriver driver;
	@FindBy(xpath=(".//*[@id='header']/div[2]/div/div/nav/div[1]/a"))
	WebElement signin;
	@FindBy(css="#search_query_top")
	WebElement searchBox;
	@FindBy(name="submit_search")
	WebElement searchButton;
	@FindBy(xpath=".//*[@id='homefeatured']/li[2]/div/div[1]/div")
	WebElement blouse;
	@FindBy(xpath=".//*[@id='homefeatured']/li[2]/div/div[1]/div/a[2]")
	WebElement quickView;
	@FindBy(className="icon-plus")
	WebElement plusIcon;
	@FindBy(xpath=".//*[@id='add_to_cart']/button")
	WebElement sumbitButton;
	@FindBy(id="group_1")
	WebElement sizeList;
	@FindBy(xpath=".//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	WebElement proccedToCheckout;
	@FindBy(xpath=".//*[@id='layer_cart']/div[1]/div[1]/h2")
	WebElement heading;
	//building the constructor 
	public HomePage(WebDriver argDriver){
		this.driver=argDriver;
		PageFactory.initElements(driver, this);
	}
	//hover over mouse to blouse
	public void hoverOverMouseBlose(){
		try {
			Thread.sleep(3000);
			Actions move = new Actions(driver);
			move.moveToElement(blouse).perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//click blose quick view
	public void ClickBloseQuickView(){
		quickView.click();
	}
	//cliking plus icon
	public void clickPlus(){
		driver.switchTo().frame(4);
		plusIcon.click();			
		driver.switchTo().defaultContent();
	}
	//Clicking proceed to checkout
	public void clickProccedToCheckOut(){
		try {
			WebDriverWait wait = new WebDriverWait(driver , 20);
			wait.until(ExpectedConditions.visibilityOf(proccedToCheckout));
			Thread.sleep(3000);
			proccedToCheckout.click();
			Thread.sleep(4000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Clicking add to cart
	public void ClickSubmit(){
		driver.switchTo().frame(4);
		sumbitButton.click();	
		driver.switchTo().defaultContent();
		}
	//Choosing the size
	public void SelectSize(String argSize){
		driver.switchTo().frame(4);
		Select select = new Select(sizeList);
		if(argSize=="m"){
			select.selectByValue("2");
		}else if (argSize=="l"){
			select.selectByValue("3");
		}
		driver.switchTo().defaultContent();
	}

	//clicking sign link in the top left
	public void SignIn(){
		signin.click();
	}
	public void clickSignIn(){
		driver.findElement(By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a")).click();
	}
	
	public void loadprpfiles(){
		try {
			Properties prop = new Properties();
			FileInputStream fs;
			fs = new FileInputStream("C:\\Users\\ahmed aboukoura\\Desktop\\project\\theproject\\src\\com\\utilites\\url&browser.properties");
			prop.load(fs);
			browserType=prop.getProperty("browser");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SearchKey(String argSearchText){
		searchBox.sendKeys(argSearchText);
	}
	public void clickSearch(){
		searchButton.click();
	}
		
}


		
	



