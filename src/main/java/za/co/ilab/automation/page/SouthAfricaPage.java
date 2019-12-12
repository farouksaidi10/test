package za.co.ilab.automation.page;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import za.co.ilab.automation.utilities.Utilities;

import java.io.IOException;
import java.util.logging.Logger;

public class SouthAfricaPage {
    //CURRENT OPENINGS

    public static final Logger log4J = Logger.getLogger(SouthAfricaPage.class.getName());
    private WebDriver driver;
    private ExtentTest extentTest;

    @FindBy( xpath = "//*[@href='https://www.ilabquality.com/job/senior-test-automation-specialist-cape-town-2/']" )
    WebElement openVacation;

    public SouthAfricaPage(WebDriver driver, ExtentTest extentTest) throws IOException {
        log4J.info("==================================== South Africa Careers screen ===================================== ");
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.extentTest = extentTest;
        this.extentTest.info("Navigating to South Africa careers screen", MediaEntityBuilder.createScreenCaptureFromPath( Utilities.getScreenShot(this.driver)).build());
    }

    public void clickOnOpenVacancy() throws IOException {
        log4J.info("Clicking a specialist position " + openVacation.toString());
        openVacation.click();
        this.extentTest.info("Clicking a specialist position", MediaEntityBuilder.createScreenCaptureFromPath( Utilities.getScreenShot(this.driver)).build());
    }
}
