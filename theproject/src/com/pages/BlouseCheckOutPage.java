package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlouseCheckOutPage {
	@FindBy(id=("total_product_price_2_11_0"))
	WebElement totalPrice;
	@FindBy(id=("product_price_2_11_0"))
	WebElement unitPrice;
	WebDriver driver;
	public BlouseCheckOutPage(WebDriver argDriver){
		this.driver=argDriver;
		PageFactory.initElements(driver, this);
	}
	//returning the unit price as a float 
	public float unitPrice(){
		String s1 = unitPrice.getText().replace("$", "");
		 float f1 = Float.parseFloat(s1 + "f");
		 return f1;
	}
//returning the total price as a float 
	public float totalPrice(){
		String s1 = totalPrice.getText().replace("$", "");
		 float f1 = Float.parseFloat(s1 + "f");
		 return f1;
	}

}
