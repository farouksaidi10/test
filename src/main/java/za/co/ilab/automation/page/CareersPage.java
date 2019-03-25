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

public class CareersPage {

    public static  Logger log4J = Logger.getLogger(CareersPage.class.getName());
    private WebDriver driver;
    private ExtentTest extentTest;

    @FindBy( xpath = "//*[text() = 'South Africa']" )
    WebElement southAfricaLink;

    public CareersPage( WebDriver driver, ExtentTest extentTest ) throws IOException {
        log4J.info("===================================== ILab Careers Page ================================== ");
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.extentTest = extentTest;
        this.extentTest.info("Navigated to ILab careers page", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.getScreenShot(this.driver)).build());
    }

    public void clickOnSouthAfricaLink() throws IOException {
        southAfricaLink.click();
        log4J.info("Click on the south africa clink " + southAfricaLink.toString());
        this.extentTest.info("Click on the south africa clink", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.getScreenShot(this.driver)).build());
    }
}
