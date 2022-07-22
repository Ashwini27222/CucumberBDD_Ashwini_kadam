package com.visionit.automation.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class ProductSameObjects
{
	private static final Logger logger = LogManager.getLogger(ProductSameObjects.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
   
	//============= Locators for WebElements on Landing page ====================================//

	private By searchBox= By.xpath("//input[@placeholder='Search']");
	private By suggestedProdList= By.xpath("//div[@class='ac_results']/ul/li");
	
	//-------Constructor----
		public ProductSameObjects(WebDriver driver,Scenario scn)
		{
			this.driver= driver;
			this.scn=scn;
		}
		//============ 1. Method to set searchBox =================================================//
	    public void setSearchBox()
	    {
	    	WebElement searchBoxElement =driver.findElement(searchBox);
	    	Assert.assertEquals(true, searchBoxElement.isDisplayed());
	    	logger.info("Validate the searchBox");
	    	scn.log("Validate the searchBox");
	    }

	//============ 2. Method to search a product=================================================//
	    public void searchProduct(String product)
	    {
	    	WebElement searchBoxElement =driver.findElement(searchBox);
	    	searchBoxElement.sendKeys(product);
	    	logger.info("search a product in searchbox, product is: "+ product);
	    	scn.log("search a product in searchbox, product is: "+ product);
	    	
	    }
	    
	//============ 3. Method to get suggested product list ======================================//
	    public void getSuggestedProdList()
	    {
	     
	    	List <WebElement> suggProdList= driver.findElements(suggestedProdList);
	        wait= new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.visibilityOfAllElements(suggProdList));
	        logger.info("Fetch the list of suggested products, size of list is: "+ suggProdList.size());
	        scn.log("Fetch the list of suggested products, size of list is: "+ suggProdList.size());
	        logger.info("List of suggested products is as follow :");
	        System.out.println("List of suggested products is as follow :");
	        for(int i=0; i< suggProdList.size(); i++)
	        {
	      	  logger.info(i+1 +". "+ suggProdList.get(i).getText());
	      	  scn.log(i+1 +". "+ suggProdList.get(i).getText());
	      	  System.out.println(i+1 +". "+ suggProdList.get(i).getText());
	        }
	        Assert.assertEquals(false, suggProdList.isEmpty());
	      }
	 //============ 4. Method to validate suggested product list ======================================//
	    public void validateSuggProdList(String prodName, int prodSize)
	    {
	    int count=0;
    	List <WebElement> suggProdList= driver.findElements(suggestedProdList);
    	logger.info("Fetch the list of suggested products contains desired product name, is as below: ");
    	scn.log("Validate the list of suggested products, list is as below: ");
        for(int i=0; i< suggProdList.size(); i++)
    	{
    		if(suggProdList.get(i).getText().contains(prodName.toString()))
    		{
    			Assert.assertEquals(true,suggProdList.get(i).getText().contains(prodName.toString()));
    			logger.info(i+1 +". "+ suggProdList.get(i).getText());
        		scn.log(i+1 +". "+ suggProdList.get(i).getText());
        		count++;
        	}
    	}
    	Assert.assertEquals(prodSize, count);
    	logger.info("Total no. of product contains desired product name is: "+ count);
    	scn.log("Total no. of product contains desired product name is: "+ count);
	
}
}
