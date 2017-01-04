package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MyAccountPage {
	//following the factory object page model 
    WebDriver driver;
    @FindBy(xpath=".//*[@id='center_column']/h1")
    WebElement pageHeading;
    @FindBy(xpath=".//*[@id='center_column']/p[1]")
    WebElement accountCreatedMsg;
    @FindBy(xpath=".//*[@id='header']/div[2]/div/div/nav/span/strong")
    WebElement topLeftCorner;
    @FindBy(xpath=".//*[@id='center_column']/div/div[1]/ul/li[5]/a/span")
    WebElement myPersonalInfo;
	//Building the constructor 
	public MyAccountPage(WebDriver argDriver){
		this.driver=argDriver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
		PageFactory.initElements(driver, this);
	}
	//returning page heading text
	public String pageHeadingText(){
		return pageHeading.getText();
	}
	//returning the account crating message as a string.
	public String accountCreatedMessage(){
		return accountCreatedMsg.getText();
	}
	public String topLeftCornerText(){
		return topLeftCorner.getText();
	}
	public void clickMyPersonaInfo(){
		myPersonalInfo.click();
		
	}

}
