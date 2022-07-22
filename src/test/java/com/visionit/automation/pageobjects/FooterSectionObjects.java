package com.visionit.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class FooterSectionObjects
{
	private static final Logger logger = LogManager.getLogger(FooterSectionObjects.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	JavascriptExecutor js;
	
	
	//-----------Locators-------------
	private By newsLetterElement= By.id("newsletter-input");
	private By proceedBtn= By.name("submitNewsletter");
	private By successSubscriptionMsgField= By.xpath("//div[@class='clearfix']/following-sibling::p[@class='alert alert-success']");
	private By failSubscriptionMsgField=By.xpath("//div[@class='clearfix']/following-sibling::p[@class='alert alert-danger']");
    
	//------------Expected---------------
	String emailId= "kadamashwini9124@gmail.com";
	String successedSubMsg= " Newsletter : You have successfully subscribed to this newsletter.";
	String failSubMsg= " Newsletter : This email address is already registered.";
	
	
	
	//------------Constructor------------
     public FooterSectionObjects(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
     
    //-----------SET NEWSLETTERELEMENT-------------------
     public void setNewsLetter()
     {
     	WebElement newsLetterTextBox =driver.findElement(newsLetterElement);
     	//Scroll till Newsletter element available on screen using Javascript executor
     	js= (JavascriptExecutor)driver;
     	js.executeScript("arguments[0].scrollIntoView(true);", newsLetterTextBox);
     	Assert.assertEquals(true, newsLetterTextBox.isDisplayed());
     	logger.info("Validate newsletter text box");
     	WebElement proceedButton= driver.findElement(proceedBtn);
     	Assert.assertEquals(true, proceedButton.isDisplayed());
     	logger.info("Validate newsletter's proceed button");
     	scn.log("Validate newsletter text box and proceed button");
     }
     
     //--------------ENTER EMAIL ID IN NEWSLETTER------------
     public void enterEmailId()
     {
     	WebElement newsLetterTextBox =driver.findElement(newsLetterElement);
     	newsLetterTextBox.sendKeys(emailId);
     	logger.info("Enter emailId in newsletter text box");
     	scn.log("Enter emailId in newsletter text box");
     }
     
     //-----------------------Method to click on proceed button----------------
     public void clickOnProceedBtn()
     {
     	WebElement proceedButton= driver.findElement(proceedBtn);
     	proceedButton.click();
     	logger.info("Click on proceed button");
     	scn.log("Click on proceed button");
     }
     
     //------------fetch subscription message------------------------------
 
     public void fetchSubscriptionMsg()
     {
     	try {
     	    WebElement subscriptionMsg= driver.findElement(successSubscriptionMsgField);
     		logger.info("Fetch the message text for successful email subscription,subscription msg is: "+ subscriptionMsg.getText());
     		scn.log("Fetch the message text for successful email subscription, ,subscription msg is: "+ subscriptionMsg.getText());
     		} 
     	catch(Exception e){
     	    WebElement failSubscriptionMsg= driver.findElement(failSubscriptionMsgField);
     	    logger.info("Fetch the message text for failed email subscription: "+failSubscriptionMsg.getText());
     	    scn.log("Fetch the message text for failed email subscription: "+failSubscriptionMsg.getText());
     	    }
     }
     
 
     //--------------validate subscription message-------------------------
     public void validateSubscriptionMsg()
     {
 	    try 
 	    {
 	     WebElement subscriptionMsg= driver.findElement(successSubscriptionMsgField);
 		 subscriptionMsg.getText().equals(successedSubMsg);
 		 logger.info("Validate the message text for successful email subscription,subscription msg is: "+ subscriptionMsg.getText());
 		 scn.log("Validate the message text for successful email subscription, ,subscription msg is: "+ subscriptionMsg.getText());
 		} 
 	    catch(Exception e)
 	    {
 	     WebElement failSubscriptionMsg= driver.findElement(failSubscriptionMsgField);
 	     failSubscriptionMsg.getText().equals(failSubMsg);
 	     logger.info("Validate the message text for failed email subscription: "+failSubscriptionMsg.getText());
 	     scn.log("Validate the message text for failed email subscription: "+failSubscriptionMsg.getText());
 	    }
     }
     
     
     
     
     
     
     
     
     
     
     
}
