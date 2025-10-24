package stepDefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
		
		

	    @After
	    public void tearDown() {
	        if (stepDefinition.driver != null) {
	            System.out.println("Closing browser after scenario...");
	            stepDefinition.driver.quit(); // or driver.close()
	        }
	    }
	}


