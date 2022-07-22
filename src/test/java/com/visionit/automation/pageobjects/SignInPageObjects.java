package com.visionit.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class SignInPageObjects 
{
	private static final Logger logger = LogManager.getLogger(SignInPageObjects.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	
	//----------Locators
	private By signInBtn=By.xpath("//a[@class='login']");
	
	//-------Constructor----
	public SignInPageObjects(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	
	//---------Display Button Methods--------
	public void signInButtonValidation()
	{
		WebElement signIn =driver.findElement(signInBtn);
		Assert.assertEquals(true, signIn.isDisplayed());
    	logger.info("Validate the signIn Button");
    	scn.log("Validate the signIn Button");
	}
	
	//---------Click Button Methods--------
	public void clickSignInButton()
	{
       WebElement signIn =driver.findElement(signInBtn);
    	
    	wait= new WebDriverWait(driver,20);
    	wait.until(ExpectedConditions.elementToBeClickable(signIn));
    	signIn.click();
    	logger.info("Click on the signIn Button");
    	scn.log("Click on the signIn Button");
	}
	
	//---------Sign in Page Title Validation Method--------
	public void signInPageVal(String SignPageTitle)
	{
		wait= new WebDriverWait(driver,60);
    	boolean acttitle =wait.until(ExpectedConditions.titleIs(SignPageTitle));
        Assert.assertEquals(true, acttitle);
    	logger.info("Validation of signIn page title is: "+ SignPageTitle);
    	scn.log("Validation of signIn page title is: "+ SignPageTitle);
	}
}
