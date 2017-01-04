package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShirtSearchedPage {
	@FindBy(xpath=".//*[@id='best-sellers_block_right']/h4/a")
	WebElement LeftProductHeading;
	WebDriver driver;
	@FindBy(xpath=".//*[@id='center_column']/ul/li[1]/div/div[2]/h5/a")
	WebElement plaidShirt;
	@FindBy(xpath=".//*[@id='center_column']/ul/li[2]/div/div[2]/h5/a")
	WebElement sleeveTShirt;
	@FindBy(xpath=".//*[@id='best-sellers_block_right']/div/ul/li[1]/div/div/span")
	WebElement firstLeftPrice;
	@FindBy(xpath=".//*[@id='best-sellers_block_right']/div/ul/li[2]/div/div/span")
	WebElement secondLeftPrice;
	@FindBy(xpath=".//*[@id='best-sellers_block_right']/div/ul/li[3]/div/div/span")
	WebElement thirdLeftPrice;
	@FindBy(xpath=".//*[@id='best-sellers_block_right']/div/ul/li[4]/div/div/span")
	WebElement fourthLeftPrice;
	@FindBy(xpath=".//*[@id='best-sellers_block_right']/div/ul/li[5]/div/div/span")
	WebElement fifthLeftPrice;
	@FindBy(xpath=".//*[@id='best-sellers_block_right']/div/ul/li[6]/div/div/span")
	WebElement sixthLeftPrice;
	//building the constructor 
	public ShirtSearchedPage(WebDriver argDriver){
		this.driver=argDriver;
		PageFactory.initElements(driver, this);
	}
	//method to return a string of the left products heading .
	public String topleftproduct(){
		return LeftProductHeading.getText();
	}
	public String firstSearchedProduct(){
		return plaidShirt.getText();
	}
	public String secondSearchedProduct(){
		return sleeveTShirt.getText();
	}
	public float ThirdLeftPrice(){
		String s3 = thirdLeftPrice.getText().replace("$", "");
		 float f3 = Float.parseFloat(s3 + "f");
		 return f3;
	}
	public float SecondLeftPrice(){
		String s2 = secondLeftPrice.getText().replace("$", "");
		 float f2 = Float.parseFloat(s2 + "f");
		 return f2;
	}
	public float FirstLeftPrice(){
		String s1 = firstLeftPrice.getText().replace("$", "");
		 float f1 = Float.parseFloat(s1 + "f");
		 return f1;
	}
	public float FourthLeftPrice(){
		String s4 = fourthLeftPrice.getText().replace("$", "");
		 float f4 = Float.parseFloat(s4 + "f");
		 return f4;
	}
	public float FifthLeftPrice(){
		String s5 = fifthLeftPrice.getText().replace("$", "");
		float f5 = Float.parseFloat(s5 + "f");
		return f5;
	}
	public float SixthLeftPrice(){
		String s6 = sixthLeftPrice.getText().replace("$", "");
		 float f6 = Float.parseFloat(s6 + "f");
		 return f6;
	}

}
