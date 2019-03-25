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

import static za.co.ilab.automation.utilities.Utilities.isTextOnPage;

public class OnlineApplicationPage {
    //We are in search of a Senior Technical Test Lead to assess the current automation requirements within a Business as usual environment
    private WebDriver driver;
    private ExtentTest extentTest;

    public static final Logger log4J = Logger.getLogger(OnlineApplicationPage.class.getName());

    @FindBy(xpath = "//*[contains(text(),'Apply Online')]")
    WebElement applyOnlineButton;

    @FindBy(id = "applicant_name")
    WebElement applicantNameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "phone")
    WebElement phoneField;

    @FindBy( id = "wpjb_submit")
    WebElement sendApplicationButton;

    public OnlineApplicationPage(WebDriver driver, ExtentTest extentTest) throws IOException {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.extentTest = extentTest;
        log4J.info("==================================== Online Application Screen ===============================");
        this.extentTest.info("Online Application Screen ", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.getScreenShot(this.driver)).build());
    }

    public void clickOnOnlineButton() throws IOException {
        log4J.info("Clicking on apply online button " + applyOnlineButton.toString());
        applyOnlineButton.click();
        this.extentTest.info("Clicking on apply online button ", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.getScreenShot(this.driver)).build());
    }

    public void enterApplicationInformation(String name, String email, String phone) throws Exception {
        Utilities.getScreenShot(this.driver);
        applicantNameField.clear();
        applicantNameField.sendKeys(name);
        log4J.info("Entering applicant name " + name + " on text field " + applicantNameField.toString());

        emailField.clear();
        emailField.sendKeys(email);
        log4J.info("Entering email name " + email + " on text field " + emailField.toString());

        phoneField.clear();
        phoneField.sendKeys(phone);
        log4J.info("Entering phone number " + phone + " on text field " + phoneField.toString());

        this.extentTest.info("Application information screen ", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.getScreenShot(this.driver)).build());

        sendApplicationButton.click();

        isTextOnPage( driver, "You need to upload at least one file.");
        this.extentTest.info("Error messae application screenshot ", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.getScreenShot(this.driver)).build());
        /* browseFileButton.sendKeys(fileName);*/
    }
}
