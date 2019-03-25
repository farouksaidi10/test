package za.co.ilab.automation.page;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import za.co.ilab.automation.excelreader.ExcelReader;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import static za.co.ilab.automation.extentmanager.ExtentManger.createInstance;

public class TestBase {

    public static final Logger log4j = Logger.getLogger(TestBase.class.getName());

    public static WebDriver driver;

    private ExcelReader excelReader;

    public static ExtentReports extent;

    public TestBase() {
        driver = null;
    }

    public void before() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        log4j.info("Logging chrome browser");
        extent = createInstance("Index.html");
    }

    public void after() {
        if (driver != null) {
            driver.quit();
            log4j.info("================ Closing the browser session, test complete ========");
        }
    }

    protected String getSiteURL() {
        String URL = System.getProperty("testURL");
        if (URL == null)
            return "https://www.ilabquality.com/";
        else {
            return URL;
        }
    }

    public String[][] getData(String excelName, String sheetName) {
        String path = "src/main/java/za/co/ilab/automation/data/" + excelName;
        excelReader = new ExcelReader(path);
        String[][] data = excelReader.getDataFromExcelSheet(sheetName);
        return data;
    }

    protected WebDriver getWebDriver() {
        return driver;
    }
}
