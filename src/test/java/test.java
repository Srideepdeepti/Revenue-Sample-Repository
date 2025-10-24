import java.text.NumberFormat;
import java.time.Duration;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.HomePage;
import pages.ModalPage;
import pages.RevenueCalculator;
import utils.CurrencyFormatter;
import utils.calculateDuty;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String amount = "47896";
		
		ChromeDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://www.service.nsw.gov.au/transaction/check-motor-vehicle-stamp-duty");
	    HomePage homePage = new HomePage(driver);
	    
	    System.out.println("Stampt Duty calculated : "+ calculateDuty.calculatepayableDuty(amount));
	    
	    
	    NumberFormat formattercurrency = NumberFormat.getCurrencyInstance(Locale.US);
        String formatted1 = formattercurrency.format(calculateDuty.calculatepayableDuty(amount));

        System.out.println("Formatted Currency : "+ CurrencyFormatter.convertToAud(calculateDuty.calculatepayableDuty(amount)));
	    homePage.clickCheckOnline();
	    
	    RevenueCalculator revenueCalculator = new RevenueCalculator(driver);
	    
	   
	    revenueCalculator.clickYes();
	    revenueCalculator.enterPurchasePrice(amount);
	    revenueCalculator.click_on_Calculate_button();
	    
	    ModalPage modalPage = new ModalPage(driver);
	    
	    Assert.assertTrue(modalPage.isModalDisplayed());
	    
	    System.out.println("Title ; "+ modalPage.getModalTitle());
	    Map<String, String> map= modalPage.getDetailsEntered();
	   System.out.println(map.get("Is this registration for a passenger vehicle?"));
	   System.out.println(map.get("Purchase price or value"));
	   String enteredPrice = revenueCalculator.getPurchasePrice();
	   
	   enteredPrice = enteredPrice.replace(",", "");

       // Convert to integer
       
	   
	   long num = Long.parseLong(enteredPrice);

       // Optional Step 3: Display/convert as 567.00
     //  String formatted = '$'+String.format("%.2f", num);

       //System.out.println("Double value: " + num);       // Prints: 567.0
       String actual_VehicleValue = CurrencyFormatter.convertToAud(num);
       String expected_VehicleValue =(map.get("Purchase price or value"));
	   
	   Assert.assertEquals(actual_VehicleValue,expected_VehicleValue );
	   String actual_Duty= map.get("Duty payable");
	   
	   System.out.println(("CAlculate duty : "+ calculateDuty.calculatepayableDuty(amount)));
	   String expected_Duty= CurrencyFormatter.convertToAud(calculateDuty.calculatepayableDuty(amount));
	   
	   System.out.println("Actual_Duty :"+actual_Duty);
	   System.out.println("Expected_Duty :"+expected_Duty);
	   
	   Assert.assertEquals( actual_Duty,expected_Duty);
	   
	    
	    modalPage.closeModal();
	   
	    driver.close();
	    

	}

}
