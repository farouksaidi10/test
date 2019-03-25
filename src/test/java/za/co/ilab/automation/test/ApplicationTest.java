package za.co.ilab.automation.test;

import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import za.co.ilab.automation.page.*;

import static za.co.ilab.automation.extentmanager.ExtentManger.getInstance;

public class ApplicationTest extends TestBase {

    private HomePage homePage;
    private CareersPage careersPage;
    private SouthAfricaPage southAfricaPage;
    private OnlineApplicationPage onlineApplicationPage;
    private ExtentTest extentTest;

    @BeforeClass
    public void init() throws Exception {
        super.before();
        getWebDriver().get(getSiteURL());
    }

    @DataProvider(name = "ApplicationInfo")
    public Object[][] getData() {
        String[][] testData = getData( "iLab.xlsx", "Application");
        return testData;
    }

    @Test(dataProvider = "ApplicationInfo")
    public void ApplicationTest(String testCaseName, String name, String email, String cellNumber) throws Exception {
        extentTest = getInstance().createTest(testCaseName);
        homePage = new HomePage(getWebDriver(), extentTest);
        homePage.clickCareers();
        careersPage = new CareersPage(getWebDriver(), extentTest);
        careersPage.clickOnSouthAfricaLink();
        southAfricaPage = new SouthAfricaPage(getWebDriver(), extentTest);
        southAfricaPage.clickOnOpenVacancy();
        onlineApplicationPage = new OnlineApplicationPage(getWebDriver(), extentTest);
        onlineApplicationPage.clickOnOnlineButton();
        onlineApplicationPage.enterApplicationInformation( name, email, cellNumber );
        Thread.sleep(2000L);
    }

    @AfterClass( alwaysRun = true )
    public void After() {
        super.after();
        getInstance().flush();
    }
}
