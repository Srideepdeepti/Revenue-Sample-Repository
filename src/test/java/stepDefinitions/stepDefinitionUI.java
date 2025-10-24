package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.Map;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



import io.cucumber.java.en.*;
import pages.HomePage;
import pages.ModalPage;
import pages.RevenueCalculator;
import utils.CurrencyFormatter;
import utils.calculateDuty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertEquals;


public class stepDefinitionUI {
	

	private static final Logger logger =  LogManager.getLogger(stepDefinitionUI.class);

    public static WebDriver driver;
    
    HomePage homePage;
    RevenueCalculator  revenueCalculatorPage ;
	ModalPage modalPage;
	

@Given("I open the Service NSW check motor vehicle stamp duty page")
public void i_open_the_service_nsw_check_motor_vehicle_stamp_duty_page() {
    
	driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.service.nsw.gov.au/transaction/check-motor-vehicle-stamp-duty");
    homePage = new HomePage(driver);	
    revenueCalculatorPage = new RevenueCalculator(driver);
}
@When("I click the Check Online button")
public void i_click_the_check_online_button() {

   homePage.clickCheckOnline();      
}

@Then("The Check Online page should be displayed")
public void the_check_online_page_should_be_displayed() {

  logger.info("Asserting1234 that Page is opened...");
   assertTrue(revenueCalculatorPage.isPageOpened());
  logger.info("Pass123: Page gets opened...");
  logger.info("Clicking(update2) on Yes button...");
  revenueCalculatorPage.clickYes();
  
}


@Then("on entering the {int} in page")
public void on_entering_the_in_page(Integer amount) {
	    
	revenueCalculatorPage.enterPurchasePrice(amount.toString());
	    
}


@Then("click on Calculate button")
public void click_on_calculate_button() {
    // Write code here that turns the phrase above into concrete actions
	revenueCalculatorPage.click_on_Calculate_button();
}

@Then("All the details like  {string} {string} are correct")
public void all_the_details_like_are_correct(String registration_status, String expectedDuty) {
    
	modalPage = new ModalPage(driver);
    logger.info("Validating Modal page dialog gets opened...");
    assertTrue(modalPage.isModalDisplayed());
    
    logger.info("Validation Sucessfull...");
    
    logger.info("Title ; "+ modalPage.getModalTitle());
    Map<String, String> map= modalPage.getDetailsEntered();
    logger.info(map.get("Is this registration for a passenger vehicle?"));
   
    logger.info("Validating Registration status, Vehicle Value and Duty details on the dialog...");
    assertEquals(map.get("Is this registration for a passenger vehicle?"),registration_status ); 
    logger.info(map.get("Purchase price or value"));
    String enteredPrice = revenueCalculatorPage.getPurchasePrice();
   
    enteredPrice = enteredPrice.replace(",", "");

   // Convert to integer
    long num = Long.parseLong(enteredPrice);

   String actual_VehicleValue = CurrencyFormatter.convertToAud(num);
   String expected_VehicleValue =(map.get("Purchase price or value"));
   logger.info("Validating Vehicle Value on the Modal Dialog..");
   assertEquals(actual_VehicleValue,expected_VehicleValue );
   logger.info("Validation Pass...");
   String actual_Duty= map.get("Duty payable");
   
   logger.info(("CAlculate duty : "+ calculateDuty.calculatepayableDuty(enteredPrice)));
   String expected_Duty= CurrencyFormatter.convertToAud(calculateDuty.calculatepayableDuty(enteredPrice));
   
   logger.info("Actual_Duty :"+actual_Duty);
   System.out.println("Expected_Duty :"+expected_Duty);
   logger.info("Validating Duty Value on the Modal Dialog..");
   assertEquals( actual_Duty,expected_Duty);
   assertEquals( actual_Duty,expectedDuty);
   logger.info("Validation Pass...");
   
   modalPage.closeModal();
}


}


