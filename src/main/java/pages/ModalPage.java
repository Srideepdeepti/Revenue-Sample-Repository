package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

	
public class ModalPage {

	    WebDriver driver;
	    WebDriverWait wait;

	    @FindBy(id = "openModal")
	    WebElement openModalButton;

	    @FindBy(xpath = "//div[@class='modal-header']")
	    WebElement modalPopup;

	    @FindBy(xpath="//div[@class='modal-header']//h4")
	    WebElement modalTitle;

	    @FindBy(id = "modalMessage")
	    WebElement modalMessage;

	    @FindBy(xpath = "//div[@class='modal-footer']/button")
	    WebElement closeModalButton;
	    
	    @FindBy(xpath = "//table[@class='TableApp']")
	    WebElement modalDetails;
	    
	    @FindBy(xpath = "//table//h4")
	    WebElement heading;


	    public ModalPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    public void clickOpenModal() {
	        openModalButton.click();
	    }

	    public boolean isModalDisplayed() {
	        wait.until(ExpectedConditions.visibilityOf(modalPopup));
	        return modalPopup.isDisplayed();
	    }

	    public String getModalTitle() {
	        return modalTitle.getText();
	    }
	    
	   
	    public String getHeading() {
	        return heading.getText();
	    }


	    

	    public void closeModal() {
	        closeModalButton.click();
	        wait.until(ExpectedConditions.invisibilityOf(modalPopup));
	    }
	    
	    
	   private Map<String, String> getTableData(WebElement table) {
	        Map<String, String> data = new LinkedHashMap<>();
	        List<WebElement> rows = table.findElements(By.xpath("//tr"));
	        for (WebElement row : rows) {
	            List<WebElement> cols = row.findElements(By.tagName("td"));
	            if (cols.size() == 2) {
	                String key = cols.get(0).getText().trim();
	                String value = cols.get(1).getText().trim();
	                data.put(key, value);
	            }
	        }
	        return data;
	    }

	    public Map<String, String> getDetailsEntered() {
	        return getTableData(modalDetails);
	    }

	    
	    }
	
	
	
	


