@ui @healthcheck
  Feature: E-commerce Project test cases

  Background: Navigation to the URL
    Given User navigated to the home application url

  @t1_pageRedirection
  Scenario: User is able to Open the browser, validate the Url
    Then Page Url should be "http://automationpractice.com/index.php"
 
  @t2_titleOfPage
  Scenario: User naviaget to URL, redirect to landing page with expected page title
  When User is on landing page
  Then Validate title of landing page with expected title as "My Store"
  
  @t3_productCategory
  Scenario Outline: Application product main category list validation
    Then main product categories count should be 3
    When User Search for product categories "<product_list>"
    And display the text of three categories

    Examples: 
      | product_list |
      | women        |
      | dresses      |
      | T-shirts     |
  
  @t4_LogoDisplay
  Scenario: User able to see logo of application on landing page
  When User is on landing page
  Then Display logo on application
  
   @t5_LogoHeightWidth
   Scenario: User is able to Open the browser, check the logo visibility 
   Then page should contain logo with desired width as 350
   
   @t6_LogoHeight
   Scenario: User is able to Open the browser, check the logo visibility 
   Then page should contain logo with desired height as 99
    
   @t7_signInButton
   Scenario: User click on SignIn button and navigate to Login page
   Given find signIn button
   When click the button signin
   Then user go to signIn page this page title as "Login - My Store"
   
   @t8_SearchProduct
   Scenario: User search a product and see the suggested product list 
   Given User able to see searchBox
   When User search for product "Dress"
   Then User see the suggested product list
   And Fetch number of products which contain "Dress" as product name and validate with result as 5
     
   @t9_twitter
   Scenario: user is able to open the browser,and can validate social media link
    When user search for twitter link from footer section of the landing page
    Then Product Description is displayed in new tab with title "seleniumfrmwrk"
    And the twitter account name should be "Selenium Framework"
  
   @t10_newsletter
   Scenario: User enter email id in newsletter text box and click on proceed button and see the text message for email subscription.
   Given User see newsletter text box and proceed button
   When User enter email id in newsletter text box and click on proceed button
   Then User see the text message for email subscription
 