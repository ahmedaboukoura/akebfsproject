package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {
	//following the factory page object model
	WebDriver driver;
	@FindBy(css="#email_create")
	WebElement leftemailbox ;
	@FindBy(xpath=".//*[@id='SubmitCreate']")
	WebElement SubmitButton;
	@FindBy(css=("#create_account_error>ol>li"))
	WebElement errorMsg;
	//Building the constructor 
	public LoginPage(WebDriver argDriver){
		this.driver=argDriver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
		PageFactory.initElements(driver, this);
	}
	//method to write a given email to emailadressbox
	public void writeEmailAdress(String argEmail){
		leftemailbox.sendKeys(argEmail);
	}
	//clicking submit method
	public void ClickSubmit(){
		SubmitButton.click();
	}
	//returing the color string of the email box 
	public String emailFieldColor(){
		return leftemailbox.getCssValue("color");	
	}
	//return error msg text
	public String ErrorMsg(){
		return errorMsg.getText();
	}
	
}
