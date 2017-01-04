package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MyPersonalInfoPage {
	//following the factory object page model 
	WebDriver driver;
	@FindBy(id="old_passwd")
	WebElement oldPassword;
	@FindBy(id="lastname")
	WebElement lastName;
	@FindBy(xpath=".//*[@id='header']/div[2]/div/div/nav/div[1]/a/span")
	WebElement topLeftCorner;
	@FindBy(xpath=".//*[@id='center_column']/div/form/fieldset/div[11]/button")
	WebElement saveButton;
	//Building the constructor 
	public MyPersonalInfoPage(WebDriver argDriver){
		this.driver=argDriver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
		PageFactory.initElements(driver, this);
	}
	public void UpdateLasttName(String argLasttName){
		lastName.clear();
		lastName.sendKeys(argLasttName);
	}
	public void UpdatePassword(String argPassword){
		oldPassword.clear();
		oldPassword.sendKeys(argPassword);
	}
	public String topLeftCornerText(){
		return topLeftCorner.getText();
	}
	public void ClickSave(){
		saveButton.click();
	}
}
