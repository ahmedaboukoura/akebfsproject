package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateAnAccountPage {
	//following the factory object page model 
	WebDriver driver;
	@FindBy(css="#id_gender1")
	WebElement genderMale;
	@FindBy(css =".page-heading")
	WebElement pageHeading;
	@FindBy(css="#customer_firstname")
	WebElement firstName;
	@FindBy(css="#customer_lastname")
	WebElement lastName;
	@FindBy(id="passwd")
	WebElement password;
	@FindBy(id="days")
	WebElement days;
	@FindBy(css="#months")
	WebElement months;
	@FindBy(name="years")
	WebElement years;
	@FindBy(css="#submitAccount")
	WebElement submitButton;
	//Building the constructor 
	public CreateAnAccountPage(WebDriver argDriver){
		this.driver=argDriver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
		PageFactory.initElements(driver, this);
	}
	//return the heading text to validate the page
	public String pageHeadingText(){
		return pageHeading.getText();
	}
	//choosing the gender to be male
	public void chooseGenderMale(){
		genderMale.submit();
	}
	//method to take a first name as a input and type it in the field
	public void typeFirstName(String argFirstName){
		firstName.sendKeys(argFirstName);
	}
	//method to take a last name as a input and type it in the field
	public void typelastName(String argLastName){
		lastName.sendKeys(argLastName);
	}
	//method to take a password as a input and type it in the field
	public void typePassword(String argPassword){
		password.sendKeys(argPassword);
	}
	//metrhod to choose a date of birth using select class 
	public void dateOfBirth(String mm , String dd , String yyyy){
		Select selectDays = new Select(days);
		selectDays.selectByValue(dd);
		Select selectYears = new Select(years);
		selectYears.selectByValue(yyyy);
		Select selectMonths = new Select(months);
		selectMonths.selectByValue(mm);
	}
	//clicking submit button
	public void clickSubmit(){
		submitButton.click();
	}
}
