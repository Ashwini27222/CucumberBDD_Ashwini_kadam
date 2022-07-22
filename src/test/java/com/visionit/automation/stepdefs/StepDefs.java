package com.visionit.automation.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

import com.visionit.automation.core.WebDriverFactory;
import com.visionit.automation.pageobjects.CmnPageObjects;
import com.visionit.automation.pageobjects.FooterSectionObjects;
import com.visionit.automation.pageobjects.ProductSameObjects;
import com.visionit.automation.pageobjects.SignInPageObjects;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs
{
	private static final Logger logger =  LogManager.getLogger(StepDefs.class);
	
	WebDriver driver;
	String base_url = "https://automationpractice.com";
	int implicit_wait_timeout_in_sec = 20;
	Scenario scn; 
	
	CmnPageObjects cmnPageObjects;
	SignInPageObjects signPageObjects;
	ProductSameObjects productSameObjects;
	FooterSectionObjects footerSectionObjects;
	
	//-------------------------BEFORE HOOK----------------------------------
	
	@Before
	public void setUp(Scenario scn) throws Exception{

		this.scn = scn; //Assign this to class variable, so that it can be used in all the step def methods

		//Get the browser name by default it is chrome
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser invoked.");

		//Init Page Object Model Objects
		cmnPageObjects = new CmnPageObjects(driver);
		signPageObjects = new SignInPageObjects(driver, scn);
		productSameObjects = new ProductSameObjects(driver, scn);
		footerSectionObjects = new FooterSectionObjects(driver, scn);
	}
	
	//-------------------------AFTER HOOK----------------------------------
	
	@After(order=1)
	public void cleanUp(){
		WebDriverFactory.quitDriver();
		scn.log("Browser Closed");
	}

	@After(order=2) // this will execute first, higher the number, sooner it executes
	public void takeScreenShot(Scenario s) {
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png","Failed Step Name: " + s.getName());
		}else{
			scn.log("Test case is passed, no screen shot captured");
		}
	}
	
	//-------------------------BACKGROUND HOOK AND GIVEN STATEMENT----------------------------------
	//@SuppressWarnings("deprecation")
	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		WebDriverFactory.navigateToTheUrl(base_url);
		scn.log("Browser navigated to URL: " + base_url);

		String expected = "My Store";
		cmnPageObjects.validatePageTitleMatch(expected);
	}

    //------------Test case 1: Landing Page Title-------------------------------------------//
	
	@Then("Page Url should be {string}")
	public void page_url_should_be(String url)
	{
		cmnPageObjects.validateUrl(url);
	}	
	
	
	//-----------------  TEST CASE 2 : PAGE TITLE VALIDATION -----------------------// 
	
	@When("User is on landing page")
	public void user_is_on_landing_page() 
	{
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}


	@Then("Validate title of landing page with expected title as {string}")
	public void validate_title_of_landing_page_with_expected_title_as(String titleOfPage) 
	{
		cmnPageObjects.validatePageTitle(titleOfPage);
		scn.log("Validate page title is: "+ driver.getTitle());
	}
	
	
	//----------------- TEST CASE 3: PRODUCT CATEGRY----------------------------------------------//
	

		@Then("main product categories count should be {int}")
		public void main_product_categories_count_should_be(Integer count) 
		{
			cmnPageObjects.CountTheProductLink(count);
			//scn.log("count of main product categories : "+count);
		}
		
		@When("User Search for product categories {string}")
		public void user_search_for_product_categories(String productCategory)
		{
			cmnPageObjects.SearchCategory(productCategory);
			scn.log("searched for product category : "+productCategory);
		}
		
		@When("display the text of three categories")
		public void display_the_text_of_three_categories() {
			cmnPageObjects.countTheProductLink();
			scn.log("product category displayed");
		}
		
		

	//----------------- Test case 4 : LOGO VALIDATION-------------------------------//
	
	@Then("Display logo on application")
	public void display_logo_on_application() 
	{
		cmnPageObjects.validateLogoDisplay();
		scn.log("Display the application logo on landing page");
	}

	//------------  Test case 5 : LOGO  WIDTH --------------------------------------//
	
	@Then("page should contain logo with desired width as {int}")
	public void page_should_contain_logo_with_desired_width_as_and_height_as(int width) 
	{
		cmnPageObjects.validateLogowidth(width);
		scn.log(" width : "+width);
	}
	
	//----------  Test case 6 :LOGO HEIGHT ------------------------------------------//
	
	@Then("page should contain logo with desired height as {int}")
	public void page_should_contain_logo_with_desired_height_as(Integer height) 
	{
		cmnPageObjects.validateLogoheight( height);
		scn.log("height of logo is : "+height);
	}

	

	
	//------- TEST CASE 7 : SIGNIN BUTTON VALIDATION--------------------------------//
	
	@Given("find signIn button")
	public void find_sign_in_button() 
	{
		signPageObjects.signInButtonValidation();
		scn.log("Display the signIn Button"); 
	}

	@When("click the button signin")
	public void click_the_button_signin()
	{
		signPageObjects.clickSignInButton();
		scn.log("Click the signIn Button"); 
	}
	@Then("user go to signIn page this page title as {string}")
	public void user_go_to_sign_in_page_this_page_title_as(String SignPageTitle) 
	{
	    signPageObjects.signInPageVal(SignPageTitle);
	    scn.log("title validation Successfully "); 
	}
	
	//--------------- TEST CASE 8 : SEARCH PRODUCT ---------------------------------//
	
	
	@Given("User able to see searchBox")
	public void user_able_to_see_search_box() 
	{
		
		productSameObjects.setSearchBox();
	}

	@When("User search for product {string}")
	public void user_search_for_product(String product)
	{
		productSameObjects.searchProduct(product);
	}
	@Then("User see the suggested product list")
	public void user_see_the_suggested_product_list()
	{
		productSameObjects.getSuggestedProdList();
	}
	@Then("Fetch number of products which contain {string} as product name and validate with result as {int}")
	public void fetch_number_of_products_which_contain_as_product_name_and_validate_with_result_as(String prodName, Integer prodSize)
	{
		productSameObjects.validateSuggProdList(prodName, prodSize);
	}
	
	
	//------------- TEST CASE 9 : TWITTER ACCOUNT VALIDATION------------------------//
	
	@When("user search for twitter link from footer section of the landing page")
	public void user_search_for_twitter_link_from_footer_section_of_the_landing_page() {
		cmnPageObjects.validate_footer();
		scn.log("footer validated");
	}
	
	@Then("Product Description is displayed in new tab with title {string}")
	public void product_description_is_displayed_in_new_tab_with_title(String expectedTitle) {
		cmnPageObjects.validate_footer_url(expectedTitle);
		scn.log("footer url validated : "+expectedTitle);
	}

	
	@Then("the twitter account name should be {string}")
	public void the_twitter_account_name_should_be(String str) {
		cmnPageObjects.validate_footer_name(str);
		scn.log("footer name validated : "+str);
	}

	
	//------------------ TEST CASE 10 : FOOTER SECTION VALIDATION------------------------------//

     @Given("User see newsletter text box and proceed button")
     public void user_see_newsletter_text_box_and_proceed_button() 
     {
    	 footerSectionObjects.setNewsLetter();
     }


    @When("User enter email id in newsletter text box and click on proceed button")
    public void user_enter_email_id_in_newsletter_text_box_and_click_on_proceed_button() 
    {
    	footerSectionObjects.enterEmailId();
    	footerSectionObjects.clickOnProceedBtn();
    }
    
    @Then("User see the text message for email subscription")
    public void user_see_the_text_message_for_email_subscription() 
    {
    	footerSectionObjects.fetchSubscriptionMsg();
    	footerSectionObjects.validateSubscriptionMsg();
    }

}
