package za.co.ilab.automation.page;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import za.co.ilab.automation.extentmanager.ExtentManger;
import za.co.ilab.automation.utilities.Utilities;

import java.io.IOException;
import java.util.logging.Logger;

public class HomePage  {

    private static final Logger log4J = Logger.getLogger(HomePage.class.getName());

    private WebDriver driver;
    private ExtentReporter reporter;
    private ExtentTest extentTest;

    @FindBy( xpath = "//nav//ul//li//a[text()= 'CAREERS']" )
    WebElement careersLink;

    public HomePage(WebDriver driver, ExtentTest extentTest) throws IOException {
        PageFactory.initElements(driver, this);
        this.extentTest = extentTest;
        this.driver = driver;
        this.extentTest.info("Launching the iLab website", MediaEntityBuilder.createScreenCaptureFromPath( Utilities.getScreenShot(this.driver)).build());
        log4J.info("============================ navigating to iLab home page =================================");
    }

    public void clickCareers() throws IOException {
        careersLink.click();
        log4J.info("Clicking on the careers link " + careersLink.toString() );
        extentTest.info("Clicking on the careers link", MediaEntityBuilder.createScreenCaptureFromPath( Utilities.getScreenShot(this.driver)).build());
    }
}
