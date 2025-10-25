package pages;
import org.openqa.selenium.WebDriver;


public class PageManager {
	
	
	private WebDriver driver;
    private HomePage homePage;
    private RevenueCalculator revenueCalculatorPage;
    private ModalPage modalPage;

    // Constructor
    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public RevenueCalculator getRevenueCalculatorPage() {
        if (revenueCalculatorPage == null) {
            revenueCalculatorPage = new RevenueCalculator(driver);
        }
        return revenueCalculatorPage;
    }

    public ModalPage getModalPage() {
        if (modalPage == null) {
            modalPage = new ModalPage(driver);
        }
        return modalPage;
    }

}
