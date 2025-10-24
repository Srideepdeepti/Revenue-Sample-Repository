package pages;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RevenueCalculator {
	
	 WebDriver driver;
	 
	 @FindBy(css = "label[for='passenger_Y']")
	 WebElement radio_Yes;
	 
	 @FindBy(xpath = "//input[@id='purchasePrice']")
	 WebElement purchase_Textbox;
	 
	 
	 @FindBy(xpath = "//button[contains(text(),'Calculate')]")
	 WebElement calculate_Button;

	 public RevenueCalculator(WebDriver driver){
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	 
	 public String getPageTitle(){
	        return driver.getTitle();
	    }

	public boolean isPageOpened(){
	        // You can also check for a unique element instead of title
	        return driver.getTitle().contains("Motor vehicle registration duty calculator");

	    }
	
	public void clickYes(){
        // You can also check for a unique element instead of title
		
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(radio_Yes));
		    radioButton.click();
        
		
    }
	
	public void enterPurchasePrice(String amount){
        // You can also check for a unique element instead of title
        
		purchase_Textbox.sendKeys(amount);
    }
	
	public String getPurchasePrice(){
        // You can also check for a unique element instead of title
      
		return purchase_Textbox.getAttribute("value");
    }
	
	public void click_on_Calculate_button(){
        // You can also check for a unique element instead of title
        
		calculate_Button.click();
    }
}
