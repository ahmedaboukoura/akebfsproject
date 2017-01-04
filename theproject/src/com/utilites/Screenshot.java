package com.utilites;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static String CaptureScreen(WebDriver driver, String ImagesPath){
		
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File srcFile = oScn.getScreenshotAs(OutputType.FILE);
		File destFile = new File(ImagesPath+".jpg");
		try{
			FileUtils.copyFile(srcFile, destFile);
		}catch(IOException e){
			e.getStackTrace();
		}
		return ImagesPath+".jpg";
		
		
		
	}

}
